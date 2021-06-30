package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.AnimatedSnowManStickModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.util.SoundEvents;

public class AnimatedSnowManStickElement extends SoundModelElement {

    public AnimatedSnowManStickElement() {

        super(SnowGolemEntity.class);
        this.addDefaultSound(SoundEvents.SNOW_GOLEM_SHOOT);
    }

    @Override
    public String[] getDescription() {

        return new String[]{"This makes a snowman's arm swing when it throws a snowball."};
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new AnimatedSnowManStickModel<>();
    }

}
