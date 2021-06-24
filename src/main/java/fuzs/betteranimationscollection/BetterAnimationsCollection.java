package fuzs.betteranimationscollection;

import fuzs.betteranimationscollection.client.element.OinkyPigElement;
import fuzs.betteranimationscollection.client.element.SyncSoundElement;
import fuzs.puzzleslib.PuzzlesLib;
import fuzs.puzzleslib.element.AbstractElement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"WeakerAccess", "unused"})
@Mod(BetterAnimationsCollection.MODID)
public class BetterAnimationsCollection {

    public static final String MODID = "betteranimationscollection";
    public static final String NAME = "Better Animations Collection";
    public static final Logger LOGGER = LogManager.getLogger(BetterAnimationsCollection.NAME);

    public static final AbstractElement SYNC_SOUND = PuzzlesLib.register("sync_sound", SyncSoundElement::new, Dist.CLIENT);
    public static final AbstractElement OINKY_PIG = PuzzlesLib.register("oinky_pig", OinkyPigElement::new, Dist.CLIENT);

    public BetterAnimationsCollection() {

        PuzzlesLib.setup(MODID, true);
        PuzzlesLib.setSideSideOnly();
    }

}
