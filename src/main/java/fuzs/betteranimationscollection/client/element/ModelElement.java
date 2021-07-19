package fuzs.betteranimationscollection.client.element;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.renderer.entity.layers.ILayerModelAccessor;
import fuzs.betteranimationscollection.mixin.client.accessor.LivingRendererAccessor;
import fuzs.puzzleslib.config.ConfigManager;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import fuzs.puzzleslib.config.serialization.EntryCollectionBuilder;
import fuzs.puzzleslib.element.AbstractElement;
import fuzs.puzzleslib.element.side.IClientElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public abstract class ModelElement extends AbstractElement implements IClientElement {

    private final Minecraft mc = Minecraft.getInstance();
    private final Map<LivingRenderer<?, ?>, EntityModel<?>> rendererToOrigModel = Maps.newHashMap();
    private final List<LayerTransformer<?>> layerTransformers = Lists.newArrayList();
    protected final List<ResourceLocation> defaultEntityBlacklist = Lists.newArrayList();

    private Set<EntityType<?>> blacklistedEntities = Sets.newHashSet();

    @Override
    public void loadClient() {

        this.switchModels();
    }

    @Override
    public void unloadClient() {

        this.restoreModels();
    }

    @Override
    public void setupClientConfig(OptionsBuilder builder) {

        builder.define("Mob Blacklist", this.defaultEntityBlacklist.stream()
                .map(ResourceLocation::toString)
                .collect(Collectors.toList())).comment("Mob variants these model changes shouldn't be applied to.", EntryCollectionBuilder.CONFIG_STRING).sync(v -> {

            Set<EntityType<?>> newEntityBlacklist = ConfigManager.deserializeToSet(v, ForgeRegistries.ENTITIES);
            if (!this.blacklistedEntities.equals(newEntityBlacklist)) {

                this.blacklistedEntities = newEntityBlacklist;
                if (this.isEnabled() && this.isLoaded()) {

                    this.restoreModels();
                    this.switchModels();
                }
            }
        });
    }

    protected abstract EntityModel<? extends LivingEntity> getEntityModel();

    protected final void addLayerTransformer(Predicate<LayerRenderer<?, ?>> filter, Supplier<? extends EntityModel<? extends Entity>> model) {

        this.layerTransformers.add(new LayerTransformer<>(filter, model));
    }

    private <T extends LivingEntity, M extends EntityModel<T>> void switchModels() {

        this.mc.getEntityRenderDispatcher().renderers.forEach((entityType, renderer) -> {

            if (!this.blacklistedEntities.contains(entityType) && renderer instanceof LivingRenderer) {

                LivingRenderer<T, M> livingRenderer = ((LivingRenderer<T, M>) renderer);
                M model = (M) this.getEntityModel();
                // find all renderers which normally use the super class of our model, so we can exchange them
                if (livingRenderer.getModel().getClass().isInstance(model)) {

                    BetterAnimationsCollection.LOGGER.info("Replaced {} with {} for {}", livingRenderer.getModel().getClass().getSimpleName(), model.getClass().getSimpleName(), livingRenderer.getClass().getSimpleName());
                    this.rendererToOrigModel.putIfAbsent(livingRenderer, livingRenderer.getModel());
                    ((LivingRendererAccessor<T, M>) livingRenderer).setModel(model);
                    this.transformLayers(livingRenderer, true);
                }
            }
        });
    }

    private <T extends LivingEntity, S extends EntityModel<T>> void restoreModels() {

        this.rendererToOrigModel.forEach((key, value) -> {

            BetterAnimationsCollection.LOGGER.info("Restored {} for {}", value.getClass().getSimpleName(), key.getClass().getSimpleName());
            ((LivingRendererAccessor<T, S>) key).setModel((S) value);
            this.transformLayers(key, false);
        });
    }

    private void transformLayers(LivingRenderer<?, ?> livingRenderer, boolean transform) {

        if (this.layerTransformers.isEmpty()) {

            return;
        }

        for (LayerRenderer<?, ?> layer : ((LivingRendererAccessor<?, ?>) livingRenderer).getLayers()) {

            if (layer instanceof ILayerModelAccessor) {

                for (LayerTransformer<?> layerTransformer : this.layerTransformers) {

                    if (transform) {

                        if (layerTransformer.applyTransform(layer)) {

                            BetterAnimationsCollection.LOGGER.info("Replaced layer model in {} for {}", layer.getClass().getSimpleName(), livingRenderer.getClass().getSimpleName());
                            break;
                        }
                    } else {

                        if (layerTransformer.applyRestore(layer)) {

                            BetterAnimationsCollection.LOGGER.info("Restored layer model in {} for {}", layer.getClass().getSimpleName(), livingRenderer.getClass().getSimpleName());
                            break;
                        }
                    }
                }
            }
        }
    }

    private static class LayerTransformer<M extends EntityModel<? extends Entity>> {

        private final Predicate<LayerRenderer<?, ?>> filter;
        private final Supplier<M> model;

        private M origModel;

        public LayerTransformer(Predicate<LayerRenderer<?, ?>> filter, Supplier<M> model) {

            this.filter = filter;
            this.model = model;
        }

        public boolean applyTransform(LayerRenderer<?, ?> layerRenderer) {

            return this.apply(layerRenderer, this.model, true);
        }

        public boolean applyRestore(LayerRenderer<?, ?> layerRenderer) {

            return this.apply(layerRenderer, () -> this.origModel, false);
        }

        private boolean apply(LayerRenderer<?, ?> layerRenderer, Supplier<M> modelSupplier, boolean preserveOriginal) {

            if (this.filter.test(layerRenderer)) {

                ILayerModelAccessor<M> modelAccessor = (ILayerModelAccessor<M>) layerRenderer;
                if (preserveOriginal && this.origModel == null) {

                    this.origModel = modelAccessor.getModel();
                }

                modelAccessor.setModel(modelSupplier.get());
                return true;
            }

            return false;
        }

    }

}
