package com.skd.workhandtools;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.common.NeoForge;

public final class AreaHighlighter {

    private static boolean registered;

    @SubscribeEvent
    public void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || player.isShiftKeyDown()) {
            return;
        }

        ItemStack stack = player.getMainHandItem();
        Grade grade = ModItems.gradeOf(stack);
        boolean isHoe = stack.getItem() instanceof WorkhandHoeItem;
        
        // For graded tools, we need a grade. For hoes, we need to be pointing at a mature harvestable block AND be in HARVEST mode.
        if (grade == null && !isHoe) {
            return;
        }
        
        // For hoes, only show preview when in HARVEST mode
        if (isHoe) {
            HoeMode mode = stack.getOrDefault(ModDataComponents.HOE_MODE, HoeMode.HARVEST);
            if (mode != HoeMode.HARVEST) {
                return; // Don't show preview in TILL mode
            }
        }

        HitResult hit = mc.hitResult;
        if (!(hit instanceof BlockHitResult blockHit) || hit.getType() == HitResult.Type.MISS) {
            return;
        }

        BlockPos target = blockHit.getBlockPos();
        Level level = player.level();
        
        // For graded tools, check if correct tool for drops. For hoes, check if pointing at mature harvestable block.
        if (grade != null) {
            if (!stack.isCorrectToolForDrops(level.getBlockState(target))) {
                return;
            }
        } else if (isHoe) {
            // Check if pointing at a mature crop/cocoa/nether_wart block
            BlockState targetState = level.getBlockState(target);
            boolean isTargetMature = false;
            if (targetState.getBlock() instanceof CropBlock) {
                isTargetMature = ((CropBlock) targetState.getBlock()).isMaxAge(targetState);
            } else if (targetState.getBlock() instanceof CocoaBlock) {
                isTargetMature = targetState.getValue(CocoaBlock.AGE) >= CocoaBlock.MAX_AGE;
            } else if (targetState.getBlock() instanceof NetherWartBlock) {
                isTargetMature = targetState.getValue(NetherWartBlock.AGE) >= NetherWartBlock.MAX_AGE;
            }
            
            if (!isTargetMature) {
                return;
            }
        }

        // For graded tools, use existing pattern logic. For hoes, compute square pattern at same Y level.
        List<BlockPos> pattern;
        if (grade != null) {
            // Existing logic for graded tools
            AoEMode mode = stack.getOrDefault(ModDataComponents.AOE_MODE, grade.hasMode() ? AoEMode.CUBIC : AoEMode.FLAT);
            if (mode == AoEMode.DISABLED) {
                pattern = List.of(target);
            } else {
                boolean lookingUp = player.getXRot() < Config.PITCH_THRESHOLD_DEGREES.get();
                Direction digDir = AoEPatterns.digDirection(player, level, target);
                pattern = AoEPatterns.computePattern(target, grade, mode, digDir, lookingUp);
            }
        } else if (isHoe) {
            // Logic for Workhand Hoes: square area at same Y level, only mature blocks
            int lateralHalf = stack.getItem() == ModItems.DIAMOND_WORKHAND_HOE.get() ? 2 : 1;
            pattern = new ArrayList<>();
            
            for (int dx = -lateralHalf; dx <= lateralHalf; dx++) {
                for (int dz = -lateralHalf; dz <= lateralHalf; dz++) {
                    BlockPos checkPos = target.offset(dx, 0, dz);
                    if (!level.isLoaded(checkPos)) {
                        continue;
                    }
                    BlockState checkState = level.getBlockState(checkPos);
                    
                    // Check if this block is a mature crop/cocoa/nether_wart
                    boolean isCheckMature = false;
                    if (checkState.getBlock() instanceof CropBlock) {
                        isCheckMature = ((CropBlock) checkState.getBlock()).isMaxAge(checkState);
                    } else if (checkState.getBlock() instanceof CocoaBlock) {
                        isCheckMature = checkState.getValue(CocoaBlock.AGE) >= CocoaBlock.MAX_AGE;
                    } else if (checkState.getBlock() instanceof NetherWartBlock) {
                        isCheckMature = checkState.getValue(NetherWartBlock.AGE) >= NetherWartBlock.MAX_AGE;
                    }
                    
                    if (isCheckMature) {
                        pattern.add(checkPos);
                    }
                }
            }
        } else {
            // Should not happen due to earlier check, but just in case
            return;
        }

        if (pattern.isEmpty()) {
            return;
        }

        PoseStack poseStack = event.getPoseStack();
        Camera camera = mc.gameRenderer.getMainCamera();
        Vec3 camPos = camera.getPosition();
        MultiBufferSource.BufferSource buffers = mc.renderBuffers().bufferSource();

        poseStack.pushPose();
        poseStack.translate(-camPos.x, -camPos.y, -camPos.z);

        for (BlockPos pos : pattern) {
            BlockState state = level.getBlockState(pos);
            if (state.isAir() || (grade != null && !stack.isCorrectToolForDrops(state))) {
                continue;
            }
            // For hoes, we already checked maturity when building the pattern, so no need to re-check
            // but we should still check if it's correct tool for drops (though for hoes it's always correct for mature crops)
            AABB aabb = new AABB(pos).inflate(0.002);
            LevelRenderer.renderLineBox(poseStack, buffers.getBuffer(RenderType.lines()), aabb, 1.0F, 1.0F, 1.0F, 1.0F);
        }

        poseStack.popPose();
        buffers.endBatch(RenderType.lines());
    }

    public static void register() {
        if (registered) {
            return;
        }
        registered = true;
        NeoForge.EVENT_BUS.register(new AreaHighlighter());
    }
}
