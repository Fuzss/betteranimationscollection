package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.render.RenderCowUdder;
import net.minecraft.entity.passive.EntityCow;

public class FeatureCow extends Feature {

    public static boolean nipples;

    public FeatureCow() {
        super(EntityCow.class, RenderCowUdder::new);
    }

    @Override
    public String getName() {
        return "cow";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        nipples = ConfigPropHelper.loadPropBoolean("nipples", this.getCategory(), true, "Show four nipples on the cow utter.", true);
    }

}
