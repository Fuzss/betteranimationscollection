package com.fuzs.betteranimationscollection2.handler;

import com.fuzs.betteranimationscollection2.BetterAnimationsCollection2;
import com.fuzs.betteranimationscollection2.feature.Feature;
import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.helper.FeatureRegistry;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;
    public static double soundRange;

    public static void init(File configFile) {

        if (config == null) {
            config = new Configuration(configFile, BetterAnimationsCollection2.VERSION);
            loadConfiguration();
        }

    }

    private static void loadConfiguration() {

        config.getCategory(Configuration.CATEGORY_GENERAL);
        FeatureRegistry.REGISTRY.forEach(Feature::setupConfig);
        soundRange = ConfigPropHelper.loadPropDouble("sound detection range", Configuration.CATEGORY_GENERAL, 0.5, "Range for the sound detection system to look for a mob that made a certain sound. Setting this to 0 will prevent all sound based animations.", 0.0, 5.0, false);

        if (config.hasChanged()) {
            config.save();
        }

    }

    @SubscribeEvent
    public void onConfigurationChanged(OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(BetterAnimationsCollection2.MODID)) {
            loadConfiguration();
        }
    }
}
