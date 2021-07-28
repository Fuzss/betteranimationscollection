package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.util.LoadedLocationList;
import fuzs.puzzleslib.config.ConfigManager;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import fuzs.puzzleslib.config.serialization.EntryCollectionBuilder;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public abstract class SoundModelElement extends ModelElement {

    protected final LoadedLocationList defaultSounds = LoadedLocationList.create();

    private Set<SoundEvent> sounds;

    protected abstract Class<? extends MobEntity> getMobClazz();

    @Override
    public void setupModelConfig(OptionsBuilder builder) {

        builder.define("Mob Sounds", this.defaultSounds.get()).comment("Mob sounds to play a unique animation for.", "Useful for adding support for modded mob variants which have different sounds from their vanilla counterparts.", EntryCollectionBuilder.CONFIG_STRING).sync(v -> {

            if (this.sounds != null) {

                SoundDetectionElement.removeAmbientSounds(this.sounds);
            }

            this.sounds = ConfigManager.deserializeToSet(v, ForgeRegistries.SOUND_EVENTS);
            SoundDetectionElement.addAmbientSounds(this.getMobClazz(), this.sounds);
        });
    }

}
