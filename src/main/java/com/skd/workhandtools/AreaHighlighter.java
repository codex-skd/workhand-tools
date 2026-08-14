package com.skd.workhandtools;

import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.SubmitCustomGeometryEvent;
import net.neoforged.neoforge.common.NeoForge;

public final class AreaHighlighter {

    private static boolean registered;

    @SubscribeEvent
    public void onSubmitCustomGeometry(SubmitCustomGeometryEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || player.isShiftKeyDown()) {
            return;
        }

        ItemStack stack = player.getMainHandItem();
        Grade grade = ModItems.gradeOf(stack);
        if (grade == null) {
            return;
        }

        HitResult hit = mc.hitResult;
        if (!(hit instanceof BlockHitResult blockHit) || hit.getType() == HitResult.Type.MISS) {
            return;
        }

        BlockPos target = blockHit.getBlockPos();
        if (!stack.isCorrectToolForDrops(player.level().getBlockState(target))) {
            return;
        }

        AoEMode mode = stack.getOrDefault(ModDataComponents.AOE_MODE, grade.hasMode() ? AoEMode.CUBIC : AoEMode.FLAT);
        List<BlockPos> pattern;
        if (mode == AoEMode.DISABLED) {
            pattern = List.of(target);
        } else {
            boolean lookingUp = player.getXRot() < Config.PITCH_THRESHOLD_DEGREES.get();
            Direction digDir = AoEPatterns.digDirection(player, player.level(), target);
            pattern = AoEPatterns.computePattern(target, grade, mode, digDir, lookingUp);
        }

        if (pattern.isEmpty()) {
            return;
        }

        PoseStack poseStack = event.getPoseStack();
        Camera camera = mc.gameRenderer.mainCamera();
        Vec3 camPos = camera.position();

        poseStack.pushPose();
        poseStack.translate(-camPos.x, -camPos.y, -camPos.z);

        for (BlockPos pos : pattern) {
            if (player.level().getBlockState(pos).isAir()) {
                continue;
            }
            VoxelShape shape = Shapes.create(new AABB(pos).inflate(0.002));
            event.getSubmitNodeCollector().submitShapeOutline(
                    poseStack,
                    shape,
                    RenderTypes.LINES_TRANSLUCENT,
                    0xFFFFFFFF,
                    0.5F,
                    false);
        }

        poseStack.popPose();
    }

    public static void register() {
        if (registered) {
            return;
        }
        registered = true;
        NeoForge.EVENT_BUS.register(new AreaHighlighter());
    }
}
