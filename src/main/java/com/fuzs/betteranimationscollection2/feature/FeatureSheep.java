package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.RenderKneelingSheep;
import net.minecraft.entity.passive.EntitySheep;

public class FeatureSheep extends Feature {

    public FeatureSheep() {
        super(EntitySheep.class, RenderKneelingSheep::new);
    }

    @Override
    public String getName() {
        return "sheep";
    }

    @Override
    protected String getDescription() {
        return "This one is pretty kneat. It makes sheep actually bend down to eat grass. It's no longer just their head lowering, their whole body lowers down to get a sweet sample of that succulent cellulose. Did you notice their KNEES bend too when they kneel?";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
    }

}
