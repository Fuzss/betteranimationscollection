package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.GhastTentaclesModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class GhastTentaclesElement extends ModelElement {

    public int maxTentaclesLength;
    public int animationSpeed;

    @Override
    public String[] getDescription() {

        return new String[]{"Divides ghast tentacles into parts and makes them wiggle realistically, like those tentacle monsters you always see at the movies.",
                "Makes them a little more scary, but ultimately nicer to look at."};
    }

    @Override
    public void setupModelConfig(OptionsBuilder builder) {

        builder.define("Max Tentacles Length", 14).min(2).max(14).comment("Define the max length of tentacles.").sync(v -> this.maxTentaclesLength = v).restart();
        builder.define("Animation Speed", 5).min(1).max(20).comment("Animation swing speed of tentacles.").sync(v -> this.animationSpeed = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new GhastTentaclesModel<>();
    }

}
