package com.fuzs.betteranimationscollection2;

import com.fuzs.betteranimationscollection2.feature.Feature;
import com.fuzs.betteranimationscollection2.handler.ConfigHandler;
import com.fuzs.betteranimationscollection2.handler.CustomRenderingHandler;
import com.fuzs.betteranimationscollection2.handler.SoundEventHandler;
import com.fuzs.betteranimationscollection2.helper.FeatureRegistry;
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
public class BetterAnimationsCollection2
{
    public static final String MODID = "betteranimationscollection2";
    public static final String NAME = "Better Animations Collection 2";
    public static final Logger LOGGER = LogManager.getLogger(BetterAnimationsCollection2.NAME);

    public BetterAnimationsCollection2() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        // populate registry
        FeatureRegistry.populate();
        // sort registry alphabetically
        FeatureRegistry.REGISTRY.sort((it, ti) -> it.getName().compareToIgnoreCase(ti.getName()));
        // create config from registry entries
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHandler.SPEC, MODID + ".toml");

    }

    public void clientSetup(final FMLClientSetupEvent evt) {

        MinecraftForge.EVENT_BUS.register(new SoundEventHandler());
        MinecraftForge.EVENT_BUS.register(new CustomRenderingHandler());
        // register entity renderers
        FeatureRegistry.REGISTRY.forEach(Feature::register);
        
    }

}
