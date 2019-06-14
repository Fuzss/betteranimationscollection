package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.renderer.render.RenderFluffyWolfTail;
import net.minecraft.entity.passive.EntityWolf;

public class FeatureWolf extends Feature {

    public static boolean fluffy;
    public static boolean lieDown;
    public static boolean rollOver;
    public static int swing;

    public FeatureWolf() {
        super(EntityWolf.class, RenderFluffyWolfTail::new);
    }

    @Override
    public String getName() {
        return "wolf";
    }

    @Override
    protected String getDescription() {
        return "Changes wolf tails to be fluffier and flowier, wagging realistically while they stand and run. Also makes tamed wolves lie down instead of sitting. Hold up some meat and they'll roll over, too.";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        fluffy = ConfigPropHelper.loadPropBoolean("fluffy", this.getCategory(), true, "Make wolf tail fluffy.", true);
        lieDown = ConfigPropHelper.loadPropBoolean("lie down", this.getCategory(), true, "Lie down instead of sitting.", false);
        rollOver = ConfigPropHelper.loadPropBoolean("roll over", this.getCategory(), false, "Roll over when sitting and the player is holding some meat. \"lie down\" has to be enabled.", false);
        swing = ConfigPropHelper.loadPropInt("tail swing", this.getCategory(), 5, "Swing amount of the tail.", 1, 20, false);
    }

}
