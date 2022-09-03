package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.FlailingEndermanModel;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CarriedBlockLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.monster.EnderMan;

import java.util.Optional;

public class FlailingEndermanElement extends ModelElementBase {
    public static int animationSpeed;
    public static boolean whileCarrying;

    private final ModelLayerLocation animatedEnderman;

    public FlailingEndermanElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedEnderman = modelLayerRegistry.register("animated_enderman");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"If an enderman is angry it will wave its arms around wildly while chasing its target.",
                "Suits their twisted nature very well."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelSet bakery) {
        context.registerAnimatedModel(EndermanModel.class, () -> new FlailingEndermanModel<>(bakery.bakeLayer(this.animatedEnderman)), (RenderLayerParent<EnderMan, EndermanModel<EnderMan>> renderLayerParent, RenderLayer<EnderMan, EndermanModel<EnderMan>> renderLayer) -> {
            if (renderLayer instanceof CarriedBlockLayer) {
                return Optional.of(new CarriedBlockLayer(renderLayerParent, Minecraft.getInstance().getBlockRenderer()));
            }
            return Optional.empty();
        });
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedEnderman, FlailingEndermanModel::createAnimatedBodyLayer);
    }

    @Override
    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {
        callback.accept(builder.comment("Animation swing speed for arms.").defineInRange("animation_speed", 5, 1, 20), v -> animationSpeed = v);
        callback.accept(builder.comment("Flail arms while carrying a block.").define("fail_while_carrying", true), v -> whileCarrying = v);
    }
}
