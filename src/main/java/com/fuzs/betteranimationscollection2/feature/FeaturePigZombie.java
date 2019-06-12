package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.RenderPigZombieKnees;
import net.minecraft.entity.monster.EntityPigZombie;

public class FeaturePigZombie extends Feature {

    public FeaturePigZombie() {
        super(EntityPigZombie.class, RenderPigZombieKnees::new);
    }

    @Override
    public String getName() {
        return "pigzombie";
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
