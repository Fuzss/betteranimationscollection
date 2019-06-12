package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.renderer.render.RenderCowUdder;
import net.minecraft.entity.passive.EntityCow;

public class FeatureCow extends Feature {

    public static boolean nipples;
    public static int swing;
    public static boolean utterChild;

    public FeatureCow() {
        super(EntityCow.class, RenderCowUdder::new);
    }

    @Override
    public String getName() {
        return "cow";
    }

    @Override
    protected String getDescription() {
        return "";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        nipples = ConfigPropHelper.loadPropBoolean("nipples", this.getCategory(), true, "Show four nipples on the cow utter.", false);
        swing = ConfigPropHelper.loadPropInt("swing", this.getCategory(), 5, "Swing amount of the utter.", 1, 20, false);
        utterChild = ConfigPropHelper.loadPropBoolean("child utter", this.getCategory(), false, "Do calves have an utter.", false);
    }

}
