package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.MagmaCubeBurgerRenderer;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureMagmaCube extends Feature<MagmaCubeEntity> {

    public FeatureMagmaCube() {
        super(MagmaCubeEntity.class, MagmaCubeBurgerRenderer::new);
    }

    @Override
    public String getName() {
        return "magmacube";
    }

    @Override
    public String getDescription() {
        return "Adds a custom death animation to magma cubes, which causes their bodies to form into a pile of steamy, delicious hamburger patties when they die. Unfortunately, you can't eat them because they're way too hot.";
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
    }

}
