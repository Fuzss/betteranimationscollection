package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.KneelingSheepRenderer;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureSheep extends Feature<SheepEntity> {

    public FeatureSheep() {
        super(SheepEntity.class, KneelingSheepRenderer::new);
    }

    @Override
    public String getName() {
        return "sheep";
    }

    @Override
    public String getDescription() {
        return "This one is pretty kneat. It makes sheep actually bend down to eat grass. It's no longer just their head lowering, their whole body lowers down to get a sweet sample of that succulent cellulose. Did you notice their KNEES bend too when they kneel?";
    }

    @Override
    public boolean hasCompatibility() {
        return false;
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
    }

}
