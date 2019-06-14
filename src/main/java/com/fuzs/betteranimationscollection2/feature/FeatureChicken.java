package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.helper.ConfigPropHelper;
import com.fuzs.betteranimationscollection2.renderer.render.RenderBuckaBuckaChicken;
import net.minecraft.entity.passive.EntityChicken;

public class FeatureChicken extends Feature {

    public static boolean moveHead;
    public static boolean moveChin;
    public static boolean moveWings;
    public static int headSpeed;
    public static int chinSpeed;
    public static int wingSpeed;

    public FeatureChicken() {
        super(EntityChicken.class, RenderBuckaBuckaChicken::new);
    }

    @Override
    public String getName() {
        return "chicken";
    }

    @Override
    protected String getDescription() {
        return "This one makes chicken beaks open and close when they cluck. When they strut their heads move back and forth, the red thing under their beak swings around and their wings flap a little. Just like the real deal!";
    }

    @Override
    public void setupConfig() {
        super.setupConfig();
        moveHead = ConfigPropHelper.loadPropBoolean("move head", this.getCategory(), true, "Move head back and forth when the entity is walking.", false);
        headSpeed = ConfigPropHelper.loadPropInt("head swing", this.getCategory(), 4, "Animation swing for the head movement.", 1, 20, false);
        moveChin = ConfigPropHelper.loadPropBoolean("wiggle chin", this.getCategory(), true, "Wiggle chin when the entity is walking.", false);
        chinSpeed = ConfigPropHelper.loadPropInt("chin swing", this.getCategory(), 5, "Animation swing for the chin movement.", 1, 20, false);
        moveWings = ConfigPropHelper.loadPropBoolean("flap wings", this.getCategory(), true, "Flap wings when the entity is walking.", false);
        wingSpeed = ConfigPropHelper.loadPropInt("wings swing", this.getCategory(), 3, "Animation swing of the wing flapping.", 1, 20, false);
    }

}
