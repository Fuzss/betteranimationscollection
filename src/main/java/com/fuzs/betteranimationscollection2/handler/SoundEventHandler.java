package com.fuzs.betteranimationscollection2.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventListener;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoundEventHandler implements ISoundEventListener {

    // map of entities whose model should do something when they make a certain sound
    private final Map<String, Class<?>> sounds = new HashMap<String, Class<?>>(){{
        put("entity.pig.ambient", EntityPig.class);
        put("entity.villager.ambient", EntityVillager.class);
        put("entity.chicken.ambient", EntityChicken.class);
        put("entity.snowman.shoot", EntitySnowman.class);
    }};

    // list of entities whose model should do something when they are hurt
    private final List<Class<?>> hurts = new ArrayList<Class<?>>(){{
        add(EntityVillager.class);
        add(EntityIronGolem.class);
    }};

    public SoundEventHandler() {
        Minecraft.getMinecraft().getSoundHandler().addListener(this);
    }

    /**
     * Prevents EntityLiving#livingSoundTime from reaching 0 or greater on the client as it wants to play a sound then
     * Instead EntityLiving#livingSoundTime is partially synced with the server in SoundEventHandler#soundPlay
     * This is required for sound related model animations
     */
    @SubscribeEvent
    public void updateEntity(LivingEvent.LivingUpdateEvent evt) {

        EntityLivingBase entity = evt.getEntityLiving();
        boolean flag = this.hurts.stream().anyMatch(it -> it.isInstance(entity));

        if (entity.world.isRemote) {

            if (flag || this.sounds.values().stream().anyMatch(it -> it.isInstance(entity))) {

                EntityLiving entityLiving = (EntityLiving) entity;

                if (entityLiving.livingSoundTime > -1) {
                    entityLiving.livingSoundTime = -entityLiving.getTalkInterval() + 20;
                }

                if (flag && entityLiving.maxHurtTime > 0 && entityLiving.hurtTime == entityLiving.maxHurtTime) {
                    entityLiving.livingSoundTime = -entityLiving.getTalkInterval();
                }

            }

        }

    }

    @Override
    public void soundPlay(ISound soundIn, SoundEventAccessor accessor) {

        if (this.sounds.keySet().stream().anyMatch(it -> soundIn.getSoundLocation().getResourcePath().equals(it))) {

            for (Entity entity : Minecraft.getMinecraft().world.loadedEntityList)
            {
                if (this.checkPos((float) entity.posX, soundIn.getXPosF()) && this.checkPos((float) entity.posY, soundIn.getYPosF()) && this.checkPos((float) entity.posZ, soundIn.getZPosF()))
                {
                    if (this.sounds.get(soundIn.getSoundLocation().getResourcePath()).isInstance(entity)) {
                        //System.out.println(entity);
                        EntityLiving entityLiving = (EntityLiving) entity;
                        entityLiving.livingSoundTime = -entityLiving.getTalkInterval();
                        break;
                    }
                }
            }

        }

    }

    private boolean checkPos(float entityPos, float soundPos) {

        entityPos = (int) (entityPos * 8) / 8.0F;
        return entityPos - ConfigHandler.soundRange < soundPos && entityPos + ConfigHandler.soundRange > soundPos;

    }

}
