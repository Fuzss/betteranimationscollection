package com.fuzs.betteranimationscollection2.handler;

import com.fuzs.betteranimationscollection2.feature.core.Feature;
import com.fuzs.betteranimationscollection2.feature.core.Features;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigBuildHandler {

    private static final ForgeConfigSpec.Builder BUILDER = createBuilder();

    public static ForgeConfigSpec.DoubleValue soundRange;

    private static ForgeConfigSpec.Builder createBuilder() {

        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        setupGeneralConfig(builder);
        for (Feature<? extends Entity> feature : Features.REGISTRY) {

            builder.comment(feature.getDescription());
            builder.push(feature.getName());
            feature.setupConfig(builder);
            builder.pop();
        }

        return builder;
    }

    private static void setupGeneralConfig(ForgeConfigSpec.Builder builder) {

        builder.push("general");
        soundRange = builder.comment("Range in blocks for the sound detection system to look for a mob that made a certain sound. Setting this to 0 will prevent all sound based animations.").defineInRange("sound detection range", 0.5, 0.0, 8.0);
        builder.pop();
    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();

}
