package com.skd.workhandtools;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
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

    private ModDataComponents() {
    }
}
