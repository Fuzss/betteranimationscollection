package com.fuzs.betteranimationscollection2.feature.core;

import com.fuzs.betteranimationscollection2.BetterAnimationsCollection2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.Arrays;

public abstract class RendererFeature<T extends Entity> extends Feature<T> {

    private final EntityType<T> entityType;
    private final IRenderFactory<? super T> renderFactory;

    private ForgeConfigSpec.BooleanValue forced;

    public RendererFeature(EntityType<T> entityType, IRenderFactory<? super T> factory) {
        this.entityType = entityType;
        this.renderFactory = factory;
    }

    @Override
    protected final void loadModel() {

        BetterAnimationsCollection2.LOGGER.info("Registering \"" + this.getName() + "\" model for vanilla entities");
        RenderingRegistry.registerEntityRenderingHandler(this.entityType, this.renderFactory);
    }

    protected String[] incompatibleMods() {
        return new String[0];
    }

    @Override
    protected final boolean shouldLoad() {

        return super.shouldLoad() && (this.forced != null && this.forced.get()
                || Arrays.stream(this.incompatibleMods()).noneMatch(mod -> ModList.get().isLoaded(mod)));
    }

    public void setupConfig(ForgeConfigSpec.Builder builder) {

        super.setupConfig(builder);

        if (this.incompatibleMods().length > 0) {
            this.forced = builder.comment("Enable even if incompatible mods are loaded. Is incompatible with: " + this.arrayToCustomString(this.incompatibleMods())).define("forced", false);
        }
    }

    private String arrayToCustomString(String[] array) {

        if (array == null || array.length < 1) {

            return "null";
        }

        StringBuilder builder = new StringBuilder();
        for (String s : array) {

            builder.append(s);
            builder.append(", ");
        }

        return builder.substring(0, builder.length() - 2);
    }

}