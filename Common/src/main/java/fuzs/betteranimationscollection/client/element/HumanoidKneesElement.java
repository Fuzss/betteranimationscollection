package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.DrownedKneesModel;
import fuzs.betteranimationscollection.client.model.HumanoidKneesModel;
import fuzs.betteranimationscollection.client.model.PiglinKneesModel;
import fuzs.betteranimationscollection.client.model.ZombieKneesModel;
import fuzs.betteranimationscollection.mixin.client.accessor.DrownedOuterLayerAccessor;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.DrownedOuterLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Zombie;

import java.util.Optional;

public class HumanoidKneesElement extends ModelElementBase {
    private final ModelLayerLocation animatedZombie;
    private final ModelLayerLocation animatedZombieInnerArmor;
    private final ModelLayerLocation animatedZombieOuterArmor;
    private final ModelLayerLocation animatedDrowned;
    private final ModelLayerLocation animatedDrownedOuterLayer;
    private final ModelLayerLocation animatedDrownedInnerArmor;
    private final ModelLayerLocation animatedDrownedOuterArmor;
    private final ModelLayerLocation animatedPiglin;
    private final ModelLayerLocation animatedPiglinInnerArmor;
    private final ModelLayerLocation animatedPiglinOuterArmor;

    public HumanoidKneesElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedZombie = modelLayerRegistry.register("animated_zombie");
        this.animatedZombieInnerArmor = modelLayerRegistry.registerInnerArmor("animated_zombie");
        this.animatedZombieOuterArmor = modelLayerRegistry.registerOuterArmor("animated_zombie");
        this.animatedDrowned = modelLayerRegistry.register("animated_drowned");
        this.animatedDrownedOuterLayer = modelLayerRegistry.register("animated_drowned", "outer");
        this.animatedDrownedInnerArmor = modelLayerRegistry.registerInnerArmor("animated_drowned");
        this.animatedDrownedOuterArmor = modelLayerRegistry.registerOuterArmor("animated_drowned");
        this.animatedPiglin = modelLayerRegistry.register("animated_piglin");
        this.animatedPiglinInnerArmor = modelLayerRegistry.registerInnerArmor("animated_piglin");
        this.animatedPiglinOuterArmor = modelLayerRegistry.registerOuterArmor("animated_piglin");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"This one makes the knees of zombie-like and piglin-like mobs bend when they walk around.",
                "Looks pretty fluid and nice. You'll like it, trust me."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelSet bakery) {
        context.registerAnimatedModel(ZombieModel.class, () -> new ZombieKneesModel<>(bakery.bakeLayer(this.animatedZombie)), (RenderLayerParent<Zombie, ZombieKneesModel<Zombie>> renderLayerParent, RenderLayer<Zombie, ZombieKneesModel<Zombie>> renderLayer) -> {
            if (renderLayer instanceof HumanoidArmorLayer) {
                return Optional.of(new HumanoidArmorLayer<>(renderLayerParent, new ZombieKneesModel<>(bakery.bakeLayer(this.animatedZombieInnerArmor)), new ZombieKneesModel<>(bakery.bakeLayer(this.animatedZombieOuterArmor))));
            }
            return Optional.empty();
        });
        context.registerAnimatedModel(DrownedModel.class, () -> new DrownedKneesModel<>(bakery.bakeLayer(this.animatedDrowned)), (RenderLayerParent<Drowned, DrownedModel<Drowned>> renderLayerParent, RenderLayer<Drowned, DrownedModel<Drowned>> renderLayer) -> {
            if (renderLayer instanceof HumanoidArmorLayer) {
                return Optional.of(new HumanoidArmorLayer<>(renderLayerParent, new DrownedKneesModel<>(bakery.bakeLayer(this.animatedDrownedInnerArmor)), new DrownedKneesModel<>(bakery.bakeLayer(this.animatedDrownedOuterArmor))));
            }
            if (renderLayer instanceof DrownedOuterLayer<Drowned>) {
                ((DrownedOuterLayerAccessor<?>) renderLayer).setModel(new DrownedKneesModel<>(bakery.bakeLayer(this.animatedDrownedOuterLayer)));
            }
            return Optional.empty();
        });
        context.registerAnimatedModel(PiglinModel.class, () -> new PiglinKneesModel<>(bakery.bakeLayer(this.animatedPiglin)), (RenderLayerParent<Mob, PiglinModel<Mob>> renderLayerParent, RenderLayer<Mob, PiglinModel<Mob>> renderLayer) -> {
            if (renderLayer instanceof HumanoidArmorLayer) {
                return Optional.of(new HumanoidArmorLayer<>(renderLayerParent, new HumanoidKneesModel<>(bakery.bakeLayer(this.animatedPiglinInnerArmor)), new HumanoidKneesModel<>(bakery.bakeLayer(this.animatedPiglinOuterArmor))));
            }
            return Optional.empty();
        });
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedZombie, () -> LayerDefinition.create(HumanoidKneesModel.createAnimatedMesh(CubeDeformation.NONE, 0.0F), 64, 64));
        context.registerLayerDefinition(this.animatedZombieInnerArmor, () -> LayerDefinition.create(HumanoidKneesModel.createAnimatedMesh(new CubeDeformation(1.0F), 0.0F), 64, 64));
        context.registerLayerDefinition(this.animatedZombieOuterArmor, () -> LayerDefinition.create(HumanoidKneesModel.createAnimatedMesh(new CubeDeformation(0.5F), 0.0F), 64, 64));
        context.registerLayerDefinition(this.animatedDrowned, () -> LayerDefinition.create(DrownedKneesModel.createAnimatedMesh(CubeDeformation.NONE, 0.0F), 64, 64));
        context.registerLayerDefinition(this.animatedDrownedOuterLayer, () -> LayerDefinition.create(DrownedKneesModel.createAnimatedMesh(new CubeDeformation(0.25F), 0.0F), 64, 64));
        context.registerLayerDefinition(this.animatedDrownedInnerArmor, () -> LayerDefinition.create(DrownedKneesModel.createAnimatedMesh(new CubeDeformation(1.0F), 0.0F), 64, 64));
        context.registerLayerDefinition(this.animatedDrownedOuterArmor, () -> LayerDefinition.create(DrownedKneesModel.createAnimatedMesh(new CubeDeformation(0.5F), 0.0F), 64, 64));
        context.registerLayerDefinition(this.animatedPiglin, PiglinKneesModel::createAnimatedBodyLayer);
        context.registerLayerDefinition(this.animatedPiglinInnerArmor, () -> LayerDefinition.create(HumanoidKneesModel.createAnimatedMesh(new CubeDeformation(1.0F), 0.0F), 64, 64));
        context.registerLayerDefinition(this.animatedPiglinOuterArmor, () -> LayerDefinition.create(HumanoidKneesModel.createAnimatedMesh(new CubeDeformation(0.5F), 0.0F), 64, 64));
    }
}
