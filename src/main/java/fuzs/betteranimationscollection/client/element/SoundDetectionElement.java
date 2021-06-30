package fuzs.betteranimationscollection.client.element;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import fuzs.puzzleslib.element.AbstractElement;
import fuzs.puzzleslib.element.side.IClientElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventListener;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class SoundDetectionElement extends AbstractElement implements IClientElement, ISoundEventListener {

    /**
     * max time an animation takes so we don't confuse our own stuff when resetting {@link MobEntity#ambientSoundTime}
     */
    private static final int MAX_SOUND_ANIMATION_TIME = 20;
    /**
     * map of entities whose model should do something when they make a certain sound
     */
    private static final Map<ResourceLocation, Class<? extends MobEntity>> AMBIENT_SOUNDS = Maps.newHashMap();
    /**
     * set of entities whose model should do something when they are hurt
     */
    private static final Set<Class<? extends MobEntity>> ATTACKABLE_ENTITIES = Sets.newHashSet();

    private final Minecraft mc = Minecraft.getInstance();

    private double soundRange;

    @Override
    public String[] getDescription() {

        return new String[]{"This is required for sound related model animations."};
    }

    @Override
    protected boolean isPersistent() {

        return true;
    }

    @Override
    public void setupClient() {

        this.addListener(this::onLivingUpdate);
    }

    @Override
    public void loadClient() {

        this.mc.getSoundManager().addListener(this);
    }

    @Override
    public void unloadClient() {

        this.mc.getSoundManager().removeListener(this);
    }

    @Override
    public void setupClientConfig(OptionsBuilder builder) {

        builder.define("Sound Detection Range", 0.5).min(0.0).max(8.0).comment("Block range for sound detection system to look for a mob that made a certain sound on the server, so the client may play an animation.").sync(v -> this.soundRange = v);
    }

    private void onLivingUpdate(final LivingEvent.LivingUpdateEvent evt) {

        LivingEntity entity = evt.getEntityLiving();
        if (!entity.level.isClientSide() || !(entity instanceof MobEntity)) {

            return;
        }

        MobEntity mobEntity = (MobEntity) entity;
        Stream.concat(AMBIENT_SOUNDS.values().stream(), ATTACKABLE_ENTITIES.stream()).forEach(clazz -> {

            if (clazz.isAssignableFrom(entity.getClass())) {

                if (mobEntity.ambientSoundTime >= 0) {

                    // prevent ambientSoundTime from reaching values greater than zero, as the client tries to play a sound then, messing up our system
                    // MAX_SOUND_ANIMATION_TIME has to be added so our stuff doesn't trigger
                    // value will be set properly without MAX_SOUND_ANIMATION_TIME in #onPlaySound
                    mobEntity.ambientSoundTime = -mobEntity.getAmbientSoundInterval() + MAX_SOUND_ANIMATION_TIME;
                }
            }
        });

        for (Class<? extends MobEntity> clazz : ATTACKABLE_ENTITIES) {

            if (clazz.isAssignableFrom(entity.getClass())) {

                // set this when a mob has just been hurt
                if (mobEntity.hurtDuration > 0 && mobEntity.hurtTime == mobEntity.hurtDuration) {

                    // this is used to play an animation when hurt
                    mobEntity.ambientSoundTime = -mobEntity.getAmbientSoundInterval();
                }
            }
        }
    }

    @Override
    public void onPlaySound(ISound soundIn, SoundEventAccessor accessor) {

        Class<? extends MobEntity> entityClazz = AMBIENT_SOUNDS.get(soundIn.getLocation());
        if (entityClazz != null) {

            // accuracy is 1/8, so we center this and then apply #soundRange
            Vector3d center = new Vector3d(soundIn.getX() + 0.0625, soundIn.getY() + 0.0625, soundIn.getZ() + 0.0625);
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(center, center).inflate(this.soundRange + 0.0625);

            assert this.mc.level != null;
            List<MobEntity> entities = this.mc.level.getEntitiesOfClass(entityClazz, axisAlignedBB);
            entities.stream()
                    .min((o1, o2) -> (int) Math.signum(o1.position().distanceTo(center) - o2.position().distanceTo(center)))
                    .ifPresent(entity -> entity.ambientSoundTime = -entity.getAmbientSoundInterval());
        }
    }

    public static void addAmbientSounds(Class<? extends MobEntity> entityClazz, Collection<SoundEvent> soundEvents) {

        for (SoundEvent soundEvent : soundEvents) {

            AMBIENT_SOUNDS.put(soundEvent.getLocation(), entityClazz);
        }
    }

    public static void removeAmbientSounds(Collection<SoundEvent> soundEvents) {

        for (SoundEvent soundEvent : soundEvents) {

            AMBIENT_SOUNDS.remove(soundEvent.getLocation());
        }
    }

    public static void addAttackableEntity(Class<? extends MobEntity> entityClazz) {

        ATTACKABLE_ENTITIES.add(entityClazz);
    }

}
