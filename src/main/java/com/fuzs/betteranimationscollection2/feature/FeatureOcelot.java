package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.render.RenderFlowyOcelotTails;
import net.minecraft.entity.passive.EntityOcelot;

public class FeatureOcelot extends Feature {

    public FeatureOcelot() {
        super(EntityOcelot.class, RenderFlowyOcelotTails::new);
    }

    @Override
    public String getName() {
        return "ocelot";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
