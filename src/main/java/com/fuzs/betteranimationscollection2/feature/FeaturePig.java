package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.RenderOinkyPig;
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
    protected String getDescription() {
        return "A very subtle animation, makes a pig's snout move when it oinks. It only moves up and down ever so slightly, but it's there. Just a little bit more life for your livestock.";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
