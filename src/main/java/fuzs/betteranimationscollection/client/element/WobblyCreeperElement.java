package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.WobblyCreeperModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.layers.CreeperChargeLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class WobblyCreeperElement extends ModelElement {

    public WobbleDirection wobbleDirection;

    @Override
    public String[] getDescription() {

        return new String[]{"Remember that one very popular \"TNT\" music video by CaptainSparklez with the really cute wobbly Creeper?",
                "Well, it's in the game now. The full thing, the real deal, exactly like the video."};
    }

    @Override
    public void constructClient() {

        this.addLayerTransformer(WobblyCreeperModel.class, layerRenderer -> layerRenderer instanceof CreeperChargeLayer, () -> new WobblyCreeperModel<>(2.0F));
    }

    @Override
    public void setupModelConfig(OptionsBuilder builder) {

        builder.define("Wobble Direction", WobbleDirection.SIDE).comment("Different directional behaviour modes for the walking animation.").sync(v -> this.wobbleDirection = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new WobblyCreeperModel<>(0.0F);
    }

    public enum WobbleDirection {

        SIDE, FRONT, CIRCLE, RANDOM
    }

}
