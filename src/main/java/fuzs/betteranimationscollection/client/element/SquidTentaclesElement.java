package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.SquidTentaclesModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class SquidTentaclesElement extends ModelElement {

    public int length;

    @Override
    public String[] getDescription() {

        return new String[]{"Gives a jellyfish-like effect to the swimming animation of squids.",
                "Also makes their tentacles flow more while moving."};
    }

    @Override
    public void setupClientConfig(OptionsBuilder builder) {

        super.setupClientConfig(builder);
        builder.define("Max Length", 8).min(1).max(8).comment("Define the max length of the tentacles.").sync(v -> this.length = v).restart();
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new SquidTentaclesModel<>();
    }

}
