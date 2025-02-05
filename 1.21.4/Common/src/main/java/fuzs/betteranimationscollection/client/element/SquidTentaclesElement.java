package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.model.SquidTentaclesModel;
import fuzs.puzzleslib.api.client.renderer.v1.RenderPropertyKey;
import fuzs.puzzleslib.api.config.v3.ValueCallback;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.SquidRenderState;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class SquidTentaclesElement extends SingletonModelElement<Squid, SquidRenderState, SquidModel> {
    public static final RenderPropertyKey<Vec3> DELTA_MOVEMENT_PROPERTY = key("delta_movement");

    public static int tentaclesLength;

    private final ModelLayerLocation animatedSquid;
    private final ModelLayerLocation animatedSquidBaby;

    public SquidTentaclesElement() {
        super(Squid.class, SquidRenderState.class, SquidModel.class);
        this.animatedSquid = this.registerModelLayer("animated_squid");
        this.animatedSquidBaby = this.registerModelLayer("animated_squid_baby");
    }

    @Override
    public String[] getDescriptionComponent() {
        return new String[]{
                "Gives a jellyfish-like effect to the swimming animation of squids; generally just makes their tentacles flow more while moving."
        };
    }

    @Override
    protected void setAnimatedModel(LivingEntityRenderer<?, SquidRenderState, SquidModel> entityRenderer, EntityRendererProvider.Context context) {
        setAnimatedAgeableModel(entityRenderer,
                new SquidTentaclesModel(context.bakeLayer(this.animatedSquid)),
                new SquidTentaclesModel(context.bakeLayer(this.animatedSquidBaby)));
    }

    @Override
    public void onRegisterLayerDefinitions(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> context) {
        context.accept(this.animatedSquid, SquidTentaclesModel::createAnimatedBodyLayer);
        context.accept(this.animatedSquidBaby,
                () -> SquidTentaclesModel.createAnimatedBodyLayer().apply(SquidModel.BABY_TRANSFORMER));
    }

    @Override
    protected void extractRenderState(Squid entity, SquidRenderState renderState, float partialTick) {
        super.extractRenderState(entity, renderState, partialTick);
        RenderPropertyKey.setRenderProperty(renderState, DELTA_MOVEMENT_PROPERTY, entity.getDeltaMovement());
    }

    @Override
    public void setupModelConfig(ModConfigSpec.Builder builder, ValueCallback callback) {
        callback.accept(builder.comment("Define length for squid tentacles.")
                .defineInRange("tentacles_length",
                        SquidTentaclesModel.SQUID_TENTACLES_LENGTH,
                        1,
                        SquidTentaclesModel.SQUID_TENTACLES_LENGTH), v -> tentaclesLength = v);
    }
}
