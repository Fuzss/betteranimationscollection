package fuzs.tbac2;

import fuzs.tbac2.render.*;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraftforge.fml.client.registry.IRenderFactory;
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
    public static final String AVERSIONS = "[1.12.2]";

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(EntitySlime.class, new IRenderFactory<EntitySlime>() {
            public Render<? super EntitySlime> createRenderFor(RenderManager manager) {
                return new RenderJigglySlime(manager);
            }
        });

        if (!Loader.isModLoaded("mobends")) {
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

            RenderingRegistry.registerEntityRenderingHandler(EntitySquid.class, new IRenderFactory<EntitySquid>() {
                public Render<? super EntitySquid> createRenderFor(RenderManager manager) {
                    return new RenderSquidTentacles(manager);
                }
            });
        }

        RenderingRegistry.registerEntityRenderingHandler(EntityOcelot.class, new IRenderFactory<EntityOcelot>() {
            public Render<? super EntityOcelot> createRenderFor(RenderManager manager) {
                return new RenderFlowyOcelotTails(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityCow.class, new IRenderFactory<EntityCow>() {
            public Render<? super EntityCow> createRenderFor(RenderManager manager) {
                return new RenderCowUdder(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityMooshroom.class, new IRenderFactory<EntityMooshroom>() {
            public Render<? super EntityMooshroom> createRenderFor(RenderManager manager) {
                return new RenderMooshroomUdder(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityWolf.class, new IRenderFactory<EntityWolf>() {
            public Render<? super EntityWolf> createRenderFor(RenderManager manager) {
                return new RenderFluffyWolfTail(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityCreeper.class, new IRenderFactory<EntityCreeper>() {
            public Render<? super EntityCreeper> createRenderFor(RenderManager manager) {
                return new RenderWobblyCreeper(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityEnderman.class, new IRenderFactory<EntityEnderman>() {
            public Render<? super EntityEnderman> createRenderFor(RenderManager manager) {
                return new RenderWavingEnderman(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntitySheep.class, new IRenderFactory<EntitySheep>() {
            public Render<? super EntitySheep> createRenderFor(RenderManager manager) {
                return new RenderKneelingSheep(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityMagmaCube.class, new IRenderFactory<EntityMagmaCube>() {
            public Render<? super EntityMagmaCube> createRenderFor(RenderManager manager) {
                return new RenderMagmaCubeBurger(manager);
            }
        });

        /*TODO
        Snowman

        Villager
        Pig
        Chicken

        Ghast
        Wolf
        */

    }
}
