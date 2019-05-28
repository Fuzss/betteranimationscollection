package com.fuzs.betteranimationscollection2;

import com.fuzs.betteranimationscollection2.render.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = BetterAnimationsCollection2.MODID,
        name = BetterAnimationsCollection2.NAME,
        version = BetterAnimationsCollection2.VERSION,
        acceptedMinecraftVersions = BetterAnimationsCollection2.RANGE,
        certificateFingerprint = BetterAnimationsCollection2.FINGERPRINT
)
public class BetterAnimationsCollection2
{
    public static final String MODID = "betteranimationscollection2";
    public static final String NAME = "Better Animations Collection 2";
    public static final String VERSION = "@VERSION@";
    public static final String RANGE = "[1.11, 1.12.2]";
    public static final String FINGERPRINT = "@FINGERPRINT@";

    public static final Logger LOGGER = LogManager.getLogger(BetterAnimationsCollection2.NAME);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(EntitySlime.class, RenderJigglySlime::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityOcelot.class, RenderFlowyOcelotTails::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCow.class, RenderCowUdder::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMooshroom.class, RenderMooshroomUdder::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCreeper.class, RenderWobblyCreeper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderman.class, RenderWavingEnderman::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySheep.class, RenderKneelingSheep::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMagmaCube.class, RenderMagmaCubeBurger::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGhast.class, RenderGhastTentacles::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPig.class, RenderOinkyPig::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityWolf.class, RenderFluffyWolfTail::new);

        if (!Loader.isModLoaded("mobends")) {
            RenderingRegistry.registerEntityRenderingHandler(EntitySpider.class, RenderSpiderKnees::new);
            RenderingRegistry.registerEntityRenderingHandler(EntityCaveSpider.class, RenderCaveSpiderKnees::new);
            RenderingRegistry.registerEntityRenderingHandler(EntitySquid.class, RenderSquidTentacles::new);
        }

        //MinecraftForge.EVENT_BUS.register(new SnowmanAttackHelper());
        
    }

    @EventHandler
    public void fingerprintViolation(FMLFingerprintViolationEvent event) {
        LOGGER.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
    }
}
