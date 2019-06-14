package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.renderer.render.RenderAnimatedSnowManStick;
import net.minecraft.entity.monster.EntitySnowman;

public class FeatureSnowman extends Feature {

    public static int leftChance;

    public FeatureSnowman() {
        super(EntitySnowman.class, RenderAnimatedSnowManStick::new);
    }

    @Override
    public String getName() {
        return "snowman";
    }

    @Override
    protected String getDescription() {
        return "This makes a snowman's arm swing when it throws a snowball. There are even rumors of some snowman being left-handed!";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        leftChance = ConfigPropHelper.loadPropInt("left chance", this.getCategory(), 5, "Chance for a left handed snowman out of 100.", 0, 100, false);
    }

}
