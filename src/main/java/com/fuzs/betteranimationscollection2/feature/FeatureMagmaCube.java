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
        return "Adds a custom death animation to magma cubes, which causes their bodies to form into a pile of steamy, delicious hamburger patties when they die. Unfortunately, you can't eat them because they're way too hot.";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
