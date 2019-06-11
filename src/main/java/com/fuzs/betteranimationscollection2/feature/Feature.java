package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.config.ConfigHandler;
import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;

import java.util.Arrays;

public abstract class Feature<T extends Entity> {

    private final Class<T> entityClazz;
    private final IRenderFactory<? super T> renderFactory;

    private boolean enabled;
    private boolean forced;

    public Feature(Class<T> clazz, IRenderFactory<? super T> factory) {
        this.entityClazz = clazz;
        this.renderFactory = factory;
    }

    public void register() {
        if (this.isEnabled()) {
            RenderingRegistry.registerEntityRenderingHandler(this.entityClazz, this.renderFactory);
        }
    }

    public boolean isEnabled() {
        return this.enabled && (this.forced || Arrays.stream(this.incompatibleMods()).noneMatch(Loader::isModLoaded));
    }

    public abstract String getName();

    protected String[] incompatibleMods() {
        return new String[0];
    }

    public void setupConfig() {

        ConfigHandler.config.getCategory(this.getCategory());
        this.enabled = ConfigPropHelper.loadPropBoolean("enabled", this.getCategory(), true, "Is this feature enabled.", true);

        String[] incompatible = this.incompatibleMods();
        if (incompatible.length > 0) {
            this.forced = ConfigPropHelper.loadPropBoolean("forced", this.getCategory(), false, "Enable even if incompatible mods are loaded. Is incompatible with: " + Arrays.toString(incompatible), true);
        }

    }

    protected String getCategory() {
        return Configuration.CATEGORY_GENERAL + Configuration.CATEGORY_SPLITTER + this.getName();
    }

}
