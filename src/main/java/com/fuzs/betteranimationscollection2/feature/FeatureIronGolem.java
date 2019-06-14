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
        return "A subtle change, this makes iron golems wiggle their big noses whenever they're hurt. Exactly the same animation as for villagers, except for iron golems!";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
