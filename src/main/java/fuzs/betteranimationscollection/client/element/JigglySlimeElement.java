package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.JigglySlimeModel;
import fuzs.betteranimationscollection.client.renderer.entity.model.OinkyPigModel;
import fuzs.betteranimationscollection.client.renderer.entity.model.SquidTentaclesModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.client.renderer.entity.layers.SlimeGelLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class JigglySlimeElement extends ModelElement {

    public int squishiness;

    @Override
    public String[] getDescription() {

        return new String[]{"A pleasing visual change; this makes the insides of slimes flow around like liquid.",
                "They splish-splosh about even more when they jump. The eyes, the mouth, and the core itself all move independently."};
    }

    @Override
    public void constructClient() {

        this.addLayerTransformer(layerRenderer -> layerRenderer instanceof SlimeGelLayer, () -> new JigglySlimeModel<>(0));
    }

    @Override
    public void setupModelConfig(OptionsBuilder builder) {

        builder.define("Squishiness", 5).min(1).max(20).comment("Animation swing of inner slime parts.").sync(v -> this.squishiness = v).restart();
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new JigglySlimeModel<>(16);
    }

}
