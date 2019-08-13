package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.OinkyPigRenderer;
import net.minecraft.entity.passive.PigEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeaturePig extends Feature<PigEntity> {

    public FeaturePig() {
        super(PigEntity.class, OinkyPigRenderer::new);
    }

    @Override
    public String getName() {
        return "pig";
    }

    @Override
    public String getDescription() {
        return "A very subtle animation, makes a pig's snout move when it oinks. It only moves up and down ever so slightly, but it's there. Just a little bit more life for your livestock.";
    }

    @Override
    protected String[] incompatibleMods() {
        return new String[]{"quark"};
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
    }

}
