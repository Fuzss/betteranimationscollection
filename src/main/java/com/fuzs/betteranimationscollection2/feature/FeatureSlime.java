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
        return "";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        squishiness = ConfigPropHelper.loadPropInt("squishiness", this.getCategory(), 5, "Animation speed of inner slime parts.", 1, 20, false);
    }

}
