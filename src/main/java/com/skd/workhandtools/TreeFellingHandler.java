package com.skd.workhandtools;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class TreeFellingHandler {

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (player == null) {
            return;
        }
        Level level = event.getLevel() instanceof Level l ? l : null;
        if (level == null || level.isClientSide()) {
            return;
        }
        ItemStack stack = player.getMainHandItem();
        if (MiningHelper.fellingModeOf(stack) == FellingMode.DISABLED) {
            return;
        }
        BlockState state = event.getState();
        MiningHelper.fellTree(level, event.getPos(), state, player, stack);
    }

    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        if (player == null || player.level().isClientSide()) {
            return;
        }
        ItemStack stack = event.getItemStack();
        if (!MiningHelper.isFellingAxe(stack)) {
            return;
        }
        FellingMode current = stack.getOrDefault(ModDataComponents.FELLING_MODE, FellingMode.DISABLED);
        FellingMode next;
        if (current == FellingMode.DISABLED) {
            next = FellingMode.SIMPLE;
        } else if (current == FellingMode.SIMPLE) {
            next = FellingMode.COMPOUND;
        } else { // current == COMPOUND
            next = FellingMode.DISABLED;
        }
        stack.set(ModDataComponents.FELLING_MODE, next);
        if (player instanceof ServerPlayer serverPlayer) {
            String messageKey;
            if (next == FellingMode.SIMPLE) {
                messageKey = "message.workhand_tools.felling.simple";
            } else if (next == FellingMode.COMPOUND) {
                messageKey = "message.workhand_tools.felling.compound";
            } else {
                messageKey = "message.workhand_tools.felling.disabled";
            }
            serverPlayer.sendSystemMessage(Component.translatable(messageKey), true);
        }
        player.swing(event.getHand());
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!MiningHelper.isFellingAxe(stack)) {
            return;
        }
        FellingMode mode = stack.getOrDefault(ModDataComponents.FELLING_MODE, FellingMode.DISABLED);
        String tooltipKey;
        if (mode == FellingMode.SIMPLE) {
            tooltipKey = "tooltip.workhand_tools.felling.simple";
        } else if (mode == FellingMode.COMPOUND) {
            tooltipKey = "tooltip.workhand_tools.felling.compound";
        } else {
            tooltipKey = "tooltip.workhand_tools.felling.disabled";
        }
        event.getToolTip().add(Component.translatable(tooltipKey).withStyle(ChatFormatting.GOLD));
        event.getToolTip().add(Component.translatable("tooltip.workhand_tools.right_click"));
    }
}
