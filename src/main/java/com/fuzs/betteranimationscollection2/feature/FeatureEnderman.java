package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.FlailingEndermanRenderer;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureEnderman extends Feature<EndermanEntity> {

    public static ForgeConfigSpec.IntValue speed;
    public static ForgeConfigSpec.BooleanValue whileCarrying;

    public FeatureEnderman() {
        super(EndermanEntity.class, FlailingEndermanRenderer::new);
    }

    @Override
    public String getName() {
        return "enderman";
    }

    @Override
    public String getDescription() {
        return "If an enderman is angry it will wave its arms around wildly while chasing its target. Suits their twisted nature very well.";
    }

    @Override
    public boolean hasCompatibility() {
        return false;
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
        speed = builder.comment("Animation swing of the arms.").defineInRange("animation swing", 5, 1, 20);
        whileCarrying = builder.comment("Flail arms while carrying a block.").define("while carrying", false);
    }

}
