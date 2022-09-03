package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.CowUdderModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;

import java.util.function.Function;

public class CowUdderElement extends ModelElementBase {
    public static int animationSpeed;
    public static boolean showNipples;
    public static boolean calfUtter;

    private final ModelLayerLocation animatedCow;

    public CowUdderElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedCow = modelLayerRegistry.register("animated_cow");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"This makes the udders on cows wobble around when they walk.",
                "Also makes their udders have nipples."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, Function<ModelLayerLocation, ModelPart> bakery) {
        context.registerAnimatedModel(CowModel.class, () -> new CowUdderModel<>(bakery.apply(this.animatedCow)));
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedCow, CowUdderModel::createAnimatedBodyLayer);
    }

    @Override
    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {
        callback.accept(builder.comment("Animation swing speed of utter when the cow is walking.").defineInRange("animation_speed", 5, 1, 20), v -> animationSpeed = v);
        callback.accept(builder.comment("Render tiny nipples on a cow's utter.").define("show_nipples", true), v -> showNipples = v);
        callback.accept(builder.comment("Should calves show an utter.").define("calf_utter", false), v -> calfUtter = v);
    }
}
