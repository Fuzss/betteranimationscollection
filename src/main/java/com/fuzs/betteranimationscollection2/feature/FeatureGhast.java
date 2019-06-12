package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.renderer.render.RenderGhastTentacles;
import net.minecraft.entity.monster.EntityGhast;

public class FeatureGhast extends Feature {

    public static int length;
    public static int speed;

    public FeatureGhast() {
        super(EntityGhast.class, RenderGhastTentacles::new);
    }

    @Override
    public String getName() {
        return "ghast";
    }

    @Override
    protected String getDescription() {
        return "Divides ghast tentacles into parts and makes them wiggle realistically, like those tentacle monsters you always see at the movies. Makes them a little more scary, but ultimately nicer to look at.";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        length = ConfigPropHelper.loadPropInt("length", this.getCategory(), 14, "Define the max length of the tentacles.", 2, 14, true);
        speed = ConfigPropHelper.loadPropInt("speed", this.getCategory(), 5, "Animation speed of the tentacles.", 1, 20, false);
    }

}
