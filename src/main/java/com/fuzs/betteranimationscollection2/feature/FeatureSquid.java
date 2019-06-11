package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.config.ConfigHandler;
import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.render.RenderSquidTentacles;
import net.minecraft.entity.passive.EntitySquid;

public class FeatureSquid extends Feature {

    public static int length;

    public FeatureSquid() {
        super(EntitySquid.class, RenderSquidTentacles::new);
    }

    @Override
    public String getName() {
        return "squid";
    }

    @Override
    protected String[] incompatibleMods() {
        return new String[]{"mobends"};
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        length = ConfigPropHelper.loadPropInt("length", this.getCategory(), 8, "Define the max length of the tentacles.", 1, 8, true);
    }

}
