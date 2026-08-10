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
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;

public class TreeFellingHandler {

    @SubscribeEvent
    public void onBlockBreak(BreakBlockEvent event) {
        Player player = event.getPlayer();
        if (player == null) {
            return;
        }
        Level level = event.getLevel() instanceof Level l ? l : null;
        if (level == null || level.isClientSide()) {
            return;
        }
        ItemStack stack = player.getMainHandItem();
        if (!MiningHelper.isFellingEnabled(stack)) {
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
        boolean current = Boolean.TRUE.equals(stack.get(ModDataComponents.FELLING_ENABLED));
        boolean next = !current;
        stack.set(ModDataComponents.FELLING_ENABLED, next);
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.sendSystemMessage(Component.translatable(
                    next ? "message.workhand_tools.felling.enabled"
                         : "message.workhand_tools.felling.disabled"), true);
        }
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!MiningHelper.isFellingAxe(stack)) {
            return;
        }
        boolean enabled = Boolean.TRUE.equals(stack.get(ModDataComponents.FELLING_ENABLED));
        event.getToolTip().add(Component.translatable(
                enabled ? "tooltip.workhand_tools.felling.enabled"
                        : "tooltip.workhand_tools.felling.disabled").withStyle(ChatFormatting.GOLD));
        event.getToolTip().add(Component.translatable("tooltip.workhand_tools.right_click"));
    }
}
