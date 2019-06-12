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
        return "";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
