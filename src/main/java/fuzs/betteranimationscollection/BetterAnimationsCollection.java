package fuzs.betteranimationscollection;

import fuzs.betteranimationscollection.client.element.*;
import fuzs.puzzleslib.PuzzlesLib;
import fuzs.puzzleslib.element.AbstractElement;
import fuzs.puzzleslib.element.ElementRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"WeakerAccess", "Convert2MethodRef"})
@Mod(BetterAnimationsCollection.MODID)
public class BetterAnimationsCollection {

    public static final String MODID = "betteranimationscollection";
    public static final String NAME = "Better Animations Collection";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    private static final ElementRegistry REGISTRY = PuzzlesLib.create(MODID);

    public static final AbstractElement SOUND_DETECTION = REGISTRY.register("sound_detection", () -> new SoundDetectionElement(), Dist.CLIENT);
    public static final AbstractElement OINKY_PIG = REGISTRY.register("oinky_pig", () -> new OinkyPigElement(), Dist.CLIENT);
    public static final AbstractElement BUCKA_BUCKA_CHICKEN = REGISTRY.register("bucka_bucka_chicken", () -> new BuckaChickenElement(), Dist.CLIENT);
    public static final AbstractElement WIGGLY_GHAST_TENTACLES = REGISTRY.register("wiggly_ghast_tentacles", () -> new GhastTentaclesElement(), Dist.CLIENT);
    public static final AbstractElement SQUIGGLY_SQUID_TENTACLES = REGISTRY.register("squiggly_squid_tentacles", () -> new SquidTentaclesElement(), Dist.CLIENT);
    public static final AbstractElement KNEELING_SHEEP = REGISTRY.register("kneeling_sheep", () -> new KneelingSheepElement(), Dist.CLIENT);
    public static final AbstractElement SPIDER_KNEES = REGISTRY.register("spider_knees", () -> new SpiderKneesElement(), Dist.CLIENT);
    public static final AbstractElement ANIMATED_SNOW_MAN_STICK = REGISTRY.register("animated_snow_man_stick", () -> new SnowManStickElement(), Dist.CLIENT);
    public static final AbstractElement WOBBLY_COW_UDDER = REGISTRY.register("wobbly_cow_udder", () -> new CowUdderElement(), Dist.CLIENT);
    public static final AbstractElement WIGGLY_IRON_GOLEM_NOSE = REGISTRY.register("wiggly_iron_golem_nose", () -> new IronGolemNoseElement(), Dist.CLIENT);
    public static final AbstractElement FLOWY_OCELOT_TAIL = REGISTRY.register("flowy_ocelot_tail", () -> new OcelotTailElement(), Dist.CLIENT);
    public static final AbstractElement CURLY_CAT_TAIL = REGISTRY.register("curly_cat_tail", () -> new CatTailElement(), Dist.CLIENT);
    public static final AbstractElement WIGGLY_VILLAGER_NOSE = REGISTRY.register("wiggly_villager_nose", () -> new VillagerNoseElement(), Dist.CLIENT);
    public static final AbstractElement MAGMA_CUBE_BURGER = REGISTRY.register("magma_cube_burger", () -> new MagmaCubeBurgerElement(), Dist.CLIENT);
    public static final AbstractElement JIGGLY_LIQUIDY_SLIME = REGISTRY.register("jiggly_liquidy_slime", () -> new JigglySlimeElement(), Dist.CLIENT);
    public static final AbstractElement ARM_FLAILING_ENDERMAN = REGISTRY.register("arm_flailing_enderman", () -> new FlailingEndermanElement(), Dist.CLIENT);
    public static final AbstractElement WOBBLY_CREEPER = REGISTRY.register("wobbly_creeper", () -> new WobblyCreeperElement(), Dist.CLIENT);
    public static final AbstractElement PLAYFUL_DOGGY = REGISTRY.register("playful_doggy", () -> new PlayfulDoggyElement(), Dist.CLIENT);

    public BetterAnimationsCollection() {

        PuzzlesLib.setup(true);
        PuzzlesLib.setSideSideOnly();
    }

}
