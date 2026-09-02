package com.skd.workhandtools;

import com.mojang.serialization.Codec;

import net.minecraft.util.StringRepresentable;

public enum FellingMode implements StringRepresentable {
    DISABLED("disabled"),
    SIMPLE("simple"),
    COMPOUND("compound");

    public static final Codec<FellingMode> CODEC = Codec.STRING.xmap(FellingMode::fromName, FellingMode::getSerializedName);

    private final String name;

    FellingMode(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    private static FellingMode fromName(String name) {
        for (FellingMode mode : values()) {
            if (mode.name.equals(name)) {
                return mode;
            }
        }
        return DISABLED;
    }
}