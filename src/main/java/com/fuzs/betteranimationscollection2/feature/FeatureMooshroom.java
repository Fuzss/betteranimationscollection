package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.render.RenderMooshroomUdder;
import net.minecraft.entity.passive.EntityMooshroom;

public class FeatureMooshroom extends Feature {

    public FeatureMooshroom() {
        super(EntityMooshroom.class, RenderMooshroomUdder::new);
    }

    @Override
    public String getName() {
        return "mooshroom";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
