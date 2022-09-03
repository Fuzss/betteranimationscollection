package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.SpiderKneesModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class SpiderKneesElement extends ModelElementBase {
    private final ModelLayerLocation animatedSpider;

    public SpiderKneesElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedSpider = modelLayerRegistry.register("animated_spider");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"A truly stunning visual addition. Spiders now finally have the knees they've always dreamed of."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelSet bakery) {
        context.registerAnimatedModel(SpiderModel.class, () -> new SpiderKneesModel<>(bakery.bakeLayer(this.animatedSpider)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedSpider, SpiderKneesModel::createAnimatedSpiderBodyLayer);
    }
}
