package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.renderer.render.RenderBuckaBuckaChicken;
import net.minecraft.entity.passive.EntityChicken;

public class FeatureChicken extends Feature {

    public static boolean movehead;
    public static boolean movechin;
    public static boolean movewings;
    public static int headspeed;
    public static int chinspeed;
    public static int wingspeed;

    public FeatureChicken() {
        super(EntityChicken.class, RenderBuckaBuckaChicken::new);
    }

    @Override
    public String getName() {
        return "chicken";
    }

    @Override
    protected String getDescription() {
        return "";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        movehead = ConfigPropHelper.loadPropBoolean("move head", this.getCategory(), true, "Move head back and forth when the entity is walking.", false);
        headspeed = ConfigPropHelper.loadPropInt("head speed", this.getCategory(), 4, "Animation speed for the head movement.", 1, 20, false);
        movechin = ConfigPropHelper.loadPropBoolean("wiggle chin", this.getCategory(), true, "Wiggle chin when the entity is walking.", false);
        chinspeed = ConfigPropHelper.loadPropInt("chin speed", this.getCategory(), 5, "Animation speed for the chin movement.", 1, 20, false);
        movewings = ConfigPropHelper.loadPropBoolean("flap wings", this.getCategory(), true, "Flap wings when the entity is walking.", false);
        wingspeed = ConfigPropHelper.loadPropInt("wings speed", this.getCategory(), 3, "Animation speed of the wing flapping.", 1, 20, false);
    }

}
