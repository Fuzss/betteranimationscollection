package com.fuzs.betteranimationscollection2.handler;

import com.fuzs.betteranimationscollection2.BetterAnimationsCollection2;
import com.fuzs.betteranimationscollection2.feature.Feature;
import com.fuzs.betteranimationscollection2.helper.FeatureRegistry;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterAnimationsCollection2.MODID)
public class ConfigHandler {

    private static final ForgeConfigSpec.Builder BUILDER = createBuilder();

    @SuppressWarnings("WeakerAccess")
    public static ForgeConfigSpec.DoubleValue soundRange;

    private static ForgeConfigSpec.Builder createBuilder() {

        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("general");
        soundRange = builder.comment("Range for the sound detection system to look for a mob that made a certain sound. Setting this to 0 will prevent all sound based animations.").defineInRange("sound detection range", 0.5, 0.0, 5.0);
        builder.pop();

        for (Feature feature : FeatureRegistry.REGISTRY) {
            builder.comment(feature.getDescription());
            builder.push(feature.getName());
            feature.setupConfig(builder);
            builder.pop();
        }

        return builder;

    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();

}
