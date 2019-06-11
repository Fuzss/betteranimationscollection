package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.render.RenderOinkyPig;
import net.minecraft.entity.passive.EntityPig;

public class FeaturePig extends Feature {

    public FeaturePig() {
        super(EntityPig.class, RenderOinkyPig::new);
    }

    @Override
    public String getName() {
        return "pig";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
