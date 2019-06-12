package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.RenderFluffyWolfTail;
import net.minecraft.entity.passive.EntityWolf;

public class FeatureWolf extends Feature {

    public FeatureWolf() {
        super(EntityWolf.class, RenderFluffyWolfTail::new);
    }

    @Override
    public String getName() {
        return "wolf";
    }

    @Override
    protected String getDescription() {
        return "";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
