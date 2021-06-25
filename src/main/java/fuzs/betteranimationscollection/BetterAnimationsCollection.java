package fuzs.betteranimationscollection;

import fuzs.betteranimationscollection.client.element.BuckaChickenElement;
import fuzs.betteranimationscollection.client.element.GhastTentaclesElement;
import fuzs.betteranimationscollection.client.element.OinkyPigElement;
import fuzs.betteranimationscollection.client.element.SoundDetectionElement;
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
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    public static final AbstractElement SOUND_DETECTION = PuzzlesLib.register(MODID, "sound_detection", SoundDetectionElement::new, Dist.CLIENT);
    public static final AbstractElement OINKY_PIG = PuzzlesLib.register(MODID, "oinky_pig", OinkyPigElement::new, Dist.CLIENT);
    public static final AbstractElement BUCKA_CHICKEN = PuzzlesLib.register(MODID, "bucka_chicken", BuckaChickenElement::new, Dist.CLIENT);
    public static final AbstractElement GHAST_TENTACLES = PuzzlesLib.register(MODID, "ghast_tentacles", GhastTentaclesElement::new, Dist.CLIENT);

    public BetterAnimationsCollection() {

        PuzzlesLib.setup(true);
        PuzzlesLib.setSideSideOnly();
    }

}
