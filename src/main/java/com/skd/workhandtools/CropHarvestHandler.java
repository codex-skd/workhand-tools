package com.skd.workhandtools;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class CropHarvestHandler {

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        if (player == null || player.level().isClientSide()) {
            return;
        }
        ItemStack stack = player.getItemInHand(event.getHand());
        if (!(stack.getItem() instanceof WorkhandHoeItem)) {
            return;
        }

        // If harvest effect is disabled, let vanilla hoe behavior proceed
        if (!stack.getOrDefault(ModDataComponents.HARVEST_ENABLED, true)) {
            return;
        }

        Level level = player.level();

        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        
        // Check if the clicked block is a mature crop/cocoa/nether_wart
        boolean isMature = false;
        if (state.getBlock() instanceof CropBlock) {
            isMature = ((CropBlock) state.getBlock()).isMaxAge(state);
        } else if (state.getBlock() instanceof CocoaBlock) {
            isMature = state.getValue(CocoaBlock.AGE) >= CocoaBlock.MAX_AGE;
        } else if (state.getBlock() instanceof NetherWartBlock) {
            isMature = state.getValue(NetherWartBlock.AGE) >= NetherWartBlock.MAX_AGE;
        }

        if (!isMature) {
            // Let vanilla hoe behavior proceed (e.g., tilling dirt)
            return;
        }

        // Determine harvest radius from the hoe
        int lateralHalf = stack.getItem() == ModItems.DIAMOND_WORKHAND_HOE.get() ? 2 : 1;

        // Harvest in a square area at the same Y level
        for (int dx = -lateralHalf; dx <= lateralHalf; dx++) {
            for (int dz = -lateralHalf; dz <= lateralHalf; dz++) {
                BlockPos targetPos = pos.offset(dx, 0, dz);
                if (!level.isLoaded(targetPos)) {
                    continue;
                }
                BlockState targetState = level.getBlockState(targetPos);
                
                // Check if this block is also a mature crop/cocoa/nether_wart (any type)
                boolean isTargetMature = false;
                if (targetState.getBlock() instanceof CropBlock) {
                    isTargetMature = ((CropBlock) targetState.getBlock()).isMaxAge(targetState);
                } else if (targetState.getBlock() instanceof CocoaBlock) {
                    isTargetMature = targetState.getValue(CocoaBlock.AGE) >= CocoaBlock.MAX_AGE;
                } else if (targetState.getBlock() instanceof NetherWartBlock) {
                    isTargetMature = targetState.getValue(NetherWartBlock.AGE) >= NetherWartBlock.MAX_AGE;
                }

                if (isTargetMature) {
                    // Harvest the block
                    if (level instanceof ServerLevel serverLevel) {
                        List<ItemStack> drops = Block.getDrops(targetState, serverLevel, targetPos, null, player, stack);
                        for (ItemStack drop : drops) {
                            Block.popResource(level, targetPos, drop);
                        }
                        targetState.spawnAfterBreak(serverLevel, targetPos, stack, true);
                    }

                    // Reset the block's age property to 0
                    if (targetState.getBlock() instanceof CropBlock) {
                        Property<?> ageProperty = targetState.getBlock().getStateDefinition().getProperty("age");
                        if (ageProperty instanceof IntegerProperty) {
                            level.setBlockAndUpdate(targetPos, targetState.setValue((IntegerProperty) ageProperty, 0));
                        }
                    } else if (targetState.getBlock() instanceof CocoaBlock) {
                        level.setBlockAndUpdate(targetPos, targetState.setValue(CocoaBlock.AGE, 0));
                    } else if (targetState.getBlock() instanceof NetherWartBlock) {
                        level.setBlockAndUpdate(targetPos, targetState.setValue(NetherWartBlock.AGE, 0));
                    }
                    
                    // Consume durability
                    stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                    
                    if (stack.isEmpty() && !player.getAbilities().instabuild) {
                        event.setCanceled(true);
                        return;
                    }
                }
            }
        }

        // Cancel the event so nothing else happens
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        if (player == null || player.level().isClientSide()) {
            return;
        }
        ItemStack stack = event.getItemStack();
        if (!(stack.getItem() instanceof WorkhandHoeItem)) {
            return;
        }

        boolean current = stack.getOrDefault(ModDataComponents.HARVEST_ENABLED, true);
        boolean next = !current;
        stack.set(ModDataComponents.HARVEST_ENABLED, next);
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.sendSystemMessage(Component.translatable(
                    next ? "message.workhand_tools.harvest.enabled"
                         : "message.workhand_tools.harvest.disabled"), true);
        }
        player.swing(event.getHand());
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!(stack.getItem() instanceof WorkhandHoeItem)) {
            return;
        }
        boolean enabled = stack.getOrDefault(ModDataComponents.HARVEST_ENABLED, true);
        event.getToolTip().add(Component.translatable(
                enabled ? "tooltip.workhand_tools.harvest.enabled"
                        : "tooltip.workhand_tools.harvest.disabled").withStyle(ChatFormatting.GOLD));
        event.getToolTip().add(Component.translatable("tooltip.workhand_tools.right_click"));
        event.getToolTip().add(Component.translatable("tooltip.workhand_tools.hold_shift"));
    }
}