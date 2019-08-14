package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.VillagerNoseRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureVillager extends Feature<VillagerEntity> {

    public FeatureVillager() {
        super(VillagerEntity.class, manager -> new VillagerNoseRenderer(manager, (IReloadableResourceManager) Minecraft.getInstance().getResourceManager()));
    }

    @Override
    public String getName() {
        return "villager";
    }

    @Override
    public String getDescription() {
        return "A subtle change, this makes villagers wiggle their big noses whenever they make their unique \"hmmm\" sound! It's a small change, but who doesn't get a kick out of it?";
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
    }

}
