package com.skd.workhandtools;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

// No @EventBusSubscriber here: this class has no static @SubscribeEvent methods (its only
// listener, registerBlockEntityRenderers, is wired manually via modEventBus.addListener below).
// With the annotation, AutomaticEventSubscriber calls bus.register(WorkhandToolsClient.class),
// which crashes mod loading with "has no @SubscribeEvent methods, but register was called anyway".
@Mod(value = WorkhandTools.MODID, dist = Dist.CLIENT)
public class WorkhandToolsClient {
    // Defaults to right mouse button (same physical input as vanilla's "use item" key), so it
    // works out of the box exactly like before, but is now reassignable in Options -> Controls ->
    // Workhand Tools. CropHarvestHandler cancels the vanilla right-click/use-item interaction for
    // a held Workhand Hoe so it doesn't till dirt underneath this; the actual mode cycling now
    // happens only here, driven by the keybinding, instead of racing the vanilla useOn chain.
    public static final KeyMapping CYCLE_HOE_MODE_KEY = new KeyMapping(
            "key.workhand_tools.cycle_hoe_mode",
            InputConstants.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_RIGHT,
            "key.categories.workhand_tools");

    public static final KeyMapping CYCLE_AOE_MODE_KEY = new KeyMapping(
            "key.workhand_tools.cycle_aoe_mode",
            InputConstants.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_RIGHT,
            "key.categories.workhand_tools");

    public static final KeyMapping CYCLE_FELLING_MODE_KEY = new KeyMapping(
            "key.workhand_tools.cycle_felling_mode",
            InputConstants.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_RIGHT,
            "key.categories.workhand_tools");

    public WorkhandToolsClient(IEventBus modEventBus, ModContainer container) {
        AreaHighlighter.register();
        ChunkAnchorBorderRenderer.register();
        TapeMeasureHandler.register();
        container.registerConfig(ModConfig.Type.CLIENT, TapeMeasureConfig.clientSpec, WorkhandTools.MODID + "/" + WorkhandTools.MODID + "-client.toml");
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        modEventBus.addListener(WorkhandToolsClient::registerBlockEntityRenderers);
        modEventBus.addListener(WorkhandToolsClient::registerKeyMappings);
        NeoForge.EVENT_BUS.addListener(WorkhandToolsClient::onClientTick);
    }

    private static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlocks.CHUNK_ANCHOR_BLOCK_ENTITY.get(), ChunkAnchorRenderer::new);
    }

    private static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(CYCLE_HOE_MODE_KEY);
        event.register(CYCLE_AOE_MODE_KEY);
        event.register(CYCLE_FELLING_MODE_KEY);
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) {
            return;
        }
        while (CYCLE_HOE_MODE_KEY.consumeClick()) {
            PacketDistributor.sendToServer(new ToggleHoeModePayload());
        }
        while (CYCLE_AOE_MODE_KEY.consumeClick()) {
            PacketDistributor.sendToServer(new ToggleAoEModePayload());
        }
        while (CYCLE_FELLING_MODE_KEY.consumeClick()) {
            PacketDistributor.sendToServer(new ToggleFellingModePayload());
        }
    }
}
