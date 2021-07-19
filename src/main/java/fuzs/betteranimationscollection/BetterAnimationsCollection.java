package fuzs.betteranimationscollection;

import fuzs.betteranimationscollection.client.element.*;
import fuzs.puzzleslib.PuzzlesLib;
import fuzs.puzzleslib.element.AbstractElement;
import fuzs.puzzleslib.element.ElementRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"WeakerAccess", "unused", "Convert2MethodRef"})
@Mod(BetterAnimationsCollection.MODID)
public class BetterAnimationsCollection {

    public static final String MODID = "betteranimationscollection";
    public static final String NAME = "Better Animations Collection";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    private static final ElementRegistry REGISTRY = PuzzlesLib.create(MODID);

    public static final AbstractElement SOUND_DETECTION = REGISTRY.register("sound_detection", () -> new SoundDetectionElement(), Dist.CLIENT);
    public static final AbstractElement OINKY_PIG = REGISTRY.register("oinky_pig", () -> new OinkyPigElement(), Dist.CLIENT);
    public static final AbstractElement BUCKA_CHICKEN = REGISTRY.register("bucka_chicken", () -> new BuckaChickenElement(), Dist.CLIENT);
    public static final AbstractElement GHAST_TENTACLES = REGISTRY.register("ghast_tentacles", () -> new GhastTentaclesElement(), Dist.CLIENT);
    public static final AbstractElement SQUID_TENTACLES = REGISTRY.register("squid_tentacles", () -> new SquidTentaclesElement(), Dist.CLIENT);
    public static final AbstractElement KNEELING_SHEEP = REGISTRY.register("kneeling_sheep", () -> new KneelingSheepElement(), Dist.CLIENT);
    public static final AbstractElement SPIDER_KNEES = REGISTRY.register("spider_knees", () -> new SpiderKneesElement(), Dist.CLIENT);
    public static final AbstractElement SNOWMAN_STICK = REGISTRY.register("snowman_stick", () -> new SnowManStickElement(), Dist.CLIENT);
    public static final AbstractElement COW_UDDER = REGISTRY.register("cow_udder", () -> new CowUdderElement(), Dist.CLIENT);

    public BetterAnimationsCollection() {

        PuzzlesLib.setup(true);
        PuzzlesLib.setSideSideOnly();
    }

}
