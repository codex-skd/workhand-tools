package com.skd.workhandtools;

import com.mojang.serialization.Codec;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, WorkhandTools.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<AoEMode>> AOE_MODE =
            DATA_COMPONENT_TYPES.register("aoe_mode", () -> DataComponentType.<AoEMode>builder()
                    .persistent(AoEMode.CODEC)
                    .networkSynchronized(NeoForgeStreamCodecs.enumCodec(AoEMode.class))
                    .build());

    // Toggle for tree felling on the workhand axes. Absent/false = felling disabled.
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> FELLING_ENABLED =
            DATA_COMPONENT_TYPES.register("felling_enabled", () -> DataComponentType.<Boolean>builder()
                    .persistent(Codec.BOOL)
                    .networkSynchronized(ByteBufCodecs.BOOL)
                    .build());

    private ModDataComponents() {
    }
}
