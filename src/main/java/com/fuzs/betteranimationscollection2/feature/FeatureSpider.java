package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.render.RenderSpiderKnees;
import net.minecraft.entity.monster.EntitySpider;

public class FeatureSpider extends Feature {

    public FeatureSpider() {
        super(EntitySpider.class, RenderSpiderKnees::new);
    }

    @Override
    public String getName() {
        return "spider";
    }

    @Override
    protected String[] incompatibleMods() {
        return new String[]{"mobends"};
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
