package fuzs.betteranimationscollection.client;

import com.google.common.collect.ImmutableList;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.puzzleslib.client.core.ClientCoreServices;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Unit;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class BetterAnimationsCollectionFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientCoreServices.FACTORIES.clientModConstructor(BetterAnimationsCollection.MOD_ID).accept(new BetterAnimationsCollectionClient());
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new IdentifiableResourceReloadListener() {

            @Override
            public ResourceLocation getFabricId() {
                return new ResourceLocation(BetterAnimationsCollection.MOD_ID, "animated_models");
            }

            @Override
            public Collection<ResourceLocation> getFabricDependencies() {
                return ImmutableList.of(ResourceReloadListenerKeys.MODELS);
            }

            @Override
            public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller2, Executor executor, Executor executor2) {
                return preparationBarrier.wait(Unit.INSTANCE).thenRunAsync(BetterAnimationsCollectionClient::applyAnimatedModels, executor2);
            }
        });
    }
}
