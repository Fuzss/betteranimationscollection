package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.render.RenderKneelingSheep;
import net.minecraft.entity.passive.EntitySheep;

public class FeatureSheep extends Feature {

    public FeatureSheep() {
        super(EntitySheep.class, RenderKneelingSheep::new);
    }

    @Override
    public String getName() {
        return "sheep";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
