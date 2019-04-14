package fuzs.tbac2;

import fuzs.tbac2.fixes.*;
import fuzs.tbac2.render.*;
import fuzs.tbac2.util.SnowmanAttackHelper;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BetterAnimationsCollection.MODID, name = BetterAnimationsCollection.NAME, version = BetterAnimationsCollection.VERSION, acceptedMinecraftVersions = BetterAnimationsCollection.AVERSIONS)
public class BetterAnimationsCollection {

    public static final String MODID = "tbac2";
    public static final String NAME = "The Better Animations Collection 2";
    public static final String VERSION = "1.0";
    public static final String AVERSIONS = "[1.11,1.12.2]";

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {

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

        MinecraftForge.EVENT_BUS.register(new ParticleCreateGolem());
        MinecraftForge.EVENT_BUS.register(new ParticleCreateWither());
        MinecraftForge.EVENT_BUS.register(new ParticleAnimalBreed());
        MinecraftForge.EVENT_BUS.register(new ParticleBlockAction());
        MinecraftForge.EVENT_BUS.register(new SnowmanAttackHelper());
    }

}
