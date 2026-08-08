package com.skd.workhandtools;

import java.util.List;

import org.joml.Matrix4f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
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

        AoEMode mode = grade.hasMode()
                ? stack.getOrDefault(ModDataComponents.AOE_MODE, AoEMode.CUBIC)
                : AoEMode.CUBIC;
        boolean lookingUp = player.getXRot() < Config.PITCH_THRESHOLD_DEGREES.get();
        List<BlockPos> pattern = AoEPatterns.computePattern(target, player, grade, mode, lookingUp);

        if (pattern.isEmpty()) {
            return;
        }

        PoseStack poseStack = event.getPoseStack();
        Camera camera = mc.gameRenderer.mainCamera();
        Vec3 camPos = camera.position();

        poseStack.pushPose();
        poseStack.translate(-camPos.x, -camPos.y, -camPos.z);

        for (BlockPos pos : pattern) {
            AABB box = new AABB(pos).inflate(0.002);
            event.getSubmitNodeCollector().submitCustomGeometry(
                    poseStack,
                    RenderTypes.LINES_TRANSLUCENT,
                    (poseEntry, consumer) -> renderLineBox(poseEntry, consumer, box, 1.0F, 1.0F, 1.0F, 0.5F));
        }

        poseStack.popPose();
    }

    private static void renderLineBox(PoseStack.Pose poseEntry, VertexConsumer consumer,
            AABB box, float r, float g, float b, float a) {
        Matrix4f matrix = poseEntry.pose();

        float x1 = (float) box.minX;
        float y1 = (float) box.minY;
        float z1 = (float) box.minZ;
        float x2 = (float) box.maxX;
        float y2 = (float) box.maxY;
        float z2 = (float) box.maxZ;

        drawLine(consumer, matrix, x1, y1, z1, x2, y1, z1, r, g, b, a);
        drawLine(consumer, matrix, x2, y1, z1, x2, y1, z2, r, g, b, a);
        drawLine(consumer, matrix, x2, y1, z2, x1, y1, z2, r, g, b, a);
        drawLine(consumer, matrix, x1, y1, z2, x1, y1, z1, r, g, b, a);

        drawLine(consumer, matrix, x1, y2, z1, x2, y2, z1, r, g, b, a);
        drawLine(consumer, matrix, x2, y2, z1, x2, y2, z2, r, g, b, a);
        drawLine(consumer, matrix, x2, y2, z2, x1, y2, z2, r, g, b, a);
        drawLine(consumer, matrix, x1, y2, z2, x1, y2, z1, r, g, b, a);

        drawLine(consumer, matrix, x1, y1, z1, x1, y2, z1, r, g, b, a);
        drawLine(consumer, matrix, x2, y1, z1, x2, y2, z1, r, g, b, a);
        drawLine(consumer, matrix, x2, y1, z2, x2, y2, z2, r, g, b, a);
        drawLine(consumer, matrix, x1, y1, z2, x1, y2, z2, r, g, b, a);
    }

    private static void drawLine(VertexConsumer consumer, Matrix4f matrix,
            float x1, float y1, float z1, float x2, float y2, float z2,
            float r, float g, float b, float a) {
        consumer.addVertex(matrix, x1, y1, z1).setColor(r, g, b, a).setNormal(0, 1, 0);
        consumer.addVertex(matrix, x2, y2, z2).setColor(r, g, b, a).setNormal(0, 1, 0);
    }

    public static void register() {
        if (registered) {
            return;
        }
        registered = true;
        NeoForge.EVENT_BUS.register(new AreaHighlighter());
    }
}
