package com.fuzs.betteranimationscollection2.helper;

import java.util.Set;

import com.fuzs.betteranimationscollection2.BetterAnimationsCollection2;
import com.fuzs.betteranimationscollection2.handler.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

@SuppressWarnings("unused")
public class GuiFactory implements IModGuiFactory {

    public void initialize(Minecraft minecraftInstance) {
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return ConfigGui.class;
    }

    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

    public static class ConfigGui extends GuiConfig {

        public ConfigGui(GuiScreen parentScreen) {
            super(parentScreen, new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                    BetterAnimationsCollection2.MODID, false, false,
                    GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
        }

    }

}
