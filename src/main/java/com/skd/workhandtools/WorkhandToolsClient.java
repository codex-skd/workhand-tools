package com.skd.workhandtools;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.bus.api.SubscribeEvent;

@Mod(value = WorkhandTools.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = WorkhandTools.MODID, value = Dist.CLIENT)
public class WorkhandToolsClient {
    public WorkhandToolsClient(IEventBus modEventBus, ModContainer container) {
        AreaHighlighter.register();
        ChunkAnchorBorderRenderer.register();
        TapeMeasureHandler.register();
        container.registerConfig(ModConfig.Type.CLIENT, TapeMeasureConfig.clientSpec, WorkhandTools.MODID + "/" + WorkhandTools.MODID + "-client.toml");
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        modEventBus.addListener(WorkhandToolsClient::registerBlockEntityRenderers);
    }

    private static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlocks.CHUNK_ANCHOR_BLOCK_ENTITY.get(), ChunkAnchorRenderer::new);
    }
}
