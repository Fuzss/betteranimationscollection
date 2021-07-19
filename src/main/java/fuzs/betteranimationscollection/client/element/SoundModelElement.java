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
import java.util.stream.Collectors;

public abstract class SoundModelElement extends ModelElement {

    protected final List<ResourceLocation> defaultSounds = Lists.newArrayList();

    private Set<SoundEvent> sounds;

    protected abstract Class<? extends MobEntity> getMobClazz();

    @Override
    public void setupClientConfig(OptionsBuilder builder) {

        super.setupClientConfig(builder);
        builder.define("Mob Sounds", this.defaultSounds.stream()
                .map(ResourceLocation::toString)
                .collect(Collectors.toList())).comment("Mob sounds to play a unique animation for.", "Useful for adding support for modded mob variants which have different sounds from their vanilla counterparts.", EntryCollectionBuilder.CONFIG_STRING).sync(v -> {

            if (this.sounds != null) {

                SoundDetectionElement.removeAmbientSounds(this.sounds);
            }

            this.sounds = ConfigManager.deserializeToSet(v, ForgeRegistries.SOUND_EVENTS);
            SoundDetectionElement.addAmbientSounds(this.getMobClazz(), this.sounds);
        });
    }

}
