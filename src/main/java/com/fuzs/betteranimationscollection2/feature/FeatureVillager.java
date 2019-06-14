package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.RenderWigglyVillagerNose;
import net.minecraft.entity.passive.EntityVillager;

public class FeatureVillager extends Feature {

    public FeatureVillager() {
        super(EntityVillager.class, RenderWigglyVillagerNose::new);
    }

    @Override
    public String getName() {
        return "villager";
    }

    @Override
    protected String getDescription() {
        return "A subtle change, this makes villagers wiggle their big noses whenever they make their unique \"hmmm\" sound! It's a small change, but who doesn't get a kick out of it?";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
