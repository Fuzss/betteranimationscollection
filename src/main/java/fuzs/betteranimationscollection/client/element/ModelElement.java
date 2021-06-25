package fuzs.betteranimationscollection.client.element;

import com.google.common.collect.Maps;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.puzzleslib.element.AbstractElement;
import fuzs.puzzleslib.element.side.IClientElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class ModelElement extends AbstractElement implements IClientElement {

    private final Minecraft mc = Minecraft.getInstance();

    private final Map<LivingRenderer<?, ?>, EntityModel<?>> rendererToOrigModel = Maps.newHashMap();

    protected abstract EntityModel<? extends LivingEntity> getEntityModel();

    @Override
    public void loadClient() {

        // find all renderers which normally use the super class of our model, so we can exchange them
        this.switchModels();
    }

    @Override
    public void unloadClient() {

        this.restoreModels();
    }

    private <T extends LivingEntity, S extends EntityModel<T>> void switchModels() {

        this.mc.getEntityRenderDispatcher().renderers.values().forEach(renderer -> {

            if (renderer instanceof LivingRenderer) {

                LivingRenderer<T, S> livingRenderer = ((LivingRenderer<T, S>) renderer);
                S model = (S) this.getEntityModel();
                if (livingRenderer.model.getClass().isInstance(model)) {

                    BetterAnimationsCollection.LOGGER.info("Replaced {} with {} for {} in EntityRenderDispatcher", livingRenderer.getModel().getClass().getSimpleName(), model.getClass().getSimpleName(), livingRenderer.getClass().getSimpleName());
                    this.rendererToOrigModel.putIfAbsent(livingRenderer, livingRenderer.getModel());
                    livingRenderer.model = model;
                }
            }
        });
    }

    @SuppressWarnings("RedundantCast")
    private <T extends LivingEntity, S extends EntityModel<T>> void restoreModels() {

        // cast is not redundant
        this.rendererToOrigModel.forEach((key, value) -> {

            ((LivingRenderer<T, S>) key).model = (S) value;
            BetterAnimationsCollection.LOGGER.info("Restored {} for {} in EntityRenderDispatcher", value.getClass().getSimpleName(), key.getClass().getSimpleName());
        });
    }

}
