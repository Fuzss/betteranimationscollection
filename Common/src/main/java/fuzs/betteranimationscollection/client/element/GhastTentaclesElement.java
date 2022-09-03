package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.GhastTentaclesModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class GhastTentaclesElement extends ModelElementBase {
    public static int maxTentaclesLength;
    public static int animationSpeed;

    private final ModelLayerLocation animatedGhast;

    public GhastTentaclesElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedGhast = modelLayerRegistry.register("animated_ghast");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"Divides ghast tentacles into parts and makes them wiggle realistically, like those tentacle monsters you always see at the movies.",
                "Makes them a little more scary, but ultimately nicer to look at."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelSet bakery) {
        context.registerAnimatedModel(GhastModel.class, () -> new GhastTentaclesModel<>(bakery.bakeLayer(this.animatedGhast)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedGhast, GhastTentaclesModel::createAnimatedBodyLayer);
    }

    @Override
    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {
        callback.accept(builder.comment("Define the max length of tentacles.").defineInRange("max_tentacles_length", GhastTentaclesModel.GHAST_MAX_TENTACLES_LENGTH, 2, GhastTentaclesModel.GHAST_MAX_TENTACLES_LENGTH), v -> maxTentaclesLength = v);
        callback.accept(builder.comment("Animation swing speed of tentacles.").defineInRange("animation_speed", 5, 1, 20), v -> animationSpeed = v);
    }
}
