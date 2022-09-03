package fuzs.betteranimationscollection.client.element;

import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ModelElementBase {
    private boolean enabled = true;
    private boolean dirty = true;

    public void setEnabled(boolean enabled) {
        if (enabled != this.enabled) {
            this.enabled = enabled;
            this.dirty = true;
        }
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public abstract String[] modelDescription();

    public final void registerAnimatedModels(AnimatedModelsContext context, EntityModelSet bakery) {
        this.dirty = false;
        if (this.enabled) {
            this.onRegisterAnimatedModels(context, bakery);
        }
    }

    abstract void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelSet bakery);

    public abstract void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context);

    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {

    }

    @FunctionalInterface
    public interface AnimatedModelsContext {

        default <T extends LivingEntity, M extends EntityModel<T>> void registerAnimatedModel(Class<? super M> vanillaModelClazz, Supplier<M> animatedModel) {
            this.registerAnimatedModel(vanillaModelClazz, animatedModel, (RenderLayerParent<T, M> renderLayerParent, RenderLayer<T, M> renderLayer) -> Optional.empty());
        }

        <T extends LivingEntity, M extends EntityModel<T>> void registerAnimatedModel(Class<? super M> vanillaModelClazz, Supplier<M> animatedModel, LayerTransformer<T, M> layerTransformer);
    }

    @FunctionalInterface
    public interface LayerTransformer<T extends LivingEntity, M extends EntityModel<T>> {

        Optional<RenderLayer<T, M>> apply(RenderLayerParent<T, M> renderLayerParent, RenderLayer<T, M> renderLayer);
    }

    public record AnimatedModelData<T extends LivingEntity, M extends EntityModel<T>>(Class<? super M> vanillaModelClazz, Supplier<M> animatedModel, LayerTransformer<T, M> layerTransformer) {

    }
}
