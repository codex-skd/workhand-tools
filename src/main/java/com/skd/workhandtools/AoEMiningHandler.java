package com.skd.workhandtools;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;

public class AoEMiningHandler {

    @SubscribeEvent
    public void onBlockBreak(BreakBlockEvent event) {
        Player player = event.getPlayer();
        if (player == null || player.isShiftKeyDown()) {
            return;
        }
        Level level = event.getLevel() instanceof Level l ? l : null;
        if (level == null || level.isClientSide()) {
            return;
        }
        ItemStack stack = player.getMainHandItem();
        Grade grade = ModItems.gradeOf(stack);
        if (grade == null) {
            return;
        }

        AoEMode mode = grade.hasMode()
                ? stack.getOrDefault(ModDataComponents.AOE_MODE, AoEMode.CUBIC)
                : AoEMode.CUBIC;
        boolean lookingUp = player.getXRot() < Config.PITCH_THRESHOLD_DEGREES.get();
        List<BlockPos> pattern = AoEPatterns.computePattern(event.getPos(), player, grade, mode, lookingUp);

        for (BlockPos pos : pattern) {
            if (pos.equals(event.getPos())) {
                continue;
            }
            if (!level.isLoaded(pos)) {
                continue;
            }
            BlockState state = level.getBlockState(pos);
            if (state.isAir() || !stack.isCorrectToolForDrops(state)) {
                continue;
            }

            breakAreaBlock(level, pos, state, player, stack);

            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            if (stack.isEmpty() && !player.getAbilities().instabuild) {
                break;
            }
        }
    }

    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        if (player == null || player.level().isClientSide()) {
            return;
        }
        ItemStack stack = event.getItemStack();
        Grade grade = ModItems.gradeOf(stack);
        if (grade == null || !grade.hasMode()) {
            return;
        }
        AoEMode next = stack.getOrDefault(ModDataComponents.AOE_MODE, AoEMode.CUBIC) == AoEMode.CUBIC
                ? AoEMode.FLAT
                : AoEMode.CUBIC;
        stack.set(ModDataComponents.AOE_MODE, next);
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.sendSystemMessage(Component.translatable(
                    next == AoEMode.CUBIC
                            ? "message.workhand_tools.mode.cubic"
                            : "message.workhand_tools.mode.flat"), true);
        }
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Grade grade = ModItems.gradeOf(stack);
        if (grade == null) {
            return;
        }

        AoEMode mode = grade.hasMode()
                ? stack.getOrDefault(ModDataComponents.AOE_MODE, AoEMode.CUBIC)
                : AoEMode.CUBIC;
        event.getToolTip().add(Component.translatable(areaKey(grade, mode)).withStyle(ChatFormatting.GOLD));

        if (grade.hasMode()) {
            event.getToolTip().add(Component.translatable(
                    mode == AoEMode.CUBIC
                            ? "message.workhand_tools.mode.cubic"
                            : "message.workhand_tools.mode.flat").withStyle(ChatFormatting.GRAY));
            event.getToolTip().add(Component.translatable("tooltip.workhand_tools.right_click"));
        }

        event.getToolTip().add(Component.translatable("tooltip.workhand_tools.hold_shift"));
    }

    private static String areaKey(Grade grade, AoEMode mode) {
        int w = grade.lateralHalf() * 2 + 1;
        int h = grade.height();
        int d = grade.depth(mode);
        String base = "tooltip.workhand_tools.area." + w + "x" + h + "x" + d;
        if (grade.hasMode() && mode == AoEMode.FLAT) {
            base += "_flat";
        }
        return base;
    }

    private void breakAreaBlock(Level level, BlockPos pos, BlockState state, Player player, ItemStack stack) {
        FluidState fluidState = level.getFluidState(pos);
        BlockEntity blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;

        state.getBlock().playerWillDestroy(level, pos, state, player);
        level.levelEvent(2001, pos, Block.getId(state));

        if (!player.getAbilities().instabuild) {
            Block.dropResources(state, level, pos, blockEntity, player, stack);
            int xp = state.getExpDrop(level, pos, blockEntity, player, stack);
            if (xp > 0) {
                player.giveExperiencePoints(xp);
            }
        }

        level.setBlock(pos, fluidState.createLegacyBlock(), 3);
    }
}
