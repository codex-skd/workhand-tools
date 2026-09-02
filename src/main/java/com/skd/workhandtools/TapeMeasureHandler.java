package com.skd.workhandtools;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.joml.Matrix4f;

import java.util.List;

// Client-side glue for the tape measure: clears the box list on login/logout, updates the
// pending box's end position from the crosshair each player tick, and renders finished/in-progress
// boxes every frame using the RenderLevelStageEvent API (matching the existing AreaHighlighter
// style in this project). Collapses Mrbysco's Measurements mod ClientClass + ClientHandler +
// LoginHandler into one class (no multi-loader split needed here).
public final class TapeMeasureHandler {

    private static boolean registered;

    private TapeMeasureHandler() {
    }

    @SubscribeEvent
    public void onLogIn(ClientPlayerNetworkEvent.LoggingIn event) {
        BoxHandler.clear();
    }

    @SubscribeEvent
    public void onLogOut(ClientPlayerNetworkEvent.LoggingOut event) {
        BoxHandler.clear();
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        if (!event.getEntity().level().isClientSide()) {
            return;
        }
        Player player = event.getEntity();
        if (Minecraft.getInstance().player != player) {
            return;
        }

        if (!player.isHolding(ModItems.TAPE_MEASURE.get())) {
            BoxHandler.clear();
            return;
        }

        List<MeasurementBox> boxList = BoxHandler.getBoxList();
        if (!boxList.isEmpty()) {
            MeasurementBox lastBox = boxList.getLast();
            if (!lastBox.isFinished()) {
                HitResult rayHit = Minecraft.getInstance().hitResult;
                if (rayHit != null && rayHit.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult blockHitResult = (BlockHitResult) rayHit;
                    lastBox.setBlockEnd(new BlockPos(blockHitResult.getBlockPos()));
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            return;
        }

        final Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null || !player.isHolding(ModItems.TAPE_MEASURE.get())) {
            return;
        }

        Matrix4f projectionMatrix = event.getProjectionMatrix();
        PoseStack poseStack = event.getPoseStack();
        Camera camera = minecraft.gameRenderer.getMainCamera();
        MultiBufferSource.BufferSource buffers = minecraft.renderBuffers().bufferSource();

        final ResourceKey<Level> currentDimension = player.level().dimension();
        poseStack.pushPose();
        List<MeasurementBox> boxList = BoxHandler.getBoxList();
        boxList.forEach(box -> box.render(currentDimension, poseStack, buffers, camera, projectionMatrix));
        poseStack.popPose();
        buffers.endBatch(net.minecraft.client.renderer.RenderType.lines());
    }

    public static void register() {
        if (registered) {
            return;
        }
        registered = true;
        NeoForge.EVENT_BUS.register(new TapeMeasureHandler());
    }
}
