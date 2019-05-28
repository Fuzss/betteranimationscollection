package com.fuzs.betteranimationscollection2.util;

import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SnowmanAttackHelper implements PrivateAccessor {

    @SubscribeEvent
    public void createSnowman(LivingEvent.LivingUpdateEvent evt) {
        if (evt.getEntity() instanceof EntitySnowman) {

            EntitySnowman snowman = (EntitySnowman) evt.getEntity();

            for (EntityAITasks.EntityAITaskEntry entityaitasks$entityaitaskentry : snowman.tasks.taskEntries) {
                EntityAIBase entityaibase = entityaitasks$entityaitaskentry.action;

                if (entityaibase instanceof EntityAIAttackRanged && entityaitasks$entityaitaskentry.using) {
                    int attackTime = getRangedAttackTime((EntityAIAttackRanged) entityaibase);
                    //System.out.println("Current attack time is: " + attackTime);
                }

            }
        }
    }

}
