package com.fuzs.betteranimationscollection2.feature;

import com.fuzs.betteranimationscollection2.feature.core.ModelFeature;
import com.fuzs.betteranimationscollection2.renderer.model.BuckaChickenModel;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraftforge.common.ForgeConfigSpec;

public class ChickenFeature extends ModelFeature<ChickenEntity> {

    public static ForgeConfigSpec.BooleanValue moveHead;
    public static ForgeConfigSpec.BooleanValue moveChin;
    public static ForgeConfigSpec.BooleanValue moveWings;
    public static ForgeConfigSpec.IntValue headSpeed;
    public static ForgeConfigSpec.IntValue chinSpeed;
    public static ForgeConfigSpec.IntValue wingSpeed;

    public ChickenFeature() {
        super(BuckaChickenModel::new);
    }

    @Override
    public String getName() {
        return "chicken";
    }

    @Override
    public String getDescription() {

        return "This one makes chicken beaks open and close when they cluck. When they strut their heads move back and " +
                "forth, the red thing under their beak swings around and their wings flap a little. Just like the real deal!";
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {

        super.setupConfig(builder);

        moveHead = builder.comment("Move head back and forth when the entity is walking.").define("Move Head", true);
        headSpeed = builder.comment("Animation swing for the head movement.").defineInRange("Head Swing", 4, 1, 20);
        moveChin = builder.comment("Wiggle chin when the entity is walking.").define("Wiggle Chin", true);
        chinSpeed = builder.comment("Animation swing for the chin movement.").defineInRange("Chin Swing", 5, 1, 20);
        moveWings = builder.comment("Flap wings when the entity is walking.").define("Flap Wings", true);
        wingSpeed = builder.comment("Animation swing of the wing flapping.").defineInRange("Wing Swing", 3, 1, 20);
    }

}
