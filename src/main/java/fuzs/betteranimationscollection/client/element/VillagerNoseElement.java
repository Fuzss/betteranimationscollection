package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.VillagerNoseModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.util.SoundEvents;

public class VillagerNoseElement extends SoundModelElement {

    public VillagerNoseElement() {

        this.defaultSounds.add(SoundEvents.VILLAGER_AMBIENT, SoundEvents.VILLAGER_TRADE, SoundEvents.WANDERING_TRADER_AMBIENT, SoundEvents.WANDERING_TRADER_TRADE);
    }

    @Override
    public String[] getDescription() {

        return new String[]{"A subtle change; this makes villagers wiggle their big noses whenever they make their iconic sound.",
                "It's a small change, but who doesn't get a kick out of it?"};
    }

    @Override
    public void constructClient() {

        SoundDetectionElement.addAttackableEntity(AbstractVillagerEntity.class);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new VillagerNoseModel<>();
    }

    @Override
    protected Class<? extends MobEntity> getMobClazz() {

        return AbstractVillagerEntity.class;
    }

}
