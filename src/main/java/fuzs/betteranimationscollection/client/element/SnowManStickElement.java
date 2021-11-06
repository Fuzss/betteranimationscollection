package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.SnowManStickModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.util.SoundEvents;

public class SnowManStickElement extends SoundModelElement {

    public SnowManStickElement() {

        this.defaultSounds.add(SoundEvents.SNOW_GOLEM_SHOOT);
    }

    @Override
    public String[] getDescription() {

        return new String[]{"This makes a snowman's arm swing when it throws a snowball."};
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new SnowManStickModel<>();
    }

    @Override
    protected Class<? extends MobEntity> getMobClazz() {

        return SnowGolemEntity.class;
    }

}
