package com.skd.workhandtools;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class AoEPatterns {

    private AoEPatterns() {
    }

    public static List<BlockPos> computePattern(BlockPos center, Grade grade, AoEMode mode,
            Direction digDir, boolean lookingUp) {
        List<BlockPos> positions = new ArrayList<>();
        int half = grade.lateralHalf();
        int depth = grade.depth(mode);

        if (digDir.getAxis() == Direction.Axis.Y) {
            computeVerticalPattern(positions, center, digDir, half, depth);
        } else {
            computeHorizontalPattern(positions, center, digDir, half, depth, grade.height(), lookingUp);
        }
        return positions;
    }

    /**
     * Derives the digging direction from the face of the aimed block that the player is actually
     * looking at (real raycast, not the player's position relative to the block — a lateral stance
     * offset must not flip the direction sideways). The result always points away from the player,
     * deeper into the targeted structure: a side face digs into the wall, the top face (floor) digs
     * down, the bottom face (ceiling) digs up.
     */
    static Direction digDirection(Player player, Level level, BlockPos center) {
        Direction hitFace = rayTraceHitFace(player, level, center)
                .orElseGet(() -> nearestDirection(player.getEyePosition(), center));
        return switch (hitFace) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            default -> hitFace.getOpposite();
        };
    }

    private static Optional<Direction> rayTraceHitFace(Player player, Level level, BlockPos center) {
        Vec3 eye = player.getEyePosition(1.0F);
        Vec3 look = player.getViewVector(1.0F);
        double reach = player.blockInteractionRange() + 1.0D;
        Vec3 end = eye.add(look.scale(reach));
        BlockHitResult hit = level.clip(new ClipContext(eye, end,
                ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
        if (hit.getType() == HitResult.Type.BLOCK && hit.getBlockPos().equals(center)) {
            return Optional.of(hit.getDirection());
        }
        return Optional.empty();
    }

    // Fallback for the rare case the raytrace doesn't land on the same block the break event
    // reports (desync between client aim and the server-processed break). Approximates the hit
    // face from the player's position relative to the block.
    private static Direction nearestDirection(Vec3 eye, BlockPos center) {
        Vec3 block = Vec3.atCenterOf(center);
        return Direction.getNearest(
                (int) Math.round(eye.x - block.x),
                (int) Math.round(eye.y - block.y),
                (int) Math.round(eye.z - block.z),
                Direction.UP);
    }

    private static void computeHorizontalPattern(List<BlockPos> positions, BlockPos center,
            Direction facing, int half, int depth, int height, boolean lookingUp) {
        Direction lateral = facing.getClockWise();
        int[] vertical = verticalOffsets(height, lookingUp);

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int dy : vertical) {
            for (int dx = -half; dx <= half; dx++) {
                for (int dz = 0; dz < depth; dz++) {
                    mutable.set(center).move(Direction.UP, dy).move(lateral, dx).move(facing, dz);
                    positions.add(mutable.immutable());
                }
            }
        }
    }

    private static void computeVerticalPattern(List<BlockPos> positions, BlockPos center,
            Direction verticalDir, int half, int depth) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int d = 0; d < depth; d++) {
            for (int a = -half; a <= half; a++) {
                for (int b = -half; b <= half; b++) {
                    mutable.set(center).move(verticalDir, d).move(Direction.EAST, a).move(Direction.SOUTH, b);
                    positions.add(mutable.immutable());
                }
            }
        }
    }

    static int[] verticalOffsets(int height, boolean lookingUp) {
        if (height == 3) {
            return new int[]{-1, 0, 1};
        }
        if (lookingUp) {
            return new int[]{-2, -1, 0, 1, 2};
        }
        return new int[]{-1, 0, 1, 2, 3};
    }
}
