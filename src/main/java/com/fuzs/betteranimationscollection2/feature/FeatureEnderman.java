package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.render.RenderWavingEnderman;
import net.minecraft.entity.monster.EntityEnderman;

public class FeatureEnderman extends Feature {

    public FeatureEnderman() {
        super(EntityEnderman.class, RenderWavingEnderman::new);
    }

    @Override
    public String getName() {
        return "enderman";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
