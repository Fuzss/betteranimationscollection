package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.JigglySlimeRenderer;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureSlime extends Feature<SlimeEntity> {

    public static ForgeConfigSpec.IntValue squishiness;

    public FeatureSlime() {
        super(SlimeEntity.class, JigglySlimeRenderer::new);
    }

    @Override
    public String getName() {
        return "slime";
    }

    @Override
    public String getDescription() {
        return "A pleasing visual change; this makes the insides of slimes flow around like liquid. They splish-splosh about even more when they jump. The eyes, the mouth, and the core itself all move independently.";
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
        squishiness = builder.comment("Animation swing of inner slime parts.").defineInRange("squishiness", 5, 1, 20);
    }

}
