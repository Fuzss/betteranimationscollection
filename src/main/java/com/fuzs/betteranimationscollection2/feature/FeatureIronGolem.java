package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.render.RenderWigglyIronGolemNose;
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
    public void setupConfig() {
        super.setupConfig();
    }

}
