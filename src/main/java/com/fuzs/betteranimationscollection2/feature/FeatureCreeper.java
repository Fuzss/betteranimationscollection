package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.helper.ConfigHelper;
import com.fuzs.betteranimationscollection2.renderer.render.WobblyCreeperRenderer;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class FeatureCreeper extends Feature<CreeperEntity> {

    public static ForgeConfigSpec.EnumValue<CreeperMode> mode;

    public FeatureCreeper() {
        super(CreeperEntity.class, WobblyCreeperRenderer::new);
    }

    @Override
    public String getName() {
        return "creeper";
    }

    @Override
    public String getDescription() {
        return "Remember the very popular TNT music video by CaptainSparklez with the really cute wobbly Creeper? Well, it's in the game now. The full thing, the real deal, exactly like the video. Probably the best custom animation in the entire collection.";
    }

    @Override
    public boolean hasCompatibility() {
        return false;
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
        mode = builder.comment(ConfigHelper.getEnumDescription("Different modes for the walking animation.", CreeperMode.class)).defineEnum("wobble mode", CreeperMode.SIDE);
    }

    @SuppressWarnings("unused")
    public enum CreeperMode {
        SIDE(), FRONT(), CIRCLE(), RANDOM()
    }

}
