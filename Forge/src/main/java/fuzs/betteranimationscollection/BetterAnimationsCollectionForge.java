package fuzs.betteranimationscollection;

import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.puzzleslib.core.CoreServices;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod(BetterAnimationsCollection.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterAnimationsCollectionForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        CoreServices.FACTORIES.modConstructor(BetterAnimationsCollection.MOD_ID).accept(new BetterAnimationsCollection());
    }
}
