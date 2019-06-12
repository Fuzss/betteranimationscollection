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
        return "";
    }

    @Override
    protected String[] incompatibleMods() {
        return new String[]{"muddypigs"};
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
