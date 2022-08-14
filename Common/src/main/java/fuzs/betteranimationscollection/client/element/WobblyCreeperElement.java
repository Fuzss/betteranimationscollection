package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.WobblyCreeperModel;
import fuzs.betteranimationscollection.mixin.client.accessor.CreeperPowerLayerAccessor;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;

import java.util.function.Function;

public class WobblyCreeperElement extends ModelElementBase {
    public static WobbleDirection wobbleDirection;

    private final ModelLayerLocation animatedCreeper;
    private final ModelLayerLocation animatedCreeperArmor;

    public WobblyCreeperElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedCreeper = modelLayerRegistry.register("animated_creeper");
        this.animatedCreeperArmor = modelLayerRegistry.register("animated_creeper", "armor");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"Remember that one very popular \"TNT\" music video by CaptainSparklez with the really cute wobbly Creeper?", "Well, it's in the game now. The full thing, the real deal, exactly like the video."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, Function<ModelLayerLocation, ModelPart> bakery) {
        context.registerAnimatedModel(CreeperModel.class, () -> new WobblyCreeperModel<>(bakery.apply(this.animatedCreeper), false), layer -> {
            if (layer instanceof CreeperPowerLayer) {
                ((CreeperPowerLayerAccessor) layer).setModel(new WobblyCreeperModel<>(bakery.apply(this.animatedCreeperArmor), true));
            }
        });
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedCreeper, () -> WobblyCreeperModel.createBodyLayer(CubeDeformation.NONE, false));
        context.registerLayerDefinition(this.animatedCreeperArmor, () -> WobblyCreeperModel.createBodyLayer(new CubeDeformation(2.0F), true));
    }

    @Override
    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {
        callback.accept(builder.comment("Different directional behaviour modes for the walking animation.").defineEnum("wobble_direction", WobbleDirection.SIDE), v -> wobbleDirection = v);
    }

    public enum WobbleDirection {
        SIDE, FRONT, CIRCLE, RANDOM
    }
}
