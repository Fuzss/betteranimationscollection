package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.WanderingTraderNoseRenderer;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureWanderingTrader extends Feature<WanderingTraderEntity> {

    public FeatureWanderingTrader() {
        super(WanderingTraderEntity.class, WanderingTraderNoseRenderer::new);
    }

    @Override
    public String getName() {
        return "wanderingtrader";
    }

    @Override
    public String getDescription() {
        return "A subtle change, this makes wandering traders wiggle their big noses whenever they make their unique \"hmmm\" sound! It's a small change, but who doesn't get a kick out of it?";
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
    }

}
