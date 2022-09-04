package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.MagmaCubeBurgerModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.LavaSlimeModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.entity.monster.Slime;

public class MagmaCubeBurgerElement extends ModelElementBase {
    private final ModelLayerLocation animatedMagmaCube;

    public MagmaCubeBurgerElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedMagmaCube = modelLayerRegistry.register("animated_magma_cube");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"Adds a custom death animation to magma cubes, which causes their bodies to form into a pile of steamy, delicious hamburger patties when they die.",
                "Unfortunately, you can't eat them because they're way too hot."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelBakery bakery) {
        context.<Slime, LavaSlimeModel<Slime>>registerAnimatedModel(LavaSlimeModel.class, () -> new MagmaCubeBurgerModel<>(bakery.bakeLayer(this.animatedMagmaCube)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedMagmaCube, LavaSlimeModel::createBodyLayer);
    }
}
