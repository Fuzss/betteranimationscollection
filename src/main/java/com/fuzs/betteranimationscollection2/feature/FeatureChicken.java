package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.renderer.render.BuckaChickenRenderer;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class FeatureChicken extends Feature<ChickenEntity> {

    public static ForgeConfigSpec.BooleanValue moveHead;
    public static ForgeConfigSpec.BooleanValue moveChin;
    public static ForgeConfigSpec.BooleanValue moveWings;
    public static ForgeConfigSpec.IntValue headSpeed;
    public static ForgeConfigSpec.IntValue chinSpeed;
    public static ForgeConfigSpec.IntValue wingSpeed;

    public FeatureChicken() {
        super(ChickenEntity.class, BuckaChickenRenderer::new);
    }

    @Override
    public String getName() {
        return "chicken";
    }

    @Override
    public String getDescription() {
        return "This one makes chicken beaks open and close when they cluck. When they strut their heads move back and forth, the red thing under their beak swings around and their wings flap a little. Just like the real deal!";
    }

    @Override
    protected String[] incompatibleMods() {
        return new String[]{"quark"};
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        super.setupConfig(builder);
        moveHead = builder.comment("Move head back and forth when the entity is walking.").define("move head", true);
        headSpeed = builder.comment("Animation swing for the head movement.").defineInRange("head swing", 4, 1, 20);
        moveChin = builder.comment("Wiggle chin when the entity is walking.").define("wiggle chin", true);
        chinSpeed = builder.comment("Animation swing for the chin movement.").defineInRange("chin swing", 5, 1, 20);
        moveWings = builder.comment("Flap wings when the entity is walking.").define("flap wings", true);
        wingSpeed = builder.comment("Animation swing of the wing flapping.").defineInRange("wings swing", 3, 1, 20);
    }

}
