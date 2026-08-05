package com.skd.workhandtools;

public enum Grade {
    GRADE_1(1, 3, 1, false),
    GRADE_2(1, 3, 3, true),
    GRADE_3(2, 5, 1, false),
    GRADE_4(2, 5, 5, true);

    private final int lateralHalf;
    private final int height;
    private final int depthCubic;
    private final boolean hasMode;

    Grade(int lateralHalf, int height, int depthCubic, boolean hasMode) {
        this.lateralHalf = lateralHalf;
        this.height = height;
        this.depthCubic = depthCubic;
        this.hasMode = hasMode;
    }

    public int lateralHalf() {
        return lateralHalf;
    }

    public int height() {
        return height;
    }

    public boolean hasMode() {
        return hasMode;
    }

    public int depth(AoEMode mode) {
        return mode == AoEMode.CUBIC ? depthCubic : 1;
    }
}
