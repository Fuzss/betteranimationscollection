package betteranimationscollection.client;

import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.BetterAnimationsCollectionClient;
import fuzs.puzzleslib.client.core.ClientCoreServices;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Unit;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

import java.util.concurrent.Executor;

@Mod.EventBusSubscriber(modid = BetterAnimationsCollection.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BetterAnimationsCollectionForgeClient {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ClientCoreServices.FACTORIES.clientModConstructor(BetterAnimationsCollection.MOD_ID).accept(new BetterAnimationsCollectionClient());
    }

    @SubscribeEvent
    public static void onRegisterClientReloadListeners(final RegisterClientReloadListenersEvent evt) {
        evt.registerReloadListener((PreparableReloadListener.PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller2, Executor executor, Executor executor2) -> {
            return preparationBarrier.wait(Unit.INSTANCE).thenRunAsync(BetterAnimationsCollectionClient::applyAnimatedModels, executor2);
        });
    }
}
