package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.PlayfulDoggyModel;
import fuzs.puzzleslib.api.config.v3.ValueCallback;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.WolfArmorLayer;
import net.minecraft.world.entity.animal.Wolf;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class PlayfulDoggyElement extends ModelElement {
    public static int tailLength;
    public static boolean fluffyTail;
    public static int animationSpeed;
    public static SittingBehaviour sittingBehaviour;

    private final ModelLayerLocation animatedWolf;
    private final ModelLayerLocation animatedWolfArmor;

    public PlayfulDoggyElement(BiFunction<String, String, ModelLayerLocation> factory) {
        this.animatedWolf = factory.apply("animated_wolf", "main");
        this.animatedWolfArmor = factory.apply("animated_wolf", "armor");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"Changes wolf tails to be fluffier and flowier, wagging realistically while they stand and run.",
                "Also makes tamed wolves lie down instead of sitting. Hold up some meat and they'll roll over, too."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelBakery bakery) {
        context.<Wolf, WolfModel<Wolf>>registerAnimatedModel(WolfModel.class, () -> new PlayfulDoggyModel<>(bakery.bakeLayer(this.animatedWolf)), (RenderLayerParent<Wolf, WolfModel<Wolf>> renderLayerParent, RenderLayer<Wolf, WolfModel<Wolf>> renderLayer) -> {
            if (renderLayer instanceof WolfArmorLayer wolfArmorLayer) {
                wolfArmorLayer.model = new PlayfulDoggyModel<>(bakery.bakeLayer(this.animatedWolfArmor));
            }
            return Optional.empty();
        });
    }

    @Override
    public void onRegisterLayerDefinitions(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> context) {
        context.accept(this.animatedWolf, () -> LayerDefinition.create(PlayfulDoggyModel.createAnimatedBodyLayer(CubeDeformation.NONE), 64, 32));
        context.accept(this.animatedWolfArmor, () -> LayerDefinition.create(PlayfulDoggyModel.createAnimatedBodyLayer(new CubeDeformation(0.2F)), 64, 32));
    }

    @Override
    public void setupModelConfig(ModConfigSpec.Builder builder, ValueCallback callback) {
        callback.accept(builder.comment("Define tail length.").defineInRange("tail_length", PlayfulDoggyModel.WOLF_TAIL_LENGTH, 1, PlayfulDoggyModel.WOLF_TAIL_LENGTH), v -> tailLength = v);
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
