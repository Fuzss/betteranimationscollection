package fuzs.betteranimationscollection.client.element;

import com.google.common.collect.Lists;
import fuzs.puzzleslib.config.ConfigManager;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import fuzs.puzzleslib.config.serialization.EntryCollectionBuilder;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;

public abstract class SoundModelElement extends ModelElement {

    private final Class<? extends MobEntity> entityClazz;
    private final List<String> defaultSounds = Lists.newArrayList();

    private Set<SoundEvent> sounds;

    protected SoundModelElement(Class<? extends MobEntity> entityClazz) {

        this.entityClazz = entityClazz;
    }

    protected final void addDefaultSound(SoundEvent soundEvent) {

        this.addDefaultSound(soundEvent.getLocation());
    }

    protected final void addDefaultSound(ResourceLocation soundEvent) {

        this.defaultSounds.add(soundEvent.toString());
    }

    @Override
    public void setupClientConfig(OptionsBuilder builder) {

        builder.define("Mob Sounds", this.defaultSounds).comment("Mob sounds to play a unique animation for.", "Useful for adding support for modded mob variations which have different sounds from their vanilla counterparts.", EntryCollectionBuilder.CONFIG_STRING).sync(v -> {

            if (this.sounds != null) {

                SoundDetectionElement.removeAmbientSounds(this.sounds);
            }

            this.sounds = ConfigManager.deserializeToSet(v, ForgeRegistries.SOUND_EVENTS);
            SoundDetectionElement.addAmbientSounds(this.entityClazz, this.sounds);
        });
    }

}
