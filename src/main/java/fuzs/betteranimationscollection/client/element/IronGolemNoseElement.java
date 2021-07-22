package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.IronGolemNoseModel;
import fuzs.betteranimationscollection.client.renderer.entity.model.SnowManStickModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.util.SoundEvents;

public class IronGolemNoseElement extends ModelElement {

    @Override
    public String[] getDescription() {

        return new String[]{"A subtle change; this makes iron golems wiggle their big noses whenever they're hurt.", "Exactly the same animation as for villagers, except for iron golems!"};
    }

    @Override
    public void constructClient() {

        SoundDetectionElement.addAttackableEntity(IronGolemEntity.class);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new IronGolemNoseModel<>();
    }

}
