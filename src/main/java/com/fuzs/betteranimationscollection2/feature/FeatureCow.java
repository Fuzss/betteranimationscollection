package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.CowUdderRenderer;
import net.minecraft.entity.passive.CowEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureCow extends Feature<CowEntity> {

    public static ForgeConfigSpec.BooleanValue nipples;
    public static ForgeConfigSpec.IntValue swing;
    public static ForgeConfigSpec.BooleanValue utterChild;

    public FeatureCow() {
        super(CowEntity.class, CowUdderRenderer::new);
    }

    @Override
    public String getName() {
        return "cow";
    }

    @Override
    public String getDescription() {
        return "This makes the udders on cows wobble around when they walk. Also makes their udders have nipples.";
    }

    @Override
    protected String[] incompatibleMods() {
        return new String[]{"quark"};
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
        nipples = builder.comment("Show four nipples on the cow utter.").define("nipples", true);
        swing = builder.comment("Swing amount of the utter.").defineInRange("swing", 5, 1, 20);
        utterChild = builder.comment("Do calves have an utter.").define("child utter", false);
    }

}
