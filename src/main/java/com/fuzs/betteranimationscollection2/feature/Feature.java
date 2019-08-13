package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.BetterAnimationsCollection2;
import com.fuzs.betteranimationscollection2.handler.CustomRenderingHandler;
import com.fuzs.betteranimationscollection2.helper.ConfigHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.Arrays;

public abstract class Feature<T extends Entity> {

    private final Class<T> entityClazz;
    private final IRenderFactory<? super T> renderFactory;

    private ForgeConfigSpec.BooleanValue enabled;
    private ForgeConfigSpec.BooleanValue forced;
    private ForgeConfigSpec.BooleanValue compatibility;

    public Feature(Class<T> clazz, IRenderFactory<? super T> factory) {
        this.entityClazz = clazz;
        this.renderFactory = factory;
    }

    @SuppressWarnings("unchecked")
    public void register() {

        if (this.isEnabled()) {

            if (ConfigHelper.getConfigBoolean(this.compatibility)) {

                BetterAnimationsCollection2.LOGGER.info("Registering \"" + this.getName() + "\" via custom method");
                CustomRenderingHandler.registerEntityRenderingHandler(this.entityClazz, ((IEntityRenderer<T, EntityModel<T>>) this.renderFactory.createRenderFor(Minecraft.getInstance().getRenderManager())).getEntityModel());

            } else {

                BetterAnimationsCollection2.LOGGER.info("Registering \"" + this.getName() + "\" via default method");
                RenderingRegistry.registerEntityRenderingHandler(this.entityClazz, this.renderFactory);

            }

        }

    }

    public boolean isEnabled() {
        return this.enabled.get() && (ConfigHelper.getConfigBoolean(this.forced)
                || Arrays.stream(this.incompatibleMods()).noneMatch(it -> ModList.get().isLoaded(it)));
    }

    public abstract String getName();

    public abstract String getDescription();

    public boolean hasCompatibility() {
        return true;
    }

    protected String[] incompatibleMods() {
        return new String[0];
    }

    public void setupConfig(ForgeConfigSpec.Builder builder) {

        this.enabled = builder.comment("Is this feature enabled.").define("enabled", true);

        String[] incompatible = this.incompatibleMods();
        if (incompatible.length > 0) {
            this.forced = builder.comment("Enable even if incompatible mods are loaded. Is incompatible with: " + ConfigHelper.arrayToCustomString(incompatible)).define("forced", false);
        }

        if (this.hasCompatibility()) {
            this.compatibility = builder.comment("Apply model in a more mod compatible way.").define("compatibility", false);
        }

    }

}
