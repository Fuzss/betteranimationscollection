package com.fuzs.betteranimationscollection2.config;

import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

public class GuiFactory implements IModGuiFactory {

    public void initialize(Minecraft minecraftInstance) {
    }

    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    public boolean hasConfigGui() {
        return true;
    }

    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new ConfigGui(parentScreen);
    }
}
