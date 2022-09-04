package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.OcelotTailModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.world.entity.LivingEntity;

public class OcelotTailElement extends ModelElementBase {
    public static int tailLength;
    public static int animationSpeed;

    private final ModelLayerLocation animatedOcelot;

    public OcelotTailElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedOcelot = modelLayerRegistry.register("animated_ocelot");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"Takes away the stick tails of the current ocelots and gives them something nicer instead.",
                "Fully animated flowing tails that move while they stand or run."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelBakery bakery) {
        context.<LivingEntity, OcelotTailModel<LivingEntity>>registerAnimatedModel(OcelotModel.class, () -> new OcelotTailModel<>(bakery.bakeLayer(this.animatedOcelot)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedOcelot, () -> OcelotTailModel.createAnimatedBodyMesh(CubeDeformation.NONE));
    }

    @Override
    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {
        callback.accept(builder.comment("Define tail length.").defineInRange("tail_length", OcelotTailModel.OCELOT_TAIL_LENGTH, 1, OcelotTailModel.OCELOT_TAIL_LENGTH), v -> tailLength = v);
        callback.accept(builder.comment("Animation swing speed for tail.").defineInRange("animation_speed", 7, 1, 20), v -> animationSpeed = v);
    }
}
