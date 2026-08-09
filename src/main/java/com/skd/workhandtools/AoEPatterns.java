package com.skd.workhandtools;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
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
     * Derives the digging direction from the face of the aimed block that the player is looking at:
     * a side face digs into the wall, the top face (floor) digs down, the bottom face (ceiling) digs up.
     */
    static Direction digDirection(Player player, BlockPos center) {
        Vec3 eye = player.getEyePosition();
        Vec3 block = Vec3.atCenterOf(center);
        Direction hitFace = Direction.getNearest(
                (int) Math.round(eye.x - block.x),
                (int) Math.round(eye.y - block.y),
                (int) Math.round(eye.z - block.z),
                Direction.UP);
        return switch (hitFace) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            default -> hitFace;
        };
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
