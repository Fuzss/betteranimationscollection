package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.FamiliarChestedHorseModel;
import fuzs.betteranimationscollection.client.model.FamiliarHorseModel;
import fuzs.betteranimationscollection.mixin.client.accessor.HorseArmorLayerAccessor;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.ChestedHorseModel;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HorseArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.animal.horse.Horse;

import java.util.Optional;

public class FamiliarHorseElement extends ModelElementBase {
    private final ModelLayerLocation animatedHorse;
    private final ModelLayerLocation animatedHorseArmor;
    private final ModelLayerLocation animatedChestedHorse;

    public FamiliarHorseElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedHorse = modelLayerRegistry.register("animated_horse");
        this.animatedHorseArmor = modelLayerRegistry.register("animated_horse_armor");
        this.animatedChestedHorse = modelLayerRegistry.register("animated_chested_horse");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"Makes horses more lively again, just like they used to be in older versions.",
                "Does this by adding back their mouth and knees while staying true to the vanilla model style."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelSet bakery) {
        context.registerAnimatedModel(HorseModel.class, () -> new FamiliarHorseModel<>(bakery.bakeLayer(this.animatedHorse)), (RenderLayerParent<Horse, HorseModel<Horse>> renderLayerParent, RenderLayer<Horse, HorseModel<Horse>> renderLayer) -> {
            if (renderLayer instanceof HorseArmorLayer) {
                ((HorseArmorLayerAccessor) renderLayer).setModel(new FamiliarHorseModel<>(bakery.bakeLayer(this.animatedHorseArmor)));
            }
            return Optional.empty();
        });
        context.registerAnimatedModel(ChestedHorseModel.class, () -> new FamiliarChestedHorseModel<>(bakery.bakeLayer(this.animatedChestedHorse)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedHorse, () -> LayerDefinition.create(FamiliarHorseModel.createAnimatedBodyMesh(CubeDeformation.NONE), 64, 64));
        context.registerLayerDefinition(this.animatedHorseArmor, () -> LayerDefinition.create(FamiliarHorseModel.createAnimatedBodyMesh(new CubeDeformation(0.1F)), 64, 64));
        context.registerLayerDefinition(this.animatedChestedHorse, FamiliarChestedHorseModel::createAnimatedBodyLayer);
    }
}
