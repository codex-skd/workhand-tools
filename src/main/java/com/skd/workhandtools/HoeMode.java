package com.skd.workhandtools;

import com.mojang.serialization.Codec;

import net.minecraft.util.StringRepresentable;

public enum HoeMode implements StringRepresentable {
    TILL("till"),
    HARVEST("harvest");

    public static final Codec<HoeMode> CODEC = Codec.STRING.xmap(HoeMode::fromName, HoeMode::getSerializedName);

    private final String name;

    HoeMode(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    private static HoeMode fromName(String name) {
        for (HoeMode mode : values()) {
            if (mode.name.equals(name)) {
                return mode;
            }
        }
        return HARVEST; // Default to HARVEST for newly crafted hoes
    }
}