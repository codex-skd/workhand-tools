package com.skd.workhandtools;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class VeinMiningHandler {

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
        if (!MiningHelper.isVeinPickaxe(stack)) {
            return;
        }
        BlockState state = event.getState();
        if (!MiningHelper.isOre(state)) {
            return;
        }
        MiningHelper.mineVein(level, event.getPos(), state, player, stack);
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (MiningHelper.isVeinPickaxe(stack)) {
            // hold_shift is already added by AoEMiningHandler.onTooltip for any graded tool
            // (vein pickaxes always have a grade) — don't duplicate it here.
            event.getToolTip().add(Component.translatable("tooltip.workhand_tools.vein_mining"));
        }
    }
}
