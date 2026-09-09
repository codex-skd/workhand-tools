package com.skd.workhandtools;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class CropHarvestHandler {

    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        if (player == null || player.level().isClientSide()) {
            return;
        }
        ItemStack stack = player.getItemInHand(event.getHand());
        if (!(stack.getItem() instanceof WorkhandHoeItem)) {
            return;
        }

        HoeMode mode = stack.getOrDefault(ModDataComponents.HOE_MODE, HoeMode.HARVEST);
        Level level = player.level();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        if (mode == HoeMode.TILL) {
            // Check if block is tillable (dirt, grass_block, dirt_path, rooted_dirt)
            // and air/non-solid block above
            if (isTillable(state) && isAirOrNonSolidAbove(level, pos)) {
                // Till the block into farmland
if (level instanceof ServerLevel serverLevel) {
                    level.setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState());
                    level.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0f, 1.0f);
                    // Consume durability
                    stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                    player.swing(event.getHand());
                }
            }
            // Hoe is a pure mode tool: always cancel so vanilla never breaks a block
            event.setCanceled(true);
        } else if (mode == HoeMode.HARVEST) {
            // Check if the clicked block is a mature crop/cocoa/nether_wart
            boolean isMature = false;
            if (state.getBlock() instanceof CropBlock) {
                isMature = ((CropBlock) state.getBlock()).isMaxAge(state);
            } else if (state.getBlock() instanceof CocoaBlock) {
                isMature = state.getValue(CocoaBlock.AGE) >= CocoaBlock.MAX_AGE;
            } else if (state.getBlock() instanceof NetherWartBlock) {
                isMature = state.getValue(NetherWartBlock.AGE) >= NetherWartBlock.MAX_AGE;
            }

            if (isMature) {
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
                player.swing(event.getHand());
            }
            // Hoe is a pure mode tool: always cancel so vanilla never breaks a block
            // (immature crops are left untouched and keep growing)
            event.setCanceled(true);
        }
    }

    // The Workhand Hoe's mode is cycled exclusively by the "cycle hoe mode" keybinding (default:
    // right mouse button, see WorkhandToolsClient), not by these events directly. They only
    // suppress the vanilla right-click/use-item interaction (which would otherwise till dirt
    // via HoeItem.useOn) so a right-click never accidentally tills regardless of the current mode
    // - actual tilling/harvesting always goes through onLeftClickBlock below.
    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (isWorkhandHoe(event.getEntity(), event.getHand())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (isWorkhandHoe(event.getEntity(), event.getHand())) {
            event.setCanceled(true);
        }
    }

    private static boolean isWorkhandHoe(Player player, InteractionHand hand) {
        return player != null && player.getItemInHand(hand).getItem() instanceof WorkhandHoeItem;
    }

    // Entry point for the "cycle hoe mode" keybinding (ToggleHoeModePayload handler in
    // WorkhandTools). Looks at both hands since the keybinding doesn't know which one the
    // player intended.
    public static void cycleModeFromKeybind(Player player) {
        InteractionHand hand = player.getMainHandItem().getItem() instanceof WorkhandHoeItem
                ? InteractionHand.MAIN_HAND
                : player.getOffhandItem().getItem() instanceof WorkhandHoeItem ? InteractionHand.OFF_HAND : null;
        if (hand == null) {
            return;
        }
        cycleModeIfHoe(player, player.getItemInHand(hand), hand);
    }

    // Cycles a Workhand Hoe between TILL and HARVEST mode. Returns true if it handled the click
    // (i.e. the stack is a Workhand Hoe and we're server-side), so the caller can cancel the event.
    private static boolean cycleModeIfHoe(Player player, ItemStack stack, InteractionHand hand) {
        if (player == null || player.level().isClientSide()) {
            return false;
        }
        if (!(stack.getItem() instanceof WorkhandHoeItem)) {
            return false;
        }

        HoeMode current = stack.getOrDefault(ModDataComponents.HOE_MODE, HoeMode.HARVEST);
        HoeMode next = (current == HoeMode.TILL) ? HoeMode.HARVEST : HoeMode.TILL;
        stack.set(ModDataComponents.HOE_MODE, next);

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.sendSystemMessage(Component.translatable(
                    next == HoeMode.TILL ? "message.workhand_tools.hoe_mode.till"
                                         : "message.workhand_tools.hoe_mode.harvest"), true);
        }
        player.swing(hand);
        return true;
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!(stack.getItem() instanceof WorkhandHoeItem)) {
            return;
        }
        HoeMode mode = stack.getOrDefault(ModDataComponents.HOE_MODE, HoeMode.HARVEST);
        event.getToolTip().add(Component.translatable(
                mode == HoeMode.TILL ? "tooltip.workhand_tools.hoe_mode.till"
                                     : "tooltip.workhand_tools.hoe_mode.harvest").withStyle(ChatFormatting.GOLD));
        event.getToolTip().add(Component.translatable("tooltip.workhand_tools.right_click"));
        event.getToolTip().add(Component.translatable("tooltip.workhand_tools.hoe_mode.keybind_hint"));
        event.getToolTip().add(Component.translatable("tooltip.workhand_tools.hold_shift"));
    }

    // Helper method to check if a block is tillable by a hoe
    private boolean isTillable(BlockState state) {
        Block block = state.getBlock();
        return block == Blocks.DIRT ||
               block == Blocks.GRASS_BLOCK ||
               block == Blocks.DIRT_PATH ||
               block == Blocks.ROOTED_DIRT;
    }

    // Helper method to check if the block above is air or non-solid (not a liquid that would prevent tilling)
    private boolean isAirOrNonSolidAbove(Level level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);
        return aboveState.isAir() || !aboveState.isSolidRender(level, pos.above());
    }
}