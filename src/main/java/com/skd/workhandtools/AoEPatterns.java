package com.skd.workhandtools;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;

public final class AoEPatterns {

    private static final float VERTICAL_THRESHOLD = 60.0F;

    private AoEPatterns() {
    }

    public static List<BlockPos> computePattern(BlockPos center, Player player, Grade grade, AoEMode mode, boolean lookingUp) {
        List<BlockPos> positions = new ArrayList<>();
        int half = grade.lateralHalf();
        int depth = grade.depth(mode);
        float pitch = player.getXRot();

        if (pitch > VERTICAL_THRESHOLD) {
            computeVerticalPattern(positions, center, Direction.DOWN, half, depth);
        } else if (pitch < -VERTICAL_THRESHOLD) {
            computeVerticalPattern(positions, center, Direction.UP, half, depth);
        } else {
            computeHorizontalPattern(positions, center, player, half, depth, grade.height(), lookingUp);
        }
        return positions;
    }

    private static void computeHorizontalPattern(List<BlockPos> positions, BlockPos center, Player player,
            int half, int depth, int height, boolean lookingUp) {
        Direction facing = player.getDirection();
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
        Direction axisA = verticalDir.getAxis() == Direction.Axis.Y ? Direction.EAST : Direction.UP;
        Direction axisB = verticalDir.getAxis() == Direction.Axis.Y ? Direction.SOUTH : Direction.UP;

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int d = 0; d < depth; d++) {
            for (int a = -half; a <= half; a++) {
                for (int b = -half; b <= half; b++) {
                    mutable.set(center).move(verticalDir, d).move(axisA, a).move(axisB, b);
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
