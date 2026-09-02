package com.skd.workhandtools;

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
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class AoEMiningHandler {

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
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

        AoEMode mode = stack.getOrDefault(ModDataComponents.AOE_MODE, defaultModeFor(grade));
        if (mode == AoEMode.DISABLED) {
            return;
        }
        boolean lookingUp = player.getXRot() < Config.PITCH_THRESHOLD_DEGREES.get();
        Direction digDir = AoEPatterns.digDirection(player, level, event.getPos());
        List<BlockPos> pattern = AoEPatterns.computePattern(event.getPos(), grade, mode, digDir, lookingUp);

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

            MiningHelper.breakBlock(level, pos, state, player, stack);

            // Vein mining: if the AoE area breaks an ore block and we're holding a vein pickaxe,
            // also break all connected ores of the same type.
            if (MiningHelper.isOre(state)) {
                MiningHelper.mineVein(level, pos, state, player, stack);
            }

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
        if (grade == null) {
            return;
        }

        AoEMode current = stack.getOrDefault(ModDataComponents.AOE_MODE, defaultModeFor(grade));
        AoEMode next;
        if (grade == Grade.KENNESTROYER) {
            // 5 modes: DISABLED -> FLAT_3 -> CUBIC_3 -> FLAT_5 -> CUBIC_5 -> DISABLED
            switch (current) {
                case DISABLED -> next = AoEMode.FLAT_3;
                case FLAT_3 -> next = AoEMode.CUBIC_3;
                case CUBIC_3 -> next = AoEMode.FLAT_5;
                case FLAT_5 -> next = AoEMode.CUBIC_5;
                case CUBIC_5 -> next = AoEMode.DISABLED;
                default -> next = AoEMode.DISABLED;
            }
        } else if (grade.hasMode()) {
            if (current == AoEMode.DISABLED) {
                next = AoEMode.FLAT;
            } else if (current == AoEMode.FLAT) {
                next = AoEMode.CUBIC;
            } else { // current == CUBIC
                next = AoEMode.DISABLED;
            }
        } else {
            next = (current == AoEMode.DISABLED) ? AoEMode.FLAT : AoEMode.DISABLED;
        }
        stack.set(ModDataComponents.AOE_MODE, next);
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.sendSystemMessage(Component.translatable(modeMessageKey(next)), true);
        }
        player.swing(event.getHand());
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Grade grade = ModItems.gradeOf(stack);
        if (grade == null) {
            return;
        }

        AoEMode mode = stack.getOrDefault(ModDataComponents.AOE_MODE, defaultModeFor(grade));
        if (mode == AoEMode.DISABLED) {
            event.getToolTip().add(Component.translatable("tooltip.workhand_tools.mode.disabled").withStyle(ChatFormatting.GRAY));
            event.getToolTip().add(Component.translatable("tooltip.workhand_tools.right_click"));
        } else {
            event.getToolTip().add(Component.translatable(areaKey(grade, mode)).withStyle(ChatFormatting.GOLD));
            if (grade.hasMode()) {
                event.getToolTip().add(Component.translatable(modeMessageKey(mode)).withStyle(ChatFormatting.GRAY));
            }
            event.getToolTip().add(Component.translatable("tooltip.workhand_tools.right_click"));
        }

        event.getToolTip().add(Component.translatable("tooltip.workhand_tools.hold_shift"));
    }

    // Kennestroyer's default (freshly crafted, no data component set yet) must be DISABLED so the
    // right-click cycle starts at the beginning (DISABLED -> FLAT_3 -> ... -> CUBIC_5). Grade 2/4
    // tools keep their existing default of CUBIC (only two real states, Cubic/Flat).
    private static AoEMode defaultModeFor(Grade grade) {
        if (grade == Grade.KENNESTROYER) {
            return AoEMode.DISABLED;
        }
        return grade.hasMode() ? AoEMode.CUBIC : AoEMode.FLAT;
    }

    private static String modeMessageKey(AoEMode mode) {
        return switch (mode) {
            case CUBIC -> "message.workhand_tools.mode.cubic";
            case FLAT -> "message.workhand_tools.mode.flat";
            case FLAT_3 -> "message.workhand_tools.mode.flat_3";
            case CUBIC_3 -> "message.workhand_tools.mode.cubic_3";
            case FLAT_5 -> "message.workhand_tools.mode.flat_5";
            case CUBIC_5 -> "message.workhand_tools.mode.cubic_5";
            case DISABLED -> "message.workhand_tools.mode.disabled";
        };
    }

    private static String areaKey(Grade grade, AoEMode mode) {
        int w = grade.lateralHalfForMode(mode) * 2 + 1;
        int h = grade.heightForMode(mode);
        int d = grade.depth(mode);
        String base = "tooltip.workhand_tools.area." + w + "x" + h + "x" + d;
        if (grade.hasMode() && (mode == AoEMode.FLAT || mode == AoEMode.FLAT_3 || mode == AoEMode.FLAT_5)) {
            base += "_flat";
        }
        return base;
    }
}
