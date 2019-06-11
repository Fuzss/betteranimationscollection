package com.fuzs.betteranimationscollection2.config;

import com.fuzs.betteranimationscollection2.BetterAnimationsCollection2;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public  class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                BetterAnimationsCollection2.MODID, false, false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }

}
