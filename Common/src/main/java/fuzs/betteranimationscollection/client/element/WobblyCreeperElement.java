package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.WobblyCreeperModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.monster.Creeper;

import java.util.Optional;

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
        return new String[]{"Remember that one very popular \"TNT\" music video by CaptainSparklez with the really cute wobbly Creeper?",
                "Well, it's in the game now. The full thing, the real deal, exactly like the video."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelBakery bakery) {
        context.<Creeper, CreeperModel<Creeper>>registerAnimatedModel(CreeperModel.class, () -> new WobblyCreeperModel<>(bakery.bakeLayer(this.animatedCreeper), false), (RenderLayerParent<Creeper, CreeperModel<Creeper>> renderLayerParent, RenderLayer<Creeper, CreeperModel<Creeper>> renderLayer) -> {
            if (renderLayer instanceof CreeperPowerLayer) {
                return Optional.of(new CreeperPowerLayer(renderLayerParent, bakery.get()) {
                    private final WobblyCreeperModel<Creeper> model = new WobblyCreeperModel<>(bakery.bakeLayer(WobblyCreeperElement.this.animatedCreeperArmor), true);

                    @Override
                    protected EntityModel<Creeper> model() {
                        return this.model;
                    }
                });
            }
            return Optional.empty();
        });
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedCreeper, () -> WobblyCreeperModel.createAnimatedBodyLayer(CubeDeformation.NONE, false));
        context.registerLayerDefinition(this.animatedCreeperArmor, () -> WobblyCreeperModel.createAnimatedBodyLayer(new CubeDeformation(2.0F), true));
    }

    @Override
    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {
        callback.accept(builder.comment("Different directional behaviour modes for the walking animation.").defineEnum("wobble_direction", WobbleDirection.SIDE), v -> wobbleDirection = v);
    }

    public enum WobbleDirection {
        SIDE, FRONT, CIRCLE, RANDOM
    }
}
