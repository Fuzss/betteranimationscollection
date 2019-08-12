package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.AnimatedSnowManStickRenderer;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureSnowman extends Feature<SnowGolemEntity> {

    public static ForgeConfigSpec.IntValue leftChance;

    public FeatureSnowman() {
        super(SnowGolemEntity.class, AnimatedSnowManStickRenderer::new);
    }

    @Override
    public String getName() {
        return "snowman";
    }

    @Override
    public String getDescription() {
        return "This makes a snowman's arm swing when it throws a snowball. There are even rumors of some snowmen being left-handed!";
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
        leftChance = builder.comment("Chance for a left handed snowman out of 100.").defineInRange("left chance", 5, 0, 100);
    }

}
