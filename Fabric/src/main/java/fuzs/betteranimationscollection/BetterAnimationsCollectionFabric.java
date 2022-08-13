package fuzs.betteranimationscollection;

import fuzs.puzzleslib.core.CoreServices;
import net.fabricmc.api.ModInitializer;

public class BetterAnimationsCollectionFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CoreServices.FACTORIES.modConstructor(BetterAnimationsCollection.MOD_ID).accept(new BetterAnimationsCollection());
    }
}
