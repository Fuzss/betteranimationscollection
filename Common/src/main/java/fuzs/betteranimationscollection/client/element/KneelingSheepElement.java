package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.KneelingSheepFurModel;
import fuzs.betteranimationscollection.client.model.KneelingSheepModel;
import fuzs.betteranimationscollection.mixin.client.accessor.SheepFurLayerAccessor;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SheepFurLayer;
import net.minecraft.world.entity.animal.Sheep;

import java.util.Optional;
import java.util.function.Function;

public class KneelingSheepElement extends ModelElementBase {
    private final ModelLayerLocation animatedSheep;
    private final ModelLayerLocation animatedSheepFur;

    public KneelingSheepElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedSheep = modelLayerRegistry.register("animated_sheep");
        this.animatedSheepFur = modelLayerRegistry.register("animated_sheep", "fur");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"This one is pretty kneat. It makes sheep actually bend down to eat grass.",
                "It's no longer just their head lowering, their whole body lowers down to get a sweet sample of that succulent cellulose.",
                "Did you notice their KNEES bend too when they kneel?"};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, Function<ModelLayerLocation, ModelPart> bakery) {
        context.registerAnimatedModel(SheepModel.class, () -> new KneelingSheepModel<>(bakery.apply(this.animatedSheep)), (RenderLayerParent<Sheep, SheepModel<Sheep>> renderLayerParent, RenderLayer<Sheep, SheepModel<Sheep>> renderLayer) -> {
            if (renderLayer instanceof SheepFurLayer) {
                ((SheepFurLayerAccessor) renderLayer).setModel(new KneelingSheepFurModel<>(bakery.apply(this.animatedSheepFur)));
            }
            return Optional.empty();
        });
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedSheep, KneelingSheepModel::createAnimatedBodyLayer);
        context.registerLayerDefinition(this.animatedSheepFur, KneelingSheepFurModel::createAnimatedFurLayer);
    }
}
