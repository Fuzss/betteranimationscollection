package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.SnowGolemStickModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.SnowGolem;

public class SnowGolemStickElement extends SoundDetectionElement {
    private final ModelLayerLocation animatedSnowGolem;

    public SnowGolemStickElement(ModelLayerRegistry modelLayerRegistry) {
        super(SnowGolem.class, SoundEvents.SNOW_GOLEM_SHOOT);
        this.animatedSnowGolem = modelLayerRegistry.register("animated_snow_golem");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"This makes a snowman's arm swing when it throws a snowball."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelBakery bakery) {
        context.<LivingEntity, SnowGolemModel<LivingEntity>>registerAnimatedModel(SnowGolemModel.class, () -> new SnowGolemStickModel<>(bakery.bakeLayer(this.animatedSnowGolem)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedSnowGolem, SnowGolemModel::createBodyLayer);
    }
}
