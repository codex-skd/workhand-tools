package com.skd.workhandtools;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.SubmitCustomGeometryEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.joml.Matrix4fc;

import java.util.List;

// Client-side glue for the tape measure: clears the box list on login/logout, updates the
// pending box's end position from the crosshair each player tick, and renders finished/in-progress
// boxes every frame using the Gizmos API (matching the existing AreaHighlighter style in this
// project). Collapses Mrbysco's Measurements mod ClientClass + ClientHandler + LoginHandler into
// one class (no multi-loader split needed here).
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
    public void onSubmitCustomGeometry(SubmitCustomGeometryEvent event) {
        final Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null || !player.isHolding(ModItems.TAPE_MEASURE.get())) {
            return;
        }

        Matrix4fc projectionMatrix = minecraft.gameRenderer.gameRenderState().levelRenderState.cameraRenderState.viewRotationMatrix;
        PoseStack poseStack = event.getPoseStack();
        Camera camera = minecraft.gameRenderer.mainCamera();
        SubmitNodeCollector nodeCollector = event.getSubmitNodeCollector();

        final ResourceKey<Level> currentDimension = player.level().dimension();
        poseStack.pushPose();
        List<MeasurementBox> boxList = BoxHandler.getBoxList();
        boxList.forEach(box -> box.render(currentDimension, poseStack, nodeCollector, camera, projectionMatrix));
        poseStack.popPose();
    }

    public static void register() {
        if (registered) {
            return;
        }
        registered = true;
        NeoForge.EVENT_BUS.register(new TapeMeasureHandler());
    }
}
