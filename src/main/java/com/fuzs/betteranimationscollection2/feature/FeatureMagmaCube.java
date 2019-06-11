package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.render.RenderMagmaCubeBurger;
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
    public void setupConfig() {
        super.setupConfig();
    }

}
