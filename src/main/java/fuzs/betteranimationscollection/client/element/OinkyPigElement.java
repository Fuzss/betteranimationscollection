package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.OinkyPigModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.SoundEvents;

public class OinkyPigElement extends SoundModelElement {

    public boolean floatyEars;
    public int earAnimationSpeed;

    public OinkyPigElement() {

        this.defaultSounds.add(SoundEvents.PIG_AMBIENT);
        this.defaultSounds.add("snowpig", "entity.snow_pig.ambient");
    }

    @Override
    public String[] getDescription() {

        return new String[]{"A very subtle animation, makes a pig's snout move when it oinks.",
                "It only moves up and down ever so slightly, but it's there. Just a little bit more life for your livestock.",
                "And don't you forget about the floaty ears! Pretty big for such a small animal."};
    }

    @Override
    public void constructClient() {

        this.addLayerTransformer(OinkyPigModel.class, layerRenderer -> layerRenderer instanceof SaddleLayer, () -> new OinkyPigModel<>(0.5F));
    }

    @Override
    public void setupModelConfig(OptionsBuilder builder) {

        super.setupModelConfig(builder);
        builder.define("Floaty Ears", true).comment("Fancy ears for pigs, just like piglins have them.").sync(v -> this.floatyEars = v);
        builder.define("Ear Animation Speed", 10).min(1).max(20).comment("Animation swing speed for ear floatiness.").sync(v -> this.earAnimationSpeed = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new OinkyPigModel<>();
    }

    @Override
    protected Class<? extends MobEntity> getMobClazz() {

        return PigEntity.class;
    }

}
