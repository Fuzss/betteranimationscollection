package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.SquidTentaclesRenderer;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureSquid extends Feature<SquidEntity> {

    public static ForgeConfigSpec.IntValue length;

    public FeatureSquid() {
        super(SquidEntity.class, SquidTentaclesRenderer::new);
    }

    @Override
    public String getName() {
        return "squid";
    }

    @Override
    public String getDescription() {
        return "Gives a jellyfish-like effect to the swimming animation of squids. Also makes their tentacles flow more while moving.";
    }

    @Override
    protected String[] incompatibleMods() {
        return new String[]{"mobends"};
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
        length = builder.comment("Define the max length of the tentacles.").defineInRange("length", 8, 1, 8);
    }

}
