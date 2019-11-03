package com.fuzs.betteranimationscollection2.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventListener;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoundEventHandler implements ISoundEventListener {

    // map of entities whose model should do something when they make a certain sound
    private final Map<String, Class<?>> sounds = new HashMap<String, Class<?>>(){{
        put("entity.pig.ambient", PigEntity.class);
        put("entity.villager.ambient", VillagerEntity.class);
        put("entity.wandering_trader.ambient", WanderingTraderEntity.class);
        put("entity.chicken.ambient", ChickenEntity.class);
        put("entity.snow_golem.shoot", SnowGolemEntity.class);
    }};

    // list of entities whose model should do something when they are hurt
    private final List<Class<?>> hurts = new ArrayList<Class<?>>(){{
        add(VillagerEntity.class);
        add(WanderingTraderEntity.class);
        add(IronGolemEntity.class);
    }};

    public SoundEventHandler() {
        Minecraft.getInstance().getSoundHandler().addListener(this);
    }

    /**
     * Prevents EntityLiving#livingSoundTime from reaching 0 or greater on the client as it wants to play a sound then
     * Instead EntityLiving#livingSoundTime is partially synced with the server in SoundEventHandler#soundPlay
     * This is required for sound related model animations
     */
    @SuppressWarnings("unused")
    @SubscribeEvent
    public void updateEntity(LivingEvent.LivingUpdateEvent evt) {

        LivingEntity entity = evt.getEntityLiving();
        boolean flag = this.hurts.stream().anyMatch(it -> it.isInstance(entity));

        if (entity.world.isRemote) {

            if (flag || this.sounds.values().stream().anyMatch(it -> it.isInstance(entity))) {

                MobEntity entityLiving = (MobEntity) entity;

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
    public void onPlaySound(@Nonnull ISound soundIn, @Nonnull SoundEventAccessor accessor) {

        if (this.sounds.keySet().stream().anyMatch(it -> soundIn.getSoundLocation().getPath().equals(it))) {

            for (Entity entity : Minecraft.getInstance().world.getAllEntities()) {

                if (this.checkPos((float) entity.posX, soundIn.getX()) && this.checkPos((float) entity.posY, soundIn.getY()) && this.checkPos((float) entity.posZ, soundIn.getZ())) {

                    if (this.sounds.get(soundIn.getSoundLocation().getPath()).isInstance(entity)) {
                        MobEntity entityLiving = (MobEntity) entity;
                        entityLiving.livingSoundTime = -entityLiving.getTalkInterval();
                        break;
                    }

                }

            }

        }

    }

    private boolean checkPos(float entityPos, float soundPos) {

        entityPos = (int) (entityPos * 8) / 8.0F;
        return entityPos - ConfigHandler.soundRange.get() < soundPos && entityPos + ConfigHandler.soundRange.get() > soundPos;

    }

}
