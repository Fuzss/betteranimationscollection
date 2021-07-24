package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.CatTailModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.layers.CatCollarLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class CatTailElement extends ModelElement {

    public int tailLength;
    public int animationSpeed;

    @Override
    public String[] getDescription() {

        return new String[]{"Takes away the stick tails of the current cats and gives them something nicer instead.",
                "Fully animated flowing tails that move while they stand or run, and even curl around their bodies when they sit."};
    }

    @Override
    public void constructClient() {

        this.addLayerTransformer(CatTailModel.class, layerRenderer -> layerRenderer instanceof CatCollarLayer, () -> new CatTailModel<>(0.01F));
    }

    @Override
    public void setupModelConfig(OptionsBuilder builder) {

        builder.define("Tail Length", 15).min(1).max(15).comment("Define tail length.").sync(v -> this.tailLength = v).restart();
        builder.define("Animation Speed", 7).min(1).max(20).comment("Animation swing speed for tail.").sync(v -> this.animationSpeed = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new CatTailModel<>(0.0F);
    }

}
