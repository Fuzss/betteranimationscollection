package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.config.ConfigHandler;
import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.render.RenderWobblyCreeper;
import net.minecraft.entity.monster.EntityCreeper;
import org.apache.commons.lang3.ArrayUtils;

public class FeatureCreeper extends Feature {

    public static int mode;
    public static boolean fixLegs;
    private final String[] modes = new String[] {"side", "front", "circle", "random"};

    public FeatureCreeper() {
        super(EntityCreeper.class, RenderWobblyCreeper::new);
    }

    @Override
    public String getName() {
        return "creeper";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        mode = ArrayUtils.indexOf(this.modes, ConfigHandler.config.getString("mode", this.getCategory(), "side", "Different modes for the walking animation.", this.modes));
        fixLegs = ConfigPropHelper.loadPropBoolean("fix leg rotation point", this.getCategory(), true, "Move leg rotation point towards the body so the legs don't seem to float when the entity is walking.", true);
    }

}
