package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.SpitfulLlamaModel;
import fuzs.betteranimationscollection.mixin.client.accessor.LlamaDecorLayerAccessor;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.model.LlamaModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.LlamaDecorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.animal.horse.Llama;

import java.util.Optional;
import java.util.function.Function;

public class SpitfulLlamaElement extends ModelElementBase {
    private final ModelLayerLocation animatedLlama;
    private final ModelLayerLocation animatedLlamaDecor;

    public SpitfulLlamaElement(ModelLayerRegistry modelLayerRegistry) {
        this.animatedLlama = modelLayerRegistry.register("animated_llama");
        this.animatedLlamaDecor = modelLayerRegistry.register("animated_llama", "decor");
    }

    @Override
    public String[] modelDescription() {
        return new String[]{"This one makes llamas open their mouth when spitting. How have they been doing that before?!"};
    }

    @Override
    void onRegisterAnimatedModels(AnimatedModelsContext context, Function<ModelLayerLocation, ModelPart> bakery) {
        context.registerAnimatedModel(LlamaModel.class, () -> new SpitfulLlamaModel<>(bakery.apply(this.animatedLlama)), (RenderLayerParent<Llama, LlamaModel<Llama>> renderLayerParent, RenderLayer<Llama, LlamaModel<Llama>> renderLayer) -> {
            if (renderLayer instanceof LlamaDecorLayer) {
                ((LlamaDecorLayerAccessor) renderLayer).setModel(new SpitfulLlamaModel<>(bakery.apply(this.animatedLlamaDecor)));
            }
            return Optional.empty();
        });
    }

    @Override
    public void onRegisterLayerDefinitions(ClientModConstructor.LayerDefinitionsContext context) {
        context.registerLayerDefinition(this.animatedLlama, () -> SpitfulLlamaModel.createAnimatedBodyLayer(CubeDeformation.NONE));
        context.registerLayerDefinition(this.animatedLlamaDecor, () -> SpitfulLlamaModel.createAnimatedBodyLayer(new CubeDeformation(0.5F)));
    }
}
