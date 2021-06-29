package fuzs.betteranimationscollection.client.element;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.puzzleslib.element.AbstractElement;
import fuzs.puzzleslib.element.side.IClientElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public abstract class ModelElement extends AbstractElement implements IClientElement {

    private final Minecraft mc = Minecraft.getInstance();
    private final Map<LivingRenderer<?, ?>, EntityModel<?>> rendererToOrigModel = Maps.newHashMap();
    private final List<LayerTransformer<?, ?>> layerTransformers = Lists.newArrayList();

    @Override
    public void loadClient() {

        // find all renderers which normally use the super class of our model, so we can exchange them
        this.switchModels();
    }

    @Override
    public void unloadClient() {

        this.restoreModels();
    }

    protected abstract EntityModel<? extends LivingEntity> getEntityModel();

    protected final <T extends Entity, M extends EntityModel<T>> void addLayerTransformer(Predicate<LayerRenderer<T, M>> filter, Consumer<LayerRenderer<T, M>> setter) {

        this.layerTransformers.add(new LayerTransformer<>(filter, setter));
    }

    private <T extends LivingEntity, M extends EntityModel<T>> void switchModels() {

        this.mc.getEntityRenderDispatcher().renderers.values().forEach(renderer -> {

            if (renderer instanceof LivingRenderer) {

                LivingRenderer<T, M> livingRenderer = ((LivingRenderer<T, M>) renderer);
                M model = (M) this.getEntityModel();
                if (livingRenderer.model.getClass().isInstance(model)) {

                    BetterAnimationsCollection.LOGGER.info("Replaced {} with {} for {} in EntityRenderDispatcher", livingRenderer.getModel().getClass().getSimpleName(), model.getClass().getSimpleName(), livingRenderer.getClass().getSimpleName());
                    this.rendererToOrigModel.putIfAbsent(livingRenderer, livingRenderer.getModel());
                    livingRenderer.model = model;
                    this.transformLayers(livingRenderer);
                }
            }
        });
    }

    private <T extends LivingEntity, M extends EntityModel<T>> void transformLayers(LivingRenderer<T, M> livingRenderer) {

        if (this.layerTransformers.isEmpty()) {

            return;
        }

        for (LayerRenderer<T, M> layer : livingRenderer.layers) {

            for (LayerTransformer<?, ?> transformer : this.layerTransformers) {

                LayerTransformer<T, M> castedTransformer = (LayerTransformer<T, M>) transformer;
                if (castedTransformer.filter.test(layer)) {

                    castedTransformer.setter.accept(layer);
                    break;
                }
            }
        }
    }

    @SuppressWarnings("RedundantCast")
    private <T extends LivingEntity, S extends EntityModel<T>> void restoreModels() {

        // cast is not redundant
        this.rendererToOrigModel.forEach((key, value) -> {

            ((LivingRenderer<T, S>) key).model = (S) value;
            BetterAnimationsCollection.LOGGER.info("Restored {} for {} in EntityRenderDispatcher", value.getClass().getSimpleName(), key.getClass().getSimpleName());
        });
    }

    private static class LayerTransformer<T extends Entity, M extends EntityModel<T>> {

        public final Predicate<LayerRenderer<T, M>> filter;
        public final Consumer<LayerRenderer<T, M>> setter;

        public LayerTransformer(Predicate<LayerRenderer<T, M>> filter, Consumer<LayerRenderer<T, M>> setter) {

            this.filter = filter;
            this.setter = setter;
        }

    }

}
