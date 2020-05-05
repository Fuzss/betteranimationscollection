package com.fuzs.betteranimationscollection2.feature.core;

import com.fuzs.betteranimationscollection2.BetterAnimationsCollection2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;

import java.util.function.Supplier;

public abstract class ModelFeature<T extends Entity> extends Feature<T> {

    private final Supplier<? extends EntityModel<T>> entityModel;

    public ModelFeature(Supplier<? extends EntityModel<T>> entityModel) {
        this.entityModel = entityModel;
    }

    @Override
    protected final void loadModel() {

        BetterAnimationsCollection2.LOGGER.info("Registering \"" + this.getName() + "\" model for all entities");
        Minecraft.getInstance().getRenderManager().renderers.values().forEach(renderer -> {

            if (renderer instanceof LivingRenderer) {

                @SuppressWarnings("rawtypes")
                LivingRenderer livingRenderer = ((LivingRenderer<?, ? extends EntityModel<?>>) renderer);
                EntityModel<T> model = this.entityModel.get();
                if (livingRenderer.entityModel.getClass().equals(model.getClass().getSuperclass())) {

                    livingRenderer.entityModel = model;
                }
            }
        });
    }

    @Override
    protected final boolean shouldLoad() {

        return super.shouldLoad();
    }

}