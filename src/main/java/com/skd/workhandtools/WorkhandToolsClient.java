package com.skd.workhandtools;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = WorkhandTools.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = WorkhandTools.MODID, value = Dist.CLIENT)
public class WorkhandToolsClient {
    public WorkhandToolsClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        AreaHighlighter.register();
    }
}
