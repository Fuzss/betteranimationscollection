package fuzs.tbac2.fixes;

import fuzs.tbac2.tweaks.EntityAIAttackRangedEasyBow;
import fuzs.tbac2.util.PrivateAccessor;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ParticleDragonExplosion implements PrivateAccessor {

    public void dragonExplosion(EntityMobGriefingEvent evt) {
        if (evt.getEntity() instanceof EntityDragon) {

        }
    }

    @SubscribeEvent
    public void create(LivingEvent.LivingUpdateEvent evt) {
        if (evt.getEntity() instanceof AbstractSkeleton) {
            AbstractSkeleton abstractskeleton = (AbstractSkeleton) evt.getEntity();
            EntityAIAttackRangedBow aiarrowattack = getAIArrowAttack(abstractskeleton);
            abstractskeleton.tasks.removeTask(aiarrowattack);
            EntityAIAttackRangedEasyBow aiarroweasyattack = new EntityAIAttackRangedEasyBow<>(abstractskeleton, 1.0D, 20, 15.0F);
            abstractskeleton.tasks.addTask(4, aiarroweasyattack);
        }
    }

}
