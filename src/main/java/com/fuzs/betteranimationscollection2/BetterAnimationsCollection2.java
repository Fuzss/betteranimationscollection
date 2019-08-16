package com.fuzs.betteranimationscollection2;

import com.fuzs.betteranimationscollection2.handler.ConfigHandler;
import com.fuzs.betteranimationscollection2.feature.Feature;
import com.fuzs.betteranimationscollection2.helper.FeatureRegistry;
import com.fuzs.betteranimationscollection2.handler.SoundEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;

@SuppressWarnings({"WeakerAccess", "unused"})
@Mod(
        modid = BetterAnimationsCollection2.MODID,
        name = BetterAnimationsCollection2.NAME,
        version = BetterAnimationsCollection2.VERSION,
        acceptedMinecraftVersions = BetterAnimationsCollection2.RANGE,
        clientSideOnly = BetterAnimationsCollection2.CLIENT,
        guiFactory = BetterAnimationsCollection2.GUI,
        certificateFingerprint = BetterAnimationsCollection2.FINGERPRINT
)
public class BetterAnimationsCollection2
{
    public static final String MODID = "betteranimationscollection2";
    public static final String NAME = "Better Animations Collection 2";
    public static final String VERSION = "@VERSION@";
    public static final String RANGE = "[1.9.4]";
    public static final boolean CLIENT = true;
    public static final String GUI = "com.fuzs.betteranimationscollection2.helper.GuiFactory";
    public static final String FINGERPRINT = "@FINGERPRINT@";

    public static final Logger LOGGER = LogManager.getLogger(BetterAnimationsCollection2.NAME);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
        // populate registry
        FeatureRegistry.populate();
        // sort registry alphabetically
        FeatureRegistry.REGISTRY.sort((it, ti) -> it.getName().compareToIgnoreCase(ti.getName()));
        // create config from registry entries
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        // register registry renderers
        FeatureRegistry.REGISTRY.forEach(Feature::register);

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(new SoundEventHandler());
        
    }

    @EventHandler
    public void fingerprintViolation(FMLFingerprintViolationEvent event) {
        LOGGER.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
    }
}
