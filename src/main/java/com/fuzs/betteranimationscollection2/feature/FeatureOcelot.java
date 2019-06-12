package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.renderer.render.RenderFlowyOcelotTails;
import net.minecraft.entity.passive.EntityOcelot;

public class FeatureOcelot extends Feature {

    public static boolean liedown;
    public static boolean lieBedOnly;

    public FeatureOcelot() {
        super(EntityOcelot.class, RenderFlowyOcelotTails::new);
    }

    @Override
    public String getName() {
        return "ocelot";
    }

    @Override
    protected String getDescription() {
        return "";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        liedown = ConfigPropHelper.loadPropBoolean("lie down", this.getCategory(), true, "Lie down instead of sitting.", false);
        lieBedOnly = ConfigPropHelper.loadPropBoolean("lie bed only", this.getCategory(), false, "Lie down on beds only instead of sitting.", false);
    }

}
