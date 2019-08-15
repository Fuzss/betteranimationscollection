package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.WolfTailRenderer;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureWolf extends Feature<WolfEntity> {

    public static ForgeConfigSpec.BooleanValue fluffy;
    public static ForgeConfigSpec.BooleanValue lieDown;
    public static ForgeConfigSpec.BooleanValue rollOver;
    public static ForgeConfigSpec.IntValue swing;

    public FeatureWolf() {
        super(WolfEntity.class, WolfTailRenderer::new);
    }

    @Override
    public String getName() {
        return "wolf";
    }

    @Override
    public String getDescription() {
        return "Changes wolf tails to be fluffier and flowier, wagging realistically while they stand and run. Also makes tamed wolves lie down instead of sitting. Hold up some meat and they'll roll over, too.";
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
        fluffy = builder.comment("Make wolf tail fluffy.").define("fluffy", true);
        lieDown = builder.comment("Lie down instead of sitting.").define("lie down", true);
        rollOver = builder.comment("Roll over when sitting and the player is holding some meat.", "\"lie down\" has to be enabled.").define("roll over", true);
        swing = builder.comment("Swing amount of the tail.").defineInRange("tail swing", 5, 1, 20);
    }

}
