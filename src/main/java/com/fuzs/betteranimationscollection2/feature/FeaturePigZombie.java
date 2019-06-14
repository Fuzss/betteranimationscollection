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
        return "This one makes the knees of zombie pigmen bend when they walk around. Looks pretty fluid and nice. You'll like it, trust me.";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
