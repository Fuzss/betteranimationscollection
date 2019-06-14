package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.renderer.render.RenderFlowyOcelotTails;
import net.minecraft.entity.passive.EntityOcelot;

public class FeatureOcelot extends Feature {

    public static boolean lieDown;
    public static boolean lieBedOnly;
    public static int swing;

    public FeatureOcelot() {
        super(EntityOcelot.class, RenderFlowyOcelotTails::new);
    }

    @Override
    public String getName() {
        return "ocelot";
    }

    @Override
    protected String getDescription() {
        return "Takes away the stick tails of the current ocelots and gives them something nicer instead. Fully animated flowing tails that move while they stand or run. Also makes cats lie down instead of sitting like in Minecraft 1.14.";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        lieDown = ConfigPropHelper.loadPropBoolean("lie down", this.getCategory(), true, "Lie down instead of sitting.", false);
        lieBedOnly = ConfigPropHelper.loadPropBoolean("lie bed only", this.getCategory(), true, "Lie down on beds only instead of sitting. \"lie down\" has to be enabled.", false);
        swing = ConfigPropHelper.loadPropInt("tail swing", this.getCategory(), 7, "Swing amount of the tail.", 1, 20, false);
    }

}
