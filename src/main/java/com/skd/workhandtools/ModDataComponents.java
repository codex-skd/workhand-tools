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

    // Mode for tree felling on the workhand axes: DISABLED, SIMPLE, or COMPOUND. Defaults to DISABLED.
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FellingMode>> FELLING_MODE =
            DATA_COMPONENT_TYPES.register("felling_mode", () -> DataComponentType.<FellingMode>builder()
                    .persistent(FellingMode.CODEC)
                    .networkSynchronized(NeoForgeStreamCodecs.enumCodec(FellingMode.class))
                    .build());

    // Mode for workhand hoes: TILL or HARVEST. Defaults to HARVEST for newly crafted hoes.
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<HoeMode>> HOE_MODE =
            DATA_COMPONENT_TYPES.register("hoe_mode", () -> DataComponentType.<HoeMode>builder()
                    .persistent(HoeMode.CODEC)
                    .networkSynchronized(NeoForgeStreamCodecs.enumCodec(HoeMode.class))
                    .build());

    private ModDataComponents() {
    }
}
