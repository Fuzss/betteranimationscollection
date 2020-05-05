package com.fuzs.betteranimationscollection2.handler;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventListener;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public class SoundSyncHandler implements ISoundEventListener {

    private final Minecraft mc = Minecraft.getInstance();
    /**
     * map of entities whose model should do something when they make a certain sound
     */
    private static final Map<SoundEvent, EntityType<? extends LivingEntity>> ENTITIES_NOISE = new HashMap<SoundEvent, EntityType<? extends LivingEntity>>(){{
        put(SoundEvents.ENTITY_PIG_AMBIENT, EntityType.PIG);
        put(SoundEvents.ENTITY_VILLAGER_AMBIENT, EntityType.VILLAGER);
        put(SoundEvents.ENTITY_CHICKEN_AMBIENT, EntityType.CHICKEN);
        put(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, EntityType.SNOW_GOLEM);
    }};
    /**
     * list of entities whose model should do something when they are hurt
     */
    private static final List<EntityType<? extends LivingEntity>> ENTITIES_DAMAGE = Lists.newArrayList(EntityType.VILLAGER, EntityType.IRON_GOLEM);

    public SoundSyncHandler() {
        this.mc.getSoundHandler().addListener(this);
    }

    /**
     * Prevents {@link net.minecraft.entity.MobEntity#livingSoundTime} from reaching 0 or greater on the client as it wants to play a sound then
     * Instead {@link net.minecraft.entity.MobEntity#livingSoundTime} is partially synced with the server in {@link #onPlaySound}
     * This is required for sound related model animations
     */
    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onLivingUpdate(final LivingEvent.LivingUpdateEvent evt) {

        LivingEntity entity = evt.getEntityLiving();
        boolean flag = ENTITIES_DAMAGE.stream().anyMatch(it -> it.equals(entity.getType()));

        if (flag || ENTITIES_NOISE.values().stream().anyMatch(it -> it.equals(entity.getType()))) {

            MobEntity entityLiving = (MobEntity) entity;

            if (entityLiving.livingSoundTime > -1) {
                entityLiving.livingSoundTime = -entityLiving.getTalkInterval() + 20;
            }

            if (flag && entityLiving.maxHurtTime > 0 && entityLiving.hurtTime == entityLiving.maxHurtTime) {
                entityLiving.livingSoundTime = -entityLiving.getTalkInterval();
            }

        }

    }

    @Override
    public void onPlaySound(@Nonnull ISound soundIn, @Nonnull SoundEventAccessor accessor) {

        SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(soundIn.getSoundLocation());
        if (this.mc.world != null && ENTITIES_NOISE.containsKey(soundEvent)) {

            for (Entity entity : this.mc.world.getAllEntities()) {

                // sound position is rounded the same way on the server side
                BiPredicate<Double, Float> comparePosition = (entityPos, soundPos) -> Math.abs((int) (entityPos * 8) / 8.0F - soundPos) < ConfigBuildHandler.soundRange.get();
                if (comparePosition.test(entity.getPosX(), soundIn.getX()) && comparePosition.test(entity.getPosY(), soundIn.getY())
                        && comparePosition.test(entity.getPosZ(), soundIn.getZ())) {

                    if (ENTITIES_NOISE.get(soundEvent).equals(entity.getType())) {

                        MobEntity entityLiving = (MobEntity) entity;
                        entityLiving.livingSoundTime = -entityLiving.getTalkInterval();
                        break;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void on(LivingEvent.LivingUpdateEvent evt) {
        if (evt.getEntity() instanceof MobEntity)
        System.out.println(((MobEntity) evt.getEntityLiving()).livingSoundTime);
    }

}
