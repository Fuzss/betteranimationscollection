package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.render.RenderCaveSpiderKnees;
import net.minecraft.entity.monster.EntityCaveSpider;

public class FeatureCaveSpider extends Feature {

    public FeatureCaveSpider() {
        super(EntityCaveSpider.class, RenderCaveSpiderKnees::new);
    }

    @Override
    public String getName() {
        return "cavespider";
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
