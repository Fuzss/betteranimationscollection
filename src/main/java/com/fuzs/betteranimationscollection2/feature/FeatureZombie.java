package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.RenderZombieKnees;
import net.minecraft.entity.monster.EntityZombie;

public class FeatureZombie extends Feature {

    public FeatureZombie() {
        super(EntityZombie.class, RenderZombieKnees::new);
    }

    @Override
    public String getName() {
        return "zombie";
    }

    @Override
    protected String getDescription() {
        return "This one makes the knees of zombies bend when they walk around. Looks pretty fluid and nice. You'll like it, trust me.";
    }

    @Override
    protected String[] incompatibleMods() {
        return new String[]{"mobends"};
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
