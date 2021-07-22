package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.CowUdderModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class CowUdderElement extends ModelElement {

    public int animationSpeed;
    public boolean showNipples;

    @Override
    public String[] getDescription() {

        return new String[]{"This makes the udders on cows wobble around when they walk.",
                "Also makes their udders have nipples."};
    }

    @Override
    public void setupModelConfig(OptionsBuilder builder) {

        builder.define("Animation Speed", 5).min(1).max(20).comment("Animation swing speed of utter when the cow is walking.").sync(v -> this.animationSpeed = v).restart();
        builder.define("Show Nipples", true).comment("Don't render four nipples on every cow's utter.").sync(v -> this.showNipples = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new CowUdderModel<>();
    }

}
