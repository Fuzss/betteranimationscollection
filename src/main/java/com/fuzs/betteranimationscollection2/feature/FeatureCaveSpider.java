package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.CaveSpiderKneesRenderer;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureCaveSpider extends Feature<CaveSpiderEntity> {

    public FeatureCaveSpider() {
        super(CaveSpiderEntity.class, CaveSpiderKneesRenderer::new);
    }

    @Override
    public String getName() {
        return "cavespider";
    }

    @Override
    public String getDescription() {
        return "A truly stunning visual addition. Cave spiders now finally have the knees they've always dreamed of.";
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
