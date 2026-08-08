package com.skd.workhandtools;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.gizmos.GizmoStyle;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.common.NeoForge;

public final class AreaHighlighter {

    private static boolean registered;
    private static final List<BlockPos> highlightCache = new ArrayList<>();

    @SubscribeEvent
    public void onRenderLevel(RenderLevelStageEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || player.isShiftKeyDown()) {
            highlightCache.clear();
            return;
        }

        ItemStack stack = player.getMainHandItem();
        Grade grade = ModItems.gradeOf(stack);
        if (grade == null) {
            highlightCache.clear();
            return;
        }

        HitResult hit = mc.hitResult;
        if (!(hit instanceof BlockHitResult blockHit) || hit.getType() == HitResult.Type.MISS) {
            highlightCache.clear();
            return;
        }

        BlockPos target = blockHit.getBlockPos();
        if (!stack.isCorrectToolForDrops(player.level().getBlockState(target))) {
            highlightCache.clear();
            return;
        }

        AoEMode mode = grade.hasMode()
                ? stack.getOrDefault(ModDataComponents.AOE_MODE, AoEMode.CUBIC)
                : AoEMode.CUBIC;
        boolean lookingUp = player.getXRot() < Config.PITCH_THRESHOLD_DEGREES.get();
        List<BlockPos> pattern = AoEPatterns.computePattern(target, player, grade, mode, lookingUp);

        highlightCache.clear();
        highlightCache.addAll(pattern);

        int fillColor = 0x40FFFF00;
        for (BlockPos pos : highlightCache) {
            Gizmos.cuboid(pos, GizmoStyle.fill(fillColor));
        }
    }

    public static void register() {
        if (registered) {
            return;
        }
        registered = true;
        NeoForge.EVENT_BUS.register(new AreaHighlighter());
    }
}
