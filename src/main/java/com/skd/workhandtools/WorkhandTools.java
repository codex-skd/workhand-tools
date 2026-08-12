package com.skd.workhandtools;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(WorkhandTools.MODID)
public class WorkhandTools {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "workhand_tools";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Items which will all be registered under the "workhand_tools" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "workhand_tools" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creative tab for the mod's tools. Order follows docs/DESIGN_WORKHAND_TOOLS.md: pickaxes first,
    // then shovels; within each tool by material progression (Wood -> Netherite) and by grade ascending.
    // Icon: iron_workhand_pickaxe (grade 1).
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WORKHAND_TAB = CREATIVE_MODE_TABS.register("workhand_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.workhand_tools"))
            .icon(() -> ModItems.IRON_WORKHAND_PICKAXE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (DeferredHolder<Item, ? extends Item> holder : ModItems.PICKAXES) {
                    output.accept(holder.get());
                }
                for (DeferredHolder<Item, ? extends Item> holder : ModItems.SHOVELS) {
                    output.accept(holder.get());
                }
                for (DeferredHolder<Item, ? extends Item> holder : ModItems.AXES) {
                    output.accept(holder.get());
                }
                output.accept(ModItems.ROBUST_STICK.get());
                output.accept(ModItems.ROBUST_STICK.get());
                output.accept(ModItems.TAPE_MEASURE.get());
            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public WorkhandTools(IEventBus modEventBus, ModContainer modContainer) {
        // Force ModItems static initialization BEFORE the item RegisterEvent fires. ModItems'
        // static fields call ITEMS.register(...) to populate the DeferredRegister; without this
        // eager touch, the only references to ModItems are the creative-tab lambdas below, which
        // are evaluated after the item registry is already processed and the tools never register.
        ModItems.PICKAXES.isEmpty();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Registers to the mod event bus
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        ModDataComponents.DATA_COMPONENT_TYPES.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new AoEMiningHandler());
        NeoForge.EVENT_BUS.register(new VeinMiningHandler());
        NeoForge.EVENT_BUS.register(new TreeFellingHandler());
        NeoForge.EVENT_BUS.register(new LeafDecayHandler());

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ModItems::buildLookups);
        LOGGER.info("Workhand Tools common setup complete");
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Workhand Tools ready on server starting");
    }
}
