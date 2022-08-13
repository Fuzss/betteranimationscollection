package fuzs.betteranimationscollection.client;

import com.google.common.collect.Maps;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.ModelElementBase;
import fuzs.betteranimationscollection.client.element.WobblyCreeperElement;
import fuzs.betteranimationscollection.config.ClientConfig;
import fuzs.betteranimationscollection.mixin.client.accessor.EntityRenderDispatcherAccessor;
import fuzs.betteranimationscollection.mixin.client.accessor.LivingEntityRendererAccessor;
import fuzs.puzzleslib.client.core.ClientModConstructor;
import fuzs.puzzleslib.client.model.geom.ModelLayerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class BetterAnimationsCollectionClient implements ClientModConstructor {
    private static final ModelLayerRegistry MODEL_LAYER_REGISTRY = ModelLayerRegistry.of(BetterAnimationsCollection.MOD_ID);
    public static final Map<ResourceLocation, ModelElementBase> MODEL_ELEMENTS = Maps.newHashMap();
    private static final Map<Class<? extends EntityModel<?>>, ModelElementBase.AnimatedModelData<?, ?>> ANIMATED_MODEL_DATA = Maps.newIdentityHashMap();
    private static boolean allowResourceReloading;

    @Override
    public void onConstructMod() {
        registerModelElement("wobbly_creeper", WobblyCreeperElement::new);
        // do this here instead of in common construct since we need all elements to be registered first, which can only happen on the client though
        BetterAnimationsCollection.CONFIG.bakeConfigs(BetterAnimationsCollection.MOD_ID);
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

                @Override
                public <T extends LivingEntity, M extends EntityModel<T>> void registerAnimatedModel(Class<M> vanillaModelClazz, Supplier<? extends M> animatedModel, Consumer<RenderLayer<?, ?>> layerTransformer) {
                    ANIMATED_MODEL_DATA.put(vanillaModelClazz, new ModelElementBase.AnimatedModelData<>(vanillaModelClazz, animatedModel, layerTransformer));
                }
            };
            MODEL_ELEMENTS.values().forEach(element -> {
                element.registerAnimatedModels(context, modelLayerLocation -> Minecraft.getInstance().getEntityModels().bakeLayer(modelLayerLocation));
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

    @SuppressWarnings("unchecked")
    public static void applyAnimatedModels() {
        for (Map.Entry<EntityType<?>, EntityRenderer<?>> entry : ((EntityRenderDispatcherAccessor) Minecraft.getInstance().getEntityRenderDispatcher()).getRenderers().entrySet()) {
            if (!BetterAnimationsCollection.CONFIG.get(ClientConfig.class).mobBlacklist.contains(entry.getKey()) && entry.getValue() instanceof LivingEntityRenderer<?,?> renderer) {
                ModelElementBase.AnimatedModelData<?, ?> animatedModelData = ANIMATED_MODEL_DATA.get(renderer.getModel().getClass());
                if (animatedModelData != null) {
                    ((LivingEntityRendererAccessor<?, EntityModel<?>>) renderer).setModel(animatedModelData.animatedModel().get());
                    ((LivingEntityRendererAccessor<?, ?>) renderer).getLayers().forEach(layer -> animatedModelData.layerTransformer().accept(layer));
                }
            }
        }
    }
}
