package com.skd.workhandtools;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
        List<BlockPos> pattern = computePattern(event.getPos(), player, grade, mode, lookingUp);

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
        if (grade == null || !grade.hasMode()) {
            return;
        }
        AoEMode mode = stack.getOrDefault(ModDataComponents.AOE_MODE, AoEMode.CUBIC);
        event.getToolTip().add(Component.translatable(
                mode == AoEMode.CUBIC
                        ? "message.workhand_tools.mode.cubic"
                        : "message.workhand_tools.mode.flat").withStyle(ChatFormatting.GRAY));
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

    private List<BlockPos> computePattern(BlockPos center, Player player, Grade grade, AoEMode mode, boolean lookingUp) {
        List<BlockPos> positions = new ArrayList<>();
        int half = grade.lateralHalf();
        int depth = grade.depth(mode);
        int depthHalf = depth / 2;
        Direction facing = player.getDirection();
        Direction lateral = facing.getClockWise();
        int[] vertical = verticalOffsets(grade.height(), lookingUp);

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int dy : vertical) {
            for (int dx = -half; dx <= half; dx++) {
                for (int dz = -depthHalf; dz <= depthHalf; dz++) {
                    mutable.set(center).move(Direction.UP, dy).move(lateral, dx).move(facing, dz);
                    positions.add(mutable.immutable());
                }
            }
        }
        return positions;
    }

    private int[] verticalOffsets(int height, boolean lookingUp) {
        if (height == 3) {
            return new int[]{-1, 0, 1};
        }
        if (lookingUp) {
            return new int[]{-2, -1, 0, 1, 2};
        }
        return new int[]{-1, 0, 1, 2, 3};
    }
}
