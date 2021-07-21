package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.OinkyPigModel;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;

public class OinkyPigElement extends SoundModelElement {

    @Override
    public String[] getDescription() {

        return new String[]{"A very subtle animation, makes a pig's snout move when it oinks.",
                "It only moves up and down ever so slightly, but it's there. Just a little bit more life for your livestock."};
    }

    @Override
    public void constructClient() {

        this.addLayerTransformer(layerRenderer -> layerRenderer instanceof SaddleLayer, () -> new OinkyPigModel<>(0.5F));
        this.defaultSounds.add(SoundEvents.PIG_AMBIENT.getRegistryName());
        this.defaultSounds.add(new ResourceLocation("snowpig", "entity.snow_pig.ambient"));
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
