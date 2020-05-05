package com.fuzs.betteranimationscollection2;

import com.fuzs.betteranimationscollection2.feature.core.Feature;
import com.fuzs.betteranimationscollection2.feature.core.Features;
import com.fuzs.betteranimationscollection2.handler.ConfigBuildHandler;
import com.fuzs.betteranimationscollection2.handler.SoundSyncHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"WeakerAccess", "unused"})
@Mod(BetterAnimationsCollection2.MODID)
public class BetterAnimationsCollection2 {

    public static final String MODID = "betteranimationscollection2";
    public static final String NAME = "Better Animations Collection 2";
    public static final Logger LOGGER = LogManager.getLogger(BetterAnimationsCollection2.NAME);

    public BetterAnimationsCollection2() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        // populate registry
        Features.create();
        // create config from registry entries
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigBuildHandler.SPEC, MODID + ".toml");
    }

    public void clientSetup(final FMLClientSetupEvent evt) {

        MinecraftForge.EVENT_BUS.register(new SoundSyncHandler());
        // register entity renderers
        Features.REGISTRY.forEach(Feature::register);
    }

}
