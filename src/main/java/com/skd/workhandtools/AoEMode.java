package com.skd.workhandtools;

import com.mojang.serialization.Codec;

import net.minecraft.util.StringRepresentable;

public enum AoEMode implements StringRepresentable {
    CUBIC("cubic"),
    FLAT("flat"),
    DISABLED("disabled");

    public static final Codec<AoEMode> CODEC = Codec.STRING.xmap(AoEMode::fromName, AoEMode::getSerializedName);

    private final String name;

    AoEMode(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    private static AoEMode fromName(String name) {
        for (AoEMode mode : values()) {
            if (mode.name.equals(name)) {
                return mode;
            }
        }
        return CUBIC;
    }
}
