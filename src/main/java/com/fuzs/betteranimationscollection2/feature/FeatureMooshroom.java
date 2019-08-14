package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.MooshroomUdderRenderer;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureMooshroom extends Feature<MooshroomEntity> {

    public static ForgeConfigSpec.BooleanValue nipples;
    public static ForgeConfigSpec.IntValue swing;
    public static ForgeConfigSpec.BooleanValue utterChild;

    public FeatureMooshroom() {
        super(MooshroomEntity.class, MooshroomUdderRenderer::new);
    }

    @Override
    public String getName() {
        return "mooshroom";
    }

    @Override
    public String getDescription() {
        return "This makes the udders on mooshrooms wobble around when they walk. Also makes their udders have nipples.";
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
        nipples = builder.comment("Show four nipples on the mooshroom utter.").define("nipples", true);
        swing = builder.comment("Swing amount of the utter.").defineInRange("swing", 5, 1, 20);
        utterChild = builder.comment("Do calves have an utter.").define("child utter", false);
    }

}
