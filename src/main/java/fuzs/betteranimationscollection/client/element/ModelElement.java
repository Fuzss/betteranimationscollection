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
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public abstract class ModelElement extends AbstractElement implements IClientElement {

    private final Minecraft mc = Minecraft.getInstance();
    private final Map<EntityType<?>, ModelInfo> entityTypeToModelInfo = Maps.newHashMap();
    private final List<LayerTransformer<?>> layerTransformers = Lists.newArrayList();
    protected final List<ResourceLocation> defaultEntityBlacklist = Lists.newArrayList();

    private Set<EntityType<?>> blacklistedEntities = Sets.newHashSet();

    @Override
    public final void setupClient2() {

        this.collectModels();
    }

    @Override
    public final void loadClient() {

        this.applyModelAction(ModelInfo::switchModel, entityType -> !this.blacklistedEntities.contains(entityType));
    }

    @Override
    public final void unloadClient() {

        this.applyModelAction(ModelInfo::resetModel, entityType -> true);
    }

    @Override
    public final void setupClientConfig(OptionsBuilder builder) {

        builder.define("Mob Blacklist", this.defaultEntityBlacklist.stream()
                .map(ResourceLocation::toString)
                .collect(Collectors.toList())).comment("Mob variants these model changes shouldn't be applied to.", EntryCollectionBuilder.CONFIG_STRING).sync(v -> {

            this.blacklistedEntities = ConfigManager.deserializeToSet(v, ForgeRegistries.ENTITIES);
            if (this.isEnabled() && this.isTypeLoaded(ModConfig.Type.CLIENT)) {

                this.applyModelAction(ModelInfo::resetModel, entityType -> this.blacklistedEntities.contains(entityType));
                this.applyModelAction(ModelInfo::switchModel, entityType -> !this.blacklistedEntities.contains(entityType));
            }
        });

        this.setupModelConfig(builder);
    }

    public void setupModelConfig(OptionsBuilder builder) {

    }

    protected abstract EntityModel<? extends LivingEntity> getEntityModel();

    protected final void addLayerTransformer(Predicate<LayerRenderer<?, ?>> filter, Supplier<? extends EntityModel<? extends Entity>> model) {

        this.layerTransformers.add(new LayerTransformer<>(filter, model));
    }

    private void collectModels() {

        this.mc.getEntityRenderDispatcher().renderers.forEach((entityType, renderer) -> {

            if (renderer instanceof LivingRenderer) {

                LivingRenderer<? extends LivingEntity, EntityModel<? extends LivingEntity>> livingRenderer = (LivingRenderer<? extends LivingEntity, EntityModel<? extends LivingEntity>>) renderer;
                // find all renderers which normally use the super class of our model, so we can exchange them
                EntityModel<? extends LivingEntity> entityModel = this.getEntityModel();
                if (livingRenderer.getModel().getClass().equals(entityModel.getClass().getSuperclass())) {

                    this.entityTypeToModelInfo.put(entityType, new ModelInfo(livingRenderer, livingRenderer.getModel(), entityModel, this.layerTransformers.size()));
                }
            }
        });
    }

    private void applyModelAction(Consumer<ModelInfo> action, Predicate<EntityType<?>> filter) {

        for (Map.Entry<EntityType<?>, ModelInfo> entry : this.entityTypeToModelInfo.entrySet()) {

            if (filter.test(entry.getKey())) {

                action.accept(entry.getValue());
            }
        }
    }

    private interface LayerAction extends Function<LayerRenderer<?, ?>, LayerRenderer<?, ?>> {

    }

    private static class LayerExchanger implements LayerAction {

        @Override
        public LayerRenderer<?, ?> apply(LayerRenderer<?, ?> layerRenderer) {
            return null;
        }
    }

    private static class LayerTransformer<M extends EntityModel<? extends Entity>> implements LayerAction {

        private final Predicate<LayerRenderer<?, ?>> filter;
        private final Supplier<M> model;

        public LayerTransformer(Predicate<LayerRenderer<?, ?>> filter, Supplier<M> model) {

            this.filter = filter;
            this.model = model;
        }

        public EntityModel<? extends Entity> applyTransform(LayerRenderer<?, ?> layerRenderer) {

            if (this.filter.test(layerRenderer)) {

                ILayerModelAccessor<EntityModel<? extends Entity>> modelAccessor = (ILayerModelAccessor<EntityModel<? extends Entity>>) layerRenderer;
                EntityModel<? extends Entity> origModel = modelAccessor.getModel();
                modelAccessor.setModel(this.model.get());
                BetterAnimationsCollection.LOGGER.info("Replaced layer model in {}", layerRenderer.getClass().getSimpleName());
                return origModel;
            }

            return null;
        }

        public boolean applyRestore(LayerRenderer<?, ?> layerRenderer, EntityModel<? extends Entity> origModel) {

            if (this.filter.test(layerRenderer) && origModel != null) {

                ILayerModelAccessor<EntityModel<? extends Entity>> modelAccessor = (ILayerModelAccessor<EntityModel<? extends Entity>>) layerRenderer;
                modelAccessor.setModel(origModel);
                BetterAnimationsCollection.LOGGER.info("Restored layer model in {}", layerRenderer.getClass().getSimpleName());
                return true;
            }

            return false;
        }

        @Override
        public LayerRenderer<?, ?> apply(LayerRenderer<?, ?> layerRenderer) {
            return null;
        }
    }

    private class ModelInfo {

        public final LivingRenderer<? extends LivingEntity, EntityModel<? extends LivingEntity>> livingRenderer;
        public final EntityModel<? extends LivingEntity> origModel;
        public final EntityModel<? extends LivingEntity> animatedModel;
        private final Object[] origLayerModels;

        private boolean isModelSwitched;

        public ModelInfo(LivingRenderer<? extends LivingEntity, EntityModel<? extends LivingEntity>> livingRenderer, EntityModel<? extends LivingEntity> origModel, EntityModel<? extends LivingEntity> animatedModel, int layerModelCapacity) {

            this.livingRenderer = livingRenderer;
            this.origModel = origModel;
            this.animatedModel = animatedModel;
            this.origLayerModels = new Object[layerModelCapacity];
        }

        public void switchModel() {

            if (!this.isModelSwitched) {

                this.isModelSwitched = true;
                this.setRendererModel(this.animatedModel);
                BetterAnimationsCollection.LOGGER.info("Replaced {} with {} for {}", this.origModel.getClass().getSimpleName(), this.animatedModel.getClass().getSimpleName(), this.livingRenderer.getClass().getSimpleName());
                this.switchLayers();
            }
        }

        public void resetModel() {

            if (this.isModelSwitched) {

                this.isModelSwitched = false;
                this.setRendererModel(this.origModel);
                BetterAnimationsCollection.LOGGER.info("Restored {} for {}", this.origModel.getClass().getSimpleName(), this.livingRenderer.getClass().getSimpleName());
                this.resetLayers();
            }
        }

        private void setRendererModel(EntityModel<? extends LivingEntity> model) {

            ((LivingRendererAccessor<? extends LivingEntity, EntityModel<? extends LivingEntity>>) this.livingRenderer).setModel(model);
        }

        private void switchLayers() {

            this.transformLayers((layerTransformer, layerRenderer, index) -> layerTransformer.applyTransform(layerRenderer), (result, index) -> {

                if (result != null) {

                    this.origLayerModels[index] = result;
                    return true;
                }

                return false;
            });
        }

        private void resetLayers() {

            this.transformLayers((layerTransformer, layerRenderer, index) -> layerTransformer.applyRestore(layerRenderer, (EntityModel<? extends Entity>) this.origLayerModels[index]), (result, index) -> result);
        }

        private <T> void transformLayers(LayerTransformation<T> applyTransformer, BiPredicate<T, Integer> resultConverter) {

            if (ModelElement.this.layerTransformers.isEmpty()) {

                return;
            }

            Map<LayerRenderer<?, ?>, LayerRenderer<?, ?>> layerRendererReplacements = Maps.newHashMap();
            for (LayerRenderer<?, ?> layerRenderer : ((LivingRendererAccessor<?, ?>) this.livingRenderer).getLayers()) {

                if (layerRenderer instanceof ILayerModelAccessor) {

                    List<LayerTransformer<?>> transformers = ModelElement.this.layerTransformers;
                    for (int i = 0, transformersSize = transformers.size(); i < transformersSize; i++) {

                        LayerTransformer<?> layerTransformer = transformers.get(i);
                        T result = applyTransformer.apply(layerTransformer, layerRenderer, i);
                        if (resultConverter.test(result, i)) {

                            break;
                        }
                    }
                }
            }
        }

    }

    @FunctionalInterface
    private interface LayerTransformation<T> {

        T apply(LayerTransformer<?> layerTransformer, LayerRenderer<?, ?> layerRenderer, Integer index);

    }

}
