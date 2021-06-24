package fuzs.betteranimationscollection.feature.core;

import fuzs.betteranimationscollection.BetterAnimationsCollection;
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

        BetterAnimationsCollection.LOGGER.info("Registering \"" + this.getName() + "\" model for all entities");
        Minecraft.getInstance().getEntityRenderDispatcher().renderers.values().forEach(renderer -> {

            if (renderer instanceof LivingRenderer) {

                @SuppressWarnings("rawtypes")
                LivingRenderer livingRenderer = ((LivingRenderer<?, ? extends EntityModel<?>>) renderer);
                EntityModel<T> model = this.entityModel.get();
                if (livingRenderer.model.getClass().equals(model.getClass().getSuperclass())) {

                    livingRenderer.model = model;
                }
            }
        });
    }

    @Override
    protected final boolean shouldLoad() {

        return super.shouldLoad();
    }

}