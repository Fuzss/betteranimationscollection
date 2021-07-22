package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.FlailingEndermanModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class FlailingEndermanElement extends ModelElement {

    public int armsLength;
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
    public void setupModelConfig(OptionsBuilder builder) {

        builder.define("Arms Length", 12).min(1).max(12).comment("Define length for arms.").sync(v -> this.armsLength = v).restart();
        builder.define("Animation Speed", 5).min(1).max(20).comment("Animation swing speed for arms.").sync(v -> this.animationSpeed = v);
        builder.define("Flail While Carrying", true).comment("Flail arms while carrying a block.").sync(v -> this.whileCarrying = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new FlailingEndermanModel<>();
    }

}
