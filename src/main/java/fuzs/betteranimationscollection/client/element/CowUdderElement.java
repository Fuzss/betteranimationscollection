package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.CowUdderModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class CowUdderElement extends ModelElement {

    public int swingAmount;
    public boolean hideNipples;

    @Override
    public String[] getDescription() {

        return new String[]{"This makes the udders on cows wobble around when they walk.", "Also makes their udders have nipples."};
    }

    @Override
    public void setupClientConfig(OptionsBuilder builder) {

        super.setupClientConfig(builder);
        builder.define("Swing Amount", 5).min(1).max(20).comment("Swing amount of the utter when the cow is walking.").sync(v -> this.swingAmount = v).restart();
        builder.define("Hide Nipples", false).comment("Don't render four nipples on every cow's utter.").sync(v -> this.hideNipples = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new CowUdderModel<>();
    }

}
