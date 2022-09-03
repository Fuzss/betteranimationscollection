package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.PlayfulDoggyModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;

import java.util.function.Function;

public class PlayfulDoggyElement extends ModelElementBase {
    public static int tailLength;
    public static boolean fluffyTail;
    public static int animationSpeed;
    public static SittingBehaviour sittingBehaviour;

    private final ModelLayerLocation animatedWolf;

    public PlayfulDoggyElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedWolf = modelLayerRegistry.register("animated_wolf");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"Changes wolf tails to be fluffier and flowier, wagging realistically while they stand and run.",
                "Also makes tamed wolves lie down instead of sitting. Hold up some meat and they'll roll over, too."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, Function<ModelLayerLocation, ModelPart> bakery) {
        context.registerAnimatedModel(WolfModel.class, () -> new PlayfulDoggyModel<>(bakery.apply(this.animatedWolf)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedWolf, PlayfulDoggyModel::createAnimatedBodyLayer);
    }

    @Override
    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {
        callback.accept(builder.comment("Define tail length.").defineInRange("tail_length", 7, 1, 7), v -> tailLength = v);
        callback.accept(builder.comment("Make wolf tail fluffy.").define("fluffy_tail", true), v -> fluffyTail = v);
        callback.accept(builder.comment("Animation swing speed for tail.").defineInRange("animation_speed", 5, 1, 20), v -> animationSpeed = v);
        callback.accept(builder.comment("Pose and behaviour when sitting.", "By default makes wolves lie down instead, and roll over when a nearby player is holding a piece meat.").defineEnum("sitting_behaviour", SittingBehaviour.LIE_DOWN_AND_BEG_FOR_MEAT), v -> sittingBehaviour = v);
    }

    public enum SittingBehaviour {
        DEFAULT,
        LIE_DOWN,
        ROLL_OVER,
        LIE_DOWN_AND_BEG_FOR_MEAT;

        public boolean lieDown() {
            return this != DEFAULT;
        }

        public boolean rollOver() {
            return this == ROLL_OVER || this == LIE_DOWN_AND_BEG_FOR_MEAT;
        }

        public boolean begForMeat() {
            return this == LIE_DOWN_AND_BEG_FOR_MEAT;
        }
    }
}
