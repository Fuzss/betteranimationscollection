package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.handler.RemoteSoundHandler;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import fuzs.puzzleslib.config.serialization.EntryCollectionBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Mob;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class SoundDetectionElement extends ModelElementBase {
    private final Class<? extends Mob> mobClazz;
    private final SoundEvent[] sounds;

    public SoundDetectionElement(Class<? extends Mob> mobClazz, SoundEvent... sounds) {
        this.mobClazz = mobClazz;
        this.sounds = sounds;
    }

    @Override
    public void setupModelConfig(AbstractConfigBuilder builder, ValueCallback callback) {
        callback.accept(builder.comment("Mob sounds to play a unique animation for.", "Useful for adding support for modded mob variants which have different sounds from their vanilla counterparts.", EntryCollectionBuilder.CONFIG_DESCRIPTION).define("mob_sounds", Stream.of(this.sounds).map(Registry.SOUND_EVENT::getKey)
                .filter(Objects::nonNull)
                .map(ResourceLocation::toString)
                .collect(Collectors.toList())), v -> {
            RemoteSoundHandler.INSTANCE.removeAmbientSounds(this.mobClazz);
            Set<SoundEvent> soundEvents = EntryCollectionBuilder.of(Registry.SOUND_EVENT_REGISTRY).buildSet(v);
            RemoteSoundHandler.INSTANCE.addAmbientSounds(this.mobClazz, soundEvents);
        });
    }
}
