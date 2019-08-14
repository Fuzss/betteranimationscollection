package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.IronGolemNoseRenderer;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureIronGolem extends Feature<IronGolemEntity> {

    public FeatureIronGolem() {
        super(IronGolemEntity.class, IronGolemNoseRenderer::new);
    }

    @Override
    public String getName() {
        return "irongolem";
    }

    @Override
    public String getDescription() {
        return "A subtle change, this makes iron golems wiggle their big noses whenever they're hurt. Exactly the same animation as for villagers, except for iron golems!";
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
    }

}
