package fuzs.betteranimationscollection.client.element;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.mixin.client.accessor.ILivingRendererAccessor;
import fuzs.puzzleslib.element.AbstractElement;
import fuzs.puzzleslib.element.side.IClientElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public abstract class ModelElement extends AbstractElement implements IClientElement {

    private final Minecraft mc = Minecraft.getInstance();
    private final Map<LivingRenderer<?, ?>, EntityModel<?>> rendererToOrigModel = Maps.newHashMap();
    private final List<Pair<Predicate<LayerRenderer<?, ?>>, Consumer<LayerRenderer<?, ?>>>> layerTransformers = Lists.newArrayList();

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

    protected final void addLayerTransformer(Predicate<LayerRenderer<?, ?>> filter, Consumer<LayerRenderer<?, ?>> setter) {

        this.layerTransformers.add(Pair.of(filter, setter));
    }

    private <T extends LivingEntity, M extends EntityModel<T>> void switchModels() {

        this.mc.getEntityRenderDispatcher().renderers.values().forEach(renderer -> {

            if (renderer instanceof LivingRenderer) {

                LivingRenderer<T, M> livingRenderer = ((LivingRenderer<T, M>) renderer);
                M model = (M) this.getEntityModel();
                if (livingRenderer.getModel().getClass().isInstance(model)) {

                    BetterAnimationsCollection.LOGGER.info("Replaced {} with {} for {}", livingRenderer.getModel().getClass().getSimpleName(), model.getClass().getSimpleName(), livingRenderer.getClass().getSimpleName());
                    this.rendererToOrigModel.putIfAbsent(livingRenderer, livingRenderer.getModel());
                    ((ILivingRendererAccessor<T, M>) livingRenderer).setModel(model);
                    this.transformLayers(livingRenderer);
                }
            }
        });
    }

    private <T extends LivingEntity, M extends EntityModel<T>> void transformLayers(LivingRenderer<T, M> livingRenderer) {

        if (this.layerTransformers.isEmpty()) {

            return;
        }

        for (LayerRenderer<T, M> layer : ((ILivingRendererAccessor<T, M>) livingRenderer).getLayers()) {

            for (Pair<Predicate<LayerRenderer<?, ?>>, Consumer<LayerRenderer<?, ?>>> transformer : this.layerTransformers) {

                if (transformer.getLeft().test(layer)) {

                    transformer.getRight().accept(layer);
                    BetterAnimationsCollection.LOGGER.info("Replaced layer model in {} for {}", layer.getClass().getSimpleName(), livingRenderer.getClass().getSimpleName());
                    break;
                }
            }
        }
    }

    private <T extends LivingEntity, S extends EntityModel<T>> void restoreModels() {

        this.rendererToOrigModel.forEach((key, value) -> {

            ((ILivingRendererAccessor<T, S>) key).setModel((S) value);
            BetterAnimationsCollection.LOGGER.info("Restored {} for {}", value.getClass().getSimpleName(), key.getClass().getSimpleName());
        });
    }

}
