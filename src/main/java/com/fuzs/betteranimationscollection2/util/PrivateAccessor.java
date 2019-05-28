/*
** 2016 Juni 19
**
** The author disclaims copyright to this source code. In place of
** a legal notice, here is a blessing:
**    May you do good and not evil.
**    May you find forgiveness for yourself and forgive others.
**    May you share freely, never taking more than you give.
 */
package com.fuzs.betteranimationscollection2.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.Timer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public interface PrivateAccessor {
    
    Logger LOGGER = LogManager.getLogger();

    String[] ENTITYANIMAL_INLOVE = new String[]{"inLove", "field_70881_d"};
    String[] ABSTRACTSKELETON_AIRARROWATTACK = new String[]{"aiArrowAttack", "field_85037_d"};
    String[] AIENTITYATTACKRANGED_RANGEDATTACKTIME = new String[]{"rangedAttackTime", "field_75320_d"};
    String[] LIVINGBASE_JUMPING = new String[]{"isJumping", "field_70703_bu"};
    
    default int getInLove(EntityAnimal instance) {
        try {
            return ObfuscationReflectionHelper.getPrivateValue(EntityAnimal.class, instance, ENTITYANIMAL_INLOVE[1]);
        } catch (Exception ex) {
            LOGGER.error("setInLove() failed", ex);
        }
        return 0;
    }

    default int getRangedAttackTime(EntityAIAttackRanged instance) {
        try {
            return ObfuscationReflectionHelper.getPrivateValue(EntityAIAttackRanged.class, instance, AIENTITYATTACKRANGED_RANGEDATTACKTIME[1]);
        } catch (Exception ex) {
            LOGGER.error("getRangedAttackTime() failed", ex);
        }
        return 0;
    }

    default boolean getIsJumping(EntityLivingBase instance) {
        try {
            return ObfuscationReflectionHelper.getPrivateValue(EntityLivingBase.class, instance, LIVINGBASE_JUMPING[1]);
        } catch (Exception ex) {
            LOGGER.error("getIsJumping() failed", ex);
        }
        return false;
    }
}