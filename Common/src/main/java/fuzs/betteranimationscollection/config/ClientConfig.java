package fuzs.betteranimationscollection.config;

import fuzs.betteranimationscollection.client.BetterAnimationsCollectionClient;
import fuzs.betteranimationscollection.client.element.ModelElementBase;
import fuzs.puzzleslib.config.ConfigCore;
import fuzs.puzzleslib.config.ValueCallback;
import fuzs.puzzleslib.config.annotation.Config;
import fuzs.puzzleslib.config.core.AbstractConfigBuilder;
import fuzs.puzzleslib.config.serialization.EntryCollectionBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClientConfig implements ConfigCore {
    @Config(category = "general", name = "mob_blacklist", description = {"Mob variants that shouldn't have any model changes applied to them.", EntryCollectionBuilder.CONFIG_DESCRIPTION})
    List<String> mobBlacklistRaw = EntryCollectionBuilder.getKeyList(Registry.ENTITY_TYPE_REGISTRY);

    public Set<EntityType<?>> mobBlacklist;

    @Override
    public void addToBuilder(AbstractConfigBuilder builder, ValueCallback callback) {
        builder.push("general");
        for (Map.Entry<ResourceLocation, ModelElementBase> entry : BetterAnimationsCollectionClient.MODEL_ELEMENTS.entrySet()) {
            callback.accept(builder.comment(entry.getValue().modelDescription()).define(entry.getKey().getPath(), true), entry.getValue()::setEnabled);
        }
        builder.pop();
        for (Map.Entry<ResourceLocation, ModelElementBase> entry : BetterAnimationsCollectionClient.MODEL_ELEMENTS.entrySet()) {
            builder.push(entry.getKey().getPath());
            entry.getValue().setupModelConfig(builder, callback);
            builder.pop();
        }
    }

    @Override
    public void afterConfigReload() {
        Set<EntityType<?>> mobBlacklist = EntryCollectionBuilder.of(Registry.ENTITY_TYPE_REGISTRY).buildSet(this.mobBlacklistRaw);
        boolean initialReload = this.mobBlacklist == null;
        if (initialReload || !mobBlacklist.equals(this.mobBlacklist)) {
            this.mobBlacklist = mobBlacklist;
            if (!initialReload) {
                BetterAnimationsCollectionClient.buildAnimatedModels(false, true);
            }
        }
    }
}
