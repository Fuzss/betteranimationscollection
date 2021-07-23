package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.layers.FlailingHeldBlockLayer;
import fuzs.betteranimationscollection.client.renderer.entity.model.FlailingEndermanModel;
import fuzs.betteranimationscollection.mixin.client.accessor.LivingRendererAccessor;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.HeldBlockLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import java.util.List;

public class FlailingEndermanElement extends ModelElement {

    public int animationSpeed;
    public boolean whileCarrying;

    @Override
    public String[] getDescription() {

        return new String[]{"If an enderman is angry it will wave its arms around wildly while chasing its target.",
                "Suits their twisted nature very well."};
    }

    @Override
    public void constructClient() {

//        this.addLayerTransformer(layerRenderer -> layerRenderer instanceof SlimeGelLayer, () -> new JigglySlimeModel<>(0));
    }

    @Override
    public void setupClient2() {

        super.setupClient2();
        Minecraft.getInstance().getEntityRenderDispatcher().renderers.forEach((entityType, renderer) -> {

            if (entityType.equals(EntityType.ENDERMAN)) {

                List<? extends LayerRenderer<?, ?>> layers = ((LivingRendererAccessor<?, ?>) renderer).getLayers();
                layers.removeIf(r -> r instanceof HeldBlockLayer);
                ((EndermanRenderer) renderer).addLayer(new FlailingHeldBlockLayer((EndermanRenderer) renderer));
            }
        });
    }

    @Override
    public void setupModelConfig(OptionsBuilder builder) {

        builder.define("Animation Speed", 5).min(1).max(20).comment("Animation swing speed for arms.").sync(v -> this.animationSpeed = v);
        builder.define("Flail While Carrying", true).comment("Flail arms while carrying a block.").sync(v -> this.whileCarrying = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new FlailingEndermanModel<>();
    }

}
