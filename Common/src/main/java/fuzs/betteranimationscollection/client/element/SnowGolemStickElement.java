package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.SnowGolemStickModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;

import java.util.function.Function;

public class SnowGolemStickElement extends ModelElementBase {
    private final ModelLayerLocation animatedSnowGolem;

    public SnowGolemStickElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedSnowGolem = modelLayerRegistry.register("animated_snow_golem");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"This makes a snowman's arm swing when it throws a snowball."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, Function<ModelLayerLocation, ModelPart> bakery) {
        context.registerAnimatedModel(SnowGolemModel.class, () -> new SnowGolemStickModel<>(bakery.apply(this.animatedSnowGolem)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedSnowGolem, SnowGolemModel::createBodyLayer);
    }
}
