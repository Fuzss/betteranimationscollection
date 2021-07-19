package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.GhastTentaclesModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class GhastTentaclesElement extends ModelElement {

    public int length;
    public int speed;

    @Override
    public String[] getDescription() {

        return new String[]{"Divides ghast tentacles into parts and makes them wiggle realistically, like those tentacle monsters you always see at the movies.",
                "Makes them a little more scary, but ultimately nicer to look at."};
    }

    @Override
    public void setupClientConfig(OptionsBuilder builder) {

        super.setupClientConfig(builder);
        builder.define("Max Length", 14).min(2).max(14).comment("Define the max length of the tentacles.").sync(v -> this.length = v).restart();
        builder.define("Animation Swing", 5).min(1).max(20).comment("Animation swing of the tentacles.").sync(v -> this.speed = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new GhastTentaclesModel<>();
    }

}
