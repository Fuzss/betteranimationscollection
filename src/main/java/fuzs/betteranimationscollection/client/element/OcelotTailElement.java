package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.OcelotTailModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class OcelotTailElement extends ModelElement {

    public int length;
    public int swing;

    @Override
    public String[] getDescription() {

        return new String[]{"Takes away the stick tails of the current ocelots and gives them something nicer instead.",
                "Fully animated flowing tails that move while they stand or run."};
    }

    @Override
    public void setupClientConfig(OptionsBuilder builder) {

        super.setupClientConfig(builder);
        builder.define("Max Length", 15).min(1).max(15).comment("Define the max length of the tail.").sync(v -> this.length = v).restart();
        builder.define("Tail Swing", 7).min(1).max(20).comment("Swing amount of the tail.").sync(v -> this.swing = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new OcelotTailModel<>();
    }

}
