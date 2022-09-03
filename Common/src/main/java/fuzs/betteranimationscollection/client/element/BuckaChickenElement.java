package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.BuckaChickenModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.animal.Chicken;

public class BuckaChickenElement extends SoundDetectionElement {
    public static boolean slimBill;
    public static boolean moveHead;
    public static boolean moveWattles;
    public static boolean moveWings;
    public static int headAnimationSpeed;
    public static int wattlesAnimationSpeed;
    public static int wingAnimationSpeed;

    private final ModelLayerLocation animatedChicken;

    public BuckaChickenElement(ModelLayerRegistry modelLayerRegistry) {
        super(Chicken.class, SoundEvents.CHICKEN_AMBIENT);
        this.animatedChicken = modelLayerRegistry.register("animated_chicken");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"This one makes chicken beaks open and close when they cluck.",
                "When they strut their heads move back and forth, the red thing under their beak swings around and their wings flap a little. Just like the real deal!"};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelSet bakery) {
        context.registerAnimatedModel(ChickenModel.class, () -> new BuckaChickenModel<>(bakery.bakeLayer(this.animatedChicken)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedChicken, BuckaChickenModel::createAnimatedBodyLayer);
    }

    @Override
    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {
        super.setupModelConfig(builder, callback);
        callback.accept(builder.comment("Make bill a lot slimmer so chickens look less like ducks.").define("slim_bill", true), v -> slimBill = v);
        callback.accept(builder.comment("Move head back and forth when chicken is walking.").define("move_head", true), v -> moveWattles = v);
        callback.accept(builder.comment("Wiggle chin when chicken is walking.").define("wiggle_wattles", true), v -> moveWings = v);
        callback.accept(builder.comment("Flap wings when chicken is walking.").define("flap_wings", false), v -> moveWings = v);
        callback.accept(builder.comment("Move head back and forth when chicken is walking.").defineInRange("head_animation_speed", 4, 1, 20), v -> headAnimationSpeed = v);
        callback.accept(builder.comment("Animation swing speed for wattles movement.").defineInRange("wattles_animation_speed", 5, 1, 20), v -> wattlesAnimationSpeed = v);
        callback.accept(builder.comment("Animation swing speed of wing flapping.").defineInRange("wing_animation_speed", 3, 1, 20), v -> wingAnimationSpeed = v);
    }
}
