package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.render.RenderFluffyWolfTail;
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
    public void setupConfig() {
        super.setupConfig();
    }

}
