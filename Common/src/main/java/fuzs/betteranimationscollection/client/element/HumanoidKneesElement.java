package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.HumanoidKneesModel;
import fuzs.betteranimationscollection.client.model.ZombieKneesModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.monster.Zombie;

import java.util.Optional;
import java.util.function.Function;

public class HumanoidKneesElement extends ModelElementBase {
    private final ModelLayerLocation animatedZombie;
    private final ModelLayerLocation animatedZombieInnerArmor;
    private final ModelLayerLocation animatedZombieOuterArmor;

    public HumanoidKneesElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedZombie = modelLayerRegistry.register("animated_zombie");
        this.animatedZombieInnerArmor = modelLayerRegistry.registerInnerArmor("animated_zombie");
        this.animatedZombieOuterArmor = modelLayerRegistry.registerOuterArmor("animated_zombie");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"This one makes the knees of zombie-like and piglin-like mobs bend when they walk around.",
                "Looks pretty fluid and nice. You'll like it, trust me."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, Function<ModelLayerLocation, ModelPart> bakery) {
        context.registerAnimatedModel(ZombieModel.class, () -> new ZombieKneesModel<>(bakery.apply(this.animatedZombie)), (RenderLayerParent<Zombie, ZombieKneesModel<Zombie>> renderLayerParent, RenderLayer<Zombie, ZombieKneesModel<Zombie>> renderLayer) -> {
            if (renderLayer instanceof HumanoidArmorLayer) {
                return Optional.of(new HumanoidArmorLayer<>(renderLayerParent, new ZombieKneesModel<>(bakery.apply(this.animatedZombieInnerArmor)), new ZombieKneesModel<>(bakery.apply(this.animatedZombieOuterArmor))));
            }
            return Optional.empty();
        });
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedZombie, () -> LayerDefinition.create(HumanoidKneesModel.createAnimatedMesh(CubeDeformation.NONE, 0.0F), 64, 64));
        context.registerLayerDefinition(this.animatedZombieInnerArmor, () -> LayerDefinition.create(HumanoidKneesModel.createAnimatedMesh(new CubeDeformation(1.0F), 0.0F), 64, 64));
        context.registerLayerDefinition(this.animatedZombieOuterArmor, () -> LayerDefinition.create(HumanoidKneesModel.createAnimatedMesh(new CubeDeformation(0.5F), 0.0F), 64, 64));
    }
}
