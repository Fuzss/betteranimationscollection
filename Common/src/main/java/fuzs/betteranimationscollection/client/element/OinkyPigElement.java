package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.OinkyPigModel;
import fuzs.betteranimationscollection.mixin.client.accessor.SaddleLayerAccessor;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;

import java.util.Optional;
import java.util.function.Function;

public class OinkyPigElement extends ModelElementBase {
    public static boolean floatyEars;
    public static int earAnimationSpeed;

    private final ModelLayerLocation animatedPig;
    private final ModelLayerLocation animatedPigSaddle;

    public OinkyPigElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedPig = modelLayerRegistry.register("animated_pig");
        this.animatedPigSaddle = modelLayerRegistry.register("animated_pig", "saddle");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"This makes the udders on cows wobble around when they walk.",
                "Also makes their udders have nipples."};
    }

    @SuppressWarnings("unchecked")
    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, Function<ModelLayerLocation, ModelPart> bakery) {
        context.registerAnimatedModel(PigModel.class, () -> new OinkyPigModel<>(bakery.apply(this.animatedPig)), layerRenderer -> {
            if (layerRenderer instanceof SaddleLayer) {
                ((SaddleLayerAccessor<?, PigModel<?>>) layerRenderer).setModel(new OinkyPigModel<>(bakery.apply(this.animatedPigSaddle)));
            }
            return Optional.empty();
        });
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedPig, () -> OinkyPigModel.createBodyLayer(CubeDeformation.NONE));
        context.registerLayerDefinition(this.animatedPigSaddle, () -> OinkyPigModel.createBodyLayer(new CubeDeformation(0.5F)));
    }

    @Override
    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {
        callback.accept(builder.comment("Fancy ears for pigs, just like piglins have them.").define("floaty_ears", true), v -> floatyEars = v);
        callback.accept(builder.comment("Animation swing speed for ear floatiness.").defineInRange("ear_animation_speed", 10, 1, 20), v -> earAnimationSpeed = v);
    }
}
