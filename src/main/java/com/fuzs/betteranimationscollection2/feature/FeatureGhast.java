package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.GhastTentaclesRenderer;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureGhast extends Feature<GhastEntity> {

    public static ForgeConfigSpec.IntValue length;
    public static ForgeConfigSpec.IntValue speed;

    public FeatureGhast() {
        super(GhastEntity.class, GhastTentaclesRenderer::new);
    }

    @Override
    public String getName() {
        return "ghast";
    }

    @Override
    public String getDescription() {
        return "Divides ghast tentacles into parts and makes them wiggle realistically, like those tentacle monsters you always see at the movies. Makes them a little more scary, but ultimately nicer to look at.";
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
        length = builder.comment("Define the max length of the tentacles.").defineInRange("max length", 14, 2, 14);
        speed = builder.comment("Animation swing of the tentacles.").defineInRange("animation swing", 5, 1, 20);
    }

}
