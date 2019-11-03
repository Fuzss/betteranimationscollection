package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.RenderFlowyOcelotTails;
import net.minecraft.entity.passive.CatEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureCat extends Feature<CatEntity> {

    public static ForgeConfigSpec.IntValue swing;
    public static ForgeConfigSpec.IntValue length;

    public FeatureCat() {
        super(CatEntity.class, RenderFlowyOcelotTails::new);
    }

    @Override
    public String getName() {
        return "ocelot";
    }

    @Override
    public String getDescription() {
        return "Takes away the stick tails of the current cats and gives them something nicer instead. Fully animated flowing tails that move while they stand or run.";
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
        swing = builder.comment("Swing amount of the tail.").defineInRange("tail swing", 7, 1, 20);
        length = builder.comment("Define the length of the tail.").defineInRange("length", 8, 1, 8);
    }

}
