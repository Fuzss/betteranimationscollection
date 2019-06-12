package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.RenderWigglyIronGolemNose;
import net.minecraft.entity.monster.EntityIronGolem;

public class FeatureIronGolem extends Feature {

    public FeatureIronGolem() {
        super(EntityIronGolem.class, RenderWigglyIronGolemNose::new);
    }

    @Override
    public String getName() {
        return "irongolem";
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
