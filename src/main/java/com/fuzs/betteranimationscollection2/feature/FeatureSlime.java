package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.renderer.render.RenderJigglySlime;
import net.minecraft.entity.monster.EntitySlime;

public class FeatureSlime extends Feature {

    public static int squishiness;

    public FeatureSlime() {
        super(EntitySlime.class, RenderJigglySlime::new);
    }

    @Override
    public String getName() {
        return "slime";
    }

    @Override
    protected String getDescription() {
        return "A pleasing visual change; this makes the insides of slimes flow around like liquid. They splish-splosh about even more when they jump. The eyes, the mouth, and the core itself all move independently.";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        squishiness = ConfigPropHelper.loadPropInt("squishiness", this.getCategory(), 5, "Animation swing of inner slime parts.", 1, 20, false);
    }

}
