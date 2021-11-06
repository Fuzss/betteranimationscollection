package fuzs.betteranimationscollection.client.element;

import com.google.common.collect.ImmutableList;
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
import fuzs.puzzleslib.util.LoadedLocationList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.*;

@SuppressWarnings("unchecked")
public abstract class ModelElement extends AbstractElement implements IClientElement {

    private final Minecraft mc = Minecraft.getInstance();
    private final Map<EntityType<?>, ModelInfo> entityTypeToModelInfo = Maps.newHashMap();
    /**
     * element can provide multiple models for multiple mobs, therefore store by model class
     * also don't use multi map as we want to retrieve a list with index access
     */
    private final Map<Class<?>, List<LayerTransformer<?>>> layerTransformers = Maps.newHashMap();
    protected final LoadedLocationList defaultEntityBlacklist = new LoadedLocationList();

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

        builder.define("Mob Blacklist", this.defaultEntityBlacklist).comment("Mob variants these model changes shouldn't be applied to.", EntryCollectionBuilder.CONFIG_STRING).sync(v -> {

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

    protected List<Supplier<EntityModel<? extends LivingEntity>>> getEntityModels() {

        return ImmutableList.of(this::getEntityModel);
    }

    protected final void addLayerTransformer(Class<?> entityModelClazz, Predicate<LayerRenderer<?, ?>> filter, Supplier<? extends EntityModel<? extends Entity>> model) {

        this.layerTransformers.computeIfAbsent(entityModelClazz, clazz -> Lists.newArrayList()).add(new LayerTransformer<>(filter, model));
    }

    protected void collectModels() {

        this.mc.getEntityRenderDispatcher().renderers.forEach((entityType, renderer) -> {

            if (renderer instanceof LivingRenderer) {

                LivingRenderer<? extends LivingEntity, EntityModel<? extends LivingEntity>> livingRenderer = (LivingRenderer<? extends LivingEntity, EntityModel<? extends LivingEntity>>) renderer;
                // whisperwoods uses null for models...
                if (livingRenderer.getModel() != null) {

                    // find all renderers which normally use the super class of our model's, so we can exchange them
                    for (Supplier<EntityModel<? extends LivingEntity>> entityModel : this.getEntityModels()) {

                        EntityModel<? extends LivingEntity> model = entityModel.get();
                        if (livingRenderer.getModel().getClass().equals(model.getClass().getSuperclass())) {

                            List<LayerTransformer<?>> modelLayerTransformers = this.layerTransformers.get(model.getClass());
                            this.entityTypeToModelInfo.put(entityType, new ModelInfo(livingRenderer, livingRenderer.getModel(), model, modelLayerTransformers != null ? modelLayerTransformers.size() : 0));
                            break;
                        }
                    }
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

        // TODO make this work
    }

    private static class LayerExchanger implements LayerAction {

        @Override
        public LayerRenderer<?, ?> apply(LayerRenderer<?, ?> layerRenderer) {

            // TODO make this work
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

            // TODO make this work
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
                BetterAnimationsCollection.LOGGER.info("Replaced {} with {} for {}", this.origModel.getClass().getSimpleName(), this.animatedModel.getClass().getSimpleName(), this.toSimpleString(this.livingRenderer));
                this.switchLayers();
            }
        }

        public void resetModel() {

            if (this.isModelSwitched) {

                this.isModelSwitched = false;
                this.setRendererModel(this.origModel);
                BetterAnimationsCollection.LOGGER.info("Restored {} for {}", this.origModel.getClass().getSimpleName(), this.toSimpleString(this.livingRenderer));
                this.resetLayers();
            }
        }

        private void setRendererModel(EntityModel<? extends LivingEntity> model) {

            ((LivingRendererAccessor<? extends LivingEntity, EntityModel<? extends LivingEntity>>) this.livingRenderer).setModel(model);
        }

        private String toSimpleString(Object o) {

            return o.getClass().getSimpleName() + "@" + Integer.toHexString(o.hashCode());
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

            List<LayerTransformer<?>> modelLayerTransformers = ModelElement.this.layerTransformers.get(this.animatedModel.getClass());
            if (modelLayerTransformers == null || modelLayerTransformers.isEmpty()) {

                return;
            }

            Map<LayerRenderer<?, ?>, LayerRenderer<?, ?>> layerRendererReplacements = Maps.newHashMap();
            for (LayerRenderer<?, ?> layerRenderer : ((LivingRendererAccessor<?, ?>) this.livingRenderer).getLayers()) {

                if (layerRenderer instanceof ILayerModelAccessor) {

                    for (int i = 0, transformersSize = modelLayerTransformers.size(); i < transformersSize; i++) {

                        LayerTransformer<?> layerTransformer = modelLayerTransformers.get(i);
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
