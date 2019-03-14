package fuzs.bac;

import com.fantasticsource.mctools.WorldEventDistributor;
import fuzs.bac.model.ModelOinkyPig;
import fuzs.bac.render.*;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.*;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = BetterAnimationsCollection.MODID, name = BetterAnimationsCollection.NAME, version = BetterAnimationsCollection.VERSION, acceptedMinecraftVersions = BetterAnimationsCollection.AVERSIONS)
public class BetterAnimationsCollection {
    public static final String MODID = "bac";
    public static final String NAME = "Better Animations Collection";
    public static final String VERSION = "1.0";
    public static final String AVERSIONS = "[1.11,1.12.2]";

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(EntitySlime.class, new IRenderFactory<EntitySlime>() {
            public Render<? super EntitySlime> createRenderFor(RenderManager manager) {
                return new RenderJigglySlime(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntitySpider.class, new IRenderFactory<EntitySpider>() {
            public Render<? super EntitySpider> createRenderFor(RenderManager manager) {
                return new RenderSpiderKnees<>(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityCaveSpider.class, new IRenderFactory<EntityCaveSpider>() {
            public Render<? super EntityCaveSpider> createRenderFor(RenderManager manager) {
                return new RenderCaveSpiderKnees(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityOcelot.class, new IRenderFactory<EntityOcelot>() {
            public Render<? super EntityOcelot> createRenderFor(RenderManager manager) {
                return new RenderFlowyOcelotTails(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityCow.class, new IRenderFactory<EntityCow>() {
            public Render<? super EntityCow> createRenderFor(RenderManager manager) {
                return new RenderCowUdders(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntitySquid.class, new IRenderFactory<EntitySquid>() {
            public Render<? super EntitySquid> createRenderFor(RenderManager manager) {
                return new RenderSquidTentacles(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityWolf.class, new IRenderFactory<EntityWolf>() {
            public Render<? super EntityWolf> createRenderFor(RenderManager manager) {
                return new RenderFluffyWolfTail(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityPig.class, new IRenderFactory<EntityPig>() {
            public Render<? super EntityPig> createRenderFor(RenderManager manager) {
                return new RenderOinkyPig(manager);
            }
        });

    }
}
