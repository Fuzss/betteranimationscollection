package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.JigglySlimeModel;
import fuzs.betteranimationscollection.mixin.client.accessor.SlimeOuterLayerAccessor;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SlimeOuterLayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.Optional;

public class JigglySlimeElement extends ModelElementBase {
    public static int animationSpeed;

    private final ModelLayerLocation animatedSlime;
    private final ModelLayerLocation animatedSlimeOuter;

    public JigglySlimeElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedSlime = modelLayerRegistry.register("animated_slime");
        this.animatedSlimeOuter = modelLayerRegistry.register("animated_slime", "outer");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"A pleasing visual change; this makes the insides of slimes flow around like liquid.",
                "They splish-splosh about even more when they jump. The eyes, the mouth, and the core itself all move independently."};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, EntityModelSet bakery) {
        context.registerAnimatedModel(SlimeModel.class, () -> new JigglySlimeModel<>(bakery.bakeLayer(this.animatedSlime)), (RenderLayerParent<LivingEntity, SlimeModel<LivingEntity>> renderLayerParent, RenderLayer<LivingEntity, SlimeModel<LivingEntity>> renderLayer) -> {
            if (renderLayer instanceof SlimeOuterLayer) {
                ((SlimeOuterLayerAccessor<?>) renderLayer).setModel(new JigglySlimeModel<>(bakery.bakeLayer(this.animatedSlimeOuter)));
            }

            return Optional.empty();
        });
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedSlime, SlimeModel::createInnerBodyLayer);
        context.registerLayerDefinition(this.animatedSlimeOuter, SlimeModel::createOuterBodyLayer);
    }

    @Override
    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {
        callback.accept(builder.comment("Animation swing speed of inner slime parts.").defineInRange("animation_speed", 5, 1, 20), v -> animationSpeed = v);
    }
}
