package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.handler.ConfigHandler;
import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.renderer.render.RenderWobblyCreeper;
import net.minecraft.entity.monster.EntityCreeper;
import org.apache.commons.lang3.ArrayUtils;

public class FeatureCreeper extends Feature {

    public static int mode;
    private final String[] modes = new String[] {"side", "front", "circle", "random"};

    public FeatureCreeper() {
        super(EntityCreeper.class, RenderWobblyCreeper::new);
    }

    @Override
    public String getName() {
        return "creeper";
    }

    @Override
    protected String getDescription() {
        return "Remember the very popular TNT music video by CaptainSparklez with the really cute wobbly Creeper? Well, it's in the game now. The full thing, the real deal, exactly like the video. Probably the best custom animation in the entire collection.";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        mode = ArrayUtils.indexOf(this.modes, ConfigHandler.config.getString("wobble mode", this.getCategory(), "side", "Different modes for the walking animation.", this.modes));
    }

}
