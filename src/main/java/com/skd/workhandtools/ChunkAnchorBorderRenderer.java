package com.skd.workhandtools;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.common.NeoForge;

// Draws only the 4 vertical corner edges of the chunk column containing an active Chunk Anchor
// (tome present), per docs/DESIGN_WORKHAND_TOOLS.md "Indicador visual del chunk (borde de esquinas)".
// Height window (-64..320) matches this version's default world build limits; a fixed window keeps
// the geometry cheap instead of scanning for actual terrain height.
// ClientLevel exposes no public "all loaded block entities" accessor, so active Chunk Anchors
// track themselves into CLIENT_ANCHORS from setLevel/setRemoved (client side only).
public final class ChunkAnchorBorderRenderer {

    private static final Set<ChunkAnchorBlockEntity> CLIENT_ANCHORS = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private static boolean registered;

    public static void trackClientAnchor(ChunkAnchorBlockEntity anchor) {
        CLIENT_ANCHORS.add(anchor);
    }

    public static void untrackClientAnchor(ChunkAnchorBlockEntity anchor) {
        CLIENT_ANCHORS.remove(anchor);
    }

    @SubscribeEvent
    public void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || mc.level == null) {
            return;
        }

        Config.ChunkAnchorBorderMode mode = Config.CHUNK_ANCHOR_BORDER_MODE.get();
        BlockPos playerPos = player.blockPosition();
        double maxDistSq;
        if (mode == Config.ChunkAnchorBorderMode.ALWAYS) {
            // "within render distance" per the config comment, not literally unlimited - the corner
            // lines are 384 blocks tall (minY..maxY), so without this cap they stay visible far past
            // where terrain/fog would normally hide anything else at that position.
            double renderDistanceBlocks = mc.options.getEffectiveRenderDistance() * 16.0;
            maxDistSq = renderDistanceBlocks * renderDistanceBlocks;
        } else {
            maxDistSq = 400.0; // 20^2
        }

        for (ChunkAnchorBlockEntity anchor : CLIENT_ANCHORS) {
            // Same reasoning as ChunkAnchorRenderer: read HAS_TOME off the synced BlockState, not
            // anchor.hasTome() (server-only ItemStack, never synced to the client block entity).
            if (anchor.isRemoved() || !anchor.getBlockState().getValue(ChunkAnchorBlock.HAS_TOME)) {
                continue;
            }

            BlockPos anchorPos = anchor.getBlockPos();
            if (playerPos.distSqr(anchorPos) > maxDistSq) {
                continue;
            }

            if (mode == Config.ChunkAnchorBorderMode.SNEAK_LOOKING) {
                if (!player.isShiftKeyDown()) {
                    continue;
                }
                Vec3 toAnchor = new Vec3(
                        anchorPos.getX() + 0.5 - player.getX(),
                        anchorPos.getY() + 0.5 - player.getY(),
                        anchorPos.getZ() + 0.5 - player.getZ()).normalize();
                if (player.getLookAngle().dot(toAnchor) < 0.95) {
                    continue;
                }
            }

            renderChunkBorder(event, anchorPos);
        }
    }

    private void renderChunkBorder(RenderLevelStageEvent event, BlockPos anchorPos) {
        ChunkPos chunkPos = new ChunkPos(anchorPos.getX() >> 4, anchorPos.getZ() >> 4);
        int minX = chunkPos.getMinBlockX();
        int minZ = chunkPos.getMinBlockZ();
        int maxX = chunkPos.getMaxBlockX() + 1;
        int maxZ = chunkPos.getMaxBlockZ() + 1;
        int minY = -64;
        int maxY = 320;

        PoseStack poseStack = event.getPoseStack();
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        Vec3 camPos = camera.getPosition();
        MultiBufferSource.BufferSource buffers = Minecraft.getInstance().renderBuffers().bufferSource();

        poseStack.pushPose();
        poseStack.translate(-camPos.x, -camPos.y, -camPos.z);

        drawCorner(poseStack, buffers, minX, minZ, minY, maxY);
        drawCorner(poseStack, buffers, maxX, minZ, minY, maxY);
        drawCorner(poseStack, buffers, maxX, maxZ, minY, maxY);
        drawCorner(poseStack, buffers, minX, maxZ, minY, maxY);

        poseStack.popPose();
        buffers.endBatch(RenderType.lines());
    }

    private void drawCorner(PoseStack poseStack, MultiBufferSource.BufferSource buffers, int x, int z, int minY, int maxY) {
        // Draw a thin degenerate AABB along the corner edge (4 vertical lines collapsed into one box)
        AABB aabb = new AABB(x, minY, z, x, maxY, z).inflate(0.02);
        LevelRenderer.renderLineBox(poseStack, buffers.getBuffer(RenderType.lines()), aabb, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void register() {
        if (registered) {
            return;
        }
        registered = true;
        NeoForge.EVENT_BUS.register(new ChunkAnchorBorderRenderer());
    }
}
