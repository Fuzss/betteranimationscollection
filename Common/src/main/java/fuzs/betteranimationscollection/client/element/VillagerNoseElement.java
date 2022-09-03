package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.handler.RemoteSoundHandler;
import fuzs.betteranimationscollection.client.model.VillagerNoseModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.AbstractVillager;

public class VillagerNoseElement extends SoundDetectionElement {
    private final ModelLayerLocation animatedVillager;

    public VillagerNoseElement(ModelLayerRegistry modelLayerRegistry) {
        super(AbstractVillager.class, SoundEvents.VILLAGER_AMBIENT, SoundEvents.VILLAGER_TRADE, SoundEvents.WANDERING_TRADER_AMBIENT, SoundEvents.WANDERING_TRADER_TRADE);
        this.animatedVillager = modelLayerRegistry.register("animated_villager");
        RemoteSoundHandler.INSTANCE.addAttackableEntity(IronGolem.class);
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"A subtle change; this makes villagers wiggle their big noses whenever they make their iconic sound.",
                "It's a small change, but who doesn't get a kick out of it?"};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelSet bakery) {
        context.registerAnimatedModel(VillagerModel.class, () -> new VillagerNoseModel<>(bakery.bakeLayer(this.animatedVillager)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedVillager, () -> LayerDefinition.create(VillagerModel.createBodyModel(), 64, 64));
    }
}
