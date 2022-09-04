package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.SquidTentaclesModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.entity.LivingEntity;

public class SquidTentaclesElement extends ModelElementBase {
    public static int tentaclesLength;

    private final ModelLayerLocation animatedSquid;

    public SquidTentaclesElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedSquid = modelLayerRegistry.register("animated_squid");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"Gives a jellyfish-like effect to the swimming animation of squids; generally just makes their tentacles flow more while moving."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelBakery bakery) {
        context.<LivingEntity, SquidModel<LivingEntity>>registerAnimatedModel(SquidModel.class, () -> new SquidTentaclesModel<>(bakery.bakeLayer(this.animatedSquid)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedSquid, SquidTentaclesModel::createAnimatedBodyLayer);
    }

    @Override
    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {
        callback.accept(builder.comment("Define length for squid tentacles.").defineInRange("tentacles_length", SquidTentaclesModel.SQUID_TENTACLES_LENGTH, 1, SquidTentaclesModel.SQUID_TENTACLES_LENGTH), v -> tentaclesLength = v);
    }
}
