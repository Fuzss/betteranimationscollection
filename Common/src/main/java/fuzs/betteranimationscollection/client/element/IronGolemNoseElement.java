package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.IronGolemNoseModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;

import java.util.function.Function;

public class IronGolemNoseElement extends ModelElementBase {
    private final ModelLayerLocation animatedIronGolem;

    public IronGolemNoseElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedIronGolem = modelLayerRegistry.register("animated_iron_golem");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"A subtle change; this makes iron golems wiggle their big noses whenever they're hurt.",
                "Exactly the same animation as for villagers, except for iron golems!"};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, Function<ModelLayerLocation, ModelPart> bakery) {
        context.registerAnimatedModel(IronGolemModel.class, () -> new IronGolemNoseModel<>(bakery.apply(this.animatedIronGolem)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedIronGolem, IronGolemNoseModel::createAnimatedBodyLayer);
    }
}
