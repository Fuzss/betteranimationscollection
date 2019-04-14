package fuzs.tbac2.fixes;

import fuzs.tbac2.util.PrivateAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ParticleDragonExplosion implements PrivateAccessor {

    @SubscribeEvent
    public void createSnowman(LivingEvent.LivingUpdateEvent evt) {
        if (evt.getEntity() instanceof EntitySnowman) {

            EntitySnowman snowman = (EntitySnowman) evt.getEntity();

            for (EntityAITasks.EntityAITaskEntry entityaitasks$entityaitaskentry : snowman.tasks.taskEntries) {
                EntityAIBase entityaibase = entityaitasks$entityaitaskentry.action;

                if (entityaibase instanceof EntityAIAttackRanged && entityaitasks$entityaitaskentry.using) {
                    int attackTime = getRangedAttackTime((EntityAIAttackRanged) entityaibase);
                    System.out.println("Current attack time is: " + attackTime);
                }

            }
        }
    }

}
