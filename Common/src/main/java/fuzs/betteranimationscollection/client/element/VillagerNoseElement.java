package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.VillagerNoseModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.Function;

public class VillagerNoseElement extends ModelElementBase {
    private final ModelLayerLocation animatedVillager;

    public VillagerNoseElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedVillager = modelLayerRegistry.register("animated_villager");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"A subtle change; this makes villagers wiggle their big noses whenever they make their iconic sound.",
                "It's a small change, but who doesn't get a kick out of it?"};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, Function<ModelLayerLocation, ModelPart> bakery) {
        context.registerAnimatedModel(VillagerModel.class, () -> new VillagerNoseModel<>(bakery.apply(this.animatedVillager)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedVillager, () -> LayerDefinition.create(VillagerModel.createBodyModel(), 64, 64));
    }
}
