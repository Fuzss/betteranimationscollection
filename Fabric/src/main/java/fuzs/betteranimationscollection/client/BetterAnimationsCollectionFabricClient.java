package fuzs.betteranimationscollection.client;

import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.api.event.entity.living.LivingEvents;
import fuzs.betteranimationscollection.client.handler.RemoteSoundHandler;
import fuzs.puzzleslib.client.core.ClientCoreServices;
import net.fabricmc.api.ClientModInitializer;

public class BetterAnimationsCollectionFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientCoreServices.FACTORIES.clientModConstructor(BetterAnimationsCollection.MOD_ID).accept(new BetterAnimationsCollectionClient());
        registerHandlers();
    }

    private static void registerHandlers() {
        LivingEvents.TICK.register(RemoteSoundHandler.INSTANCE::onLivingTick);
    }
}
