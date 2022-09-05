package fuzs.betteranimationscollection.client;

import com.google.common.collect.Maps;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.*;
import fuzs.betteranimationscollection.config.ClientConfig;
import fuzs.betteranimationscollection.mixin.client.accessor.EntityRenderDispatcherAccessor;
import fuzs.betteranimationscollection.mixin.client.accessor.LivingEntityRendererAccessor;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Unit;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.Supplier;

public class BetterAnimationsCollectionClient implements ClientModConstructor {
    private static final ModelLayerRegistry MODEL_LAYER_REGISTRY = ModelLayerRegistry.of(BetterAnimationsCollection.MOD_ID);
    public static final Map<ResourceLocation, ModelElementBase> MODEL_ELEMENTS = Maps.newHashMap();
    private static final Map<Class<? extends EntityModel<?>>, ModelElementBase.AnimatedModelData<?, ?>> ANIMATED_MODEL_DATA = Maps.newIdentityHashMap();

    private static boolean allowResourceReloading;

    @Override
    public void onConstructMod() {
        bootstrap();
        // do this here instead of in common construct since we need all elements to be registered first, which can only happen on the client though
        BetterAnimationsCollection.CONFIG.bakeConfigs(BetterAnimationsCollection.MOD_ID);
    }

    private static void bootstrap() {
        registerModelElement("oinky_pig", OinkyPigElement::new);
        registerModelElement("bucka_bucka_chicken", BuckaChickenElement::new);
        registerModelElement("wiggly_ghast_tentacles", GhastTentaclesElement::new);
        registerModelElement("squiggly_squid_tentacles", SquidTentaclesElement::new);
        registerModelElement("kneeling_sheep", KneelingSheepElement::new);
        registerModelElement("spider_knees", SpiderKneesElement::new);
        registerModelElement("animated_snow_man_stick", SnowGolemStickElement::new);
        registerModelElement("wobbly_cow_udder", CowUdderElement::new);
        registerModelElement("wiggly_iron_golem_nose", IronGolemNoseElement::new);
        registerModelElement("flowy_ocelot_tail", OcelotTailElement::new);
        registerModelElement("curly_cat_tail", CatTailElement::new);
        registerModelElement("wiggly_villager_nose", VillagerNoseElement::new);
        registerModelElement("magma_cube_burger", MagmaCubeBurgerElement::new);
        registerModelElement("jiggly_liquidy_slime", JigglySlimeElement::new);
        registerModelElement("arm_flailing_enderman", FlailingEndermanElement::new);
        registerModelElement("wobbly_creeper", WobblyCreeperElement::new);
        registerModelElement("playful_doggy", PlayfulDoggyElement::new);
        registerModelElement("bending_humanoid_knees", HumanoidKneesElement::new);
        registerModelElement("familiar_horse", FamiliarHorseElement::new);
        registerModelElement("spitful_llama", SpitfulLlamaElement::new);
    }

    private static void registerModelElement(String identifier, Function<ModelLayerRegistry, ModelElementBase> factory) {
        MODEL_ELEMENTS.put(new ResourceLocation(BetterAnimationsCollection.MOD_ID, identifier), factory.apply(MODEL_LAYER_REGISTRY));
    }

    @Override
    public void onClientSetup() {
        buildAnimatedModels(false, false);
        // add this listener later, so it doesn't interfere with initial config loading
        BetterAnimationsCollection.CONFIG.getHolder(ClientConfig.class).accept(() -> buildAnimatedModels(true, true));
    }

    public static void buildAnimatedModels(boolean checkDirty, boolean reloadResourcePacks) {
        if (!checkDirty || MODEL_ELEMENTS.values().stream().anyMatch(ModelElementBase::isDirty)) {
            ANIMATED_MODEL_DATA.clear();
            ModelElementBase.AnimatedModelsContext context = new ModelElementBase.AnimatedModelsContext() {

                @SuppressWarnings("unchecked")
                @Override
                public <T extends LivingEntity, M extends EntityModel<T>> void registerAnimatedModel(Class<? super M> vanillaModelClazz, Supplier<? extends M> animatedModel, ModelElementBase.LayerTransformer<T, M> layerTransformer) {
                    ANIMATED_MODEL_DATA.put((Class<? extends EntityModel<?>>) vanillaModelClazz, new ModelElementBase.AnimatedModelData<>(vanillaModelClazz, animatedModel, layerTransformer));
                }
            };
            MODEL_ELEMENTS.values().forEach(element -> {
                element.registerAnimatedModels(context, ModelElementBase.EntityModelBakery.of(Minecraft.getInstance()::getEntityModels));
            });
            if (reloadResourcePacks && allowResourceReloading) {
                Minecraft.getInstance().reloadResourcePacks();
            }
        }
    }

    @Override
    public void onRegisterLayerDefinitions(LayerDefinitionsContext context) {
        MODEL_ELEMENTS.values().forEach(element -> element.onRegisterLayerDefinitions(context));
    }

    @Override
    public void onRegisterClientReloadListeners(ClientReloadListenersContext context) {
        context.registerReloadListener("animated_models", (PreparableReloadListener.PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller2, Executor executor, Executor executor2) -> {
            return preparationBarrier.wait(Unit.INSTANCE).thenRunAsync(BetterAnimationsCollectionClient::applyAnimatedModels, executor2);
        });
    }

    @SuppressWarnings("unchecked")
    public static <T extends LivingEntity, M extends EntityModel<T>> void applyAnimatedModels() {
        for (Map.Entry<EntityType<?>, EntityRenderer<?>> entry : ((EntityRenderDispatcherAccessor) Minecraft.getInstance().getEntityRenderDispatcher()).getRenderers().entrySet()) {
            if (!BetterAnimationsCollection.CONFIG.get(ClientConfig.class).mobBlacklist.contains(entry.getKey()) && entry.getValue() instanceof LivingEntityRenderer<?,?>) {
                RenderLayerParent<T, M> livingRenderer = (LivingEntityRenderer<T, M>) entry.getValue();
                // this shouldn't be null, but some mods do it anyway...
                if (livingRenderer.getModel() == null) continue;
                ModelElementBase.AnimatedModelData<T, M> animatedModelData = (ModelElementBase.AnimatedModelData<T, M>) ANIMATED_MODEL_DATA.get(livingRenderer.getModel().getClass());
                if (animatedModelData != null) {
                    ((LivingEntityRendererAccessor<T, M>) livingRenderer).setModel(animatedModelData.animatedModel().get());
                    ListIterator<RenderLayer<T, M>> iterator = ((LivingEntityRendererAccessor<T, M>) livingRenderer).getLayers().listIterator();
                    while (iterator.hasNext()) {
                        animatedModelData.layerTransformer().apply(livingRenderer, iterator.next()).ifPresent(iterator::set);
                    }
                }
            }
        }
        // let vanilla run this once during start-up, afterwards it's ok for us to trigger resource pack reloads as well
        allowResourceReloading = true;
    }
}
