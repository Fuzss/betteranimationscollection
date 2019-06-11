package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.render.RenderJigglySlime;
import net.minecraft.entity.monster.EntitySlime;

public class FeatureSlime extends Feature {

    public FeatureSlime() {
        super(EntitySlime.class, RenderJigglySlime::new);
    }

    @Override
    public String getName() {
        return "slime";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
