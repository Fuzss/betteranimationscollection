package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.SpiderKneesRenderer;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureSpider extends Feature<SpiderEntity> {

    public FeatureSpider() {
        super(SpiderEntity.class, SpiderKneesRenderer::new);
    }

    @Override
    public String getName() {
        return "spider";
    }

    @Override
    public String getDescription() {
        return "A truly stunning visual addition. Spiders now finally have the knees they've always dreamed of.";
    }

    @Override
    protected String[] incompatibleMods() {
        return new String[]{"mobends"};
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
    }

}
