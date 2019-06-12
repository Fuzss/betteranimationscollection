package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.RenderMagmaCubeBurger;
import net.minecraft.entity.monster.EntityMagmaCube;

public class FeatureMagmaCube extends Feature {

    public FeatureMagmaCube() {
        super(EntityMagmaCube.class, RenderMagmaCubeBurger::new);
    }

    @Override
    public String getName() {
        return "magmacube";
    }

    @Override
    protected String getDescription() {
        return "";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
