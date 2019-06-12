package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.RenderHuskKnees;
import net.minecraft.entity.monster.EntityHusk;

public class FeatureHusk extends Feature {

    public FeatureHusk() {
        super(EntityHusk.class, RenderHuskKnees::new);
    }

    @Override
    public String getName() {
        return "husk";
    }

    @Override
    protected String getDescription() {
        return "";
    }

    @Override
    protected String[] incompatibleMods() {
        return new String[]{"mobends"};
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
