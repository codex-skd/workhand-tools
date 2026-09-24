package com.skd.workhandtools;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
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
        // Sneaking suppresses the felling cascade (mirrors AoEMiningHandler): the targeted log
        // is still broken by vanilla, but no connected logs are taken down.
        if (player.isShiftKeyDown()) {
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
        if (MiningHelper.isFellingAxe(event.getItemStack())) {
            event.setCanceled(true);
        }
    }

    // Unlike the hoe handler, this handler does NOT cancel PlayerInteractEvent.RightClickBlock,
    // so vanilla AxeItem.useOn (log stripping / copper scraping) still runs independently on a
    // targeted block. The keybinding fires purely on input state, so right-clicking a strippable
    // log with a felling axe will now also toggle felling mode alongside the vanilla strip.
    public static void cycleModeFromKeybind(ServerPlayer player) {
        ItemStack stack = player.getMainHandItem();
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
        String messageKey;
        if (next == FellingMode.SIMPLE) {
            messageKey = "message.workhand_tools.felling.simple";
        } else if (next == FellingMode.COMPOUND) {
            messageKey = "message.workhand_tools.felling.compound";
        } else {
            messageKey = "message.workhand_tools.felling.disabled";
        }
        player.sendSystemMessage(Component.translatable(messageKey), true);
        player.swing(InteractionHand.MAIN_HAND);
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
        event.getToolTip().add(Component.translatable("tooltip.workhand_tools.hoe_mode.keybind_hint"));
        event.getToolTip().add(Component.translatable("tooltip.workhand_tools.hold_shift"));
    }
}
