package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.RenderSpiderKnees;
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
    protected String getDescription() {
        return "A truly stunning visual addition. Spiders now finally have the knees they've always dreamed of.";
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
