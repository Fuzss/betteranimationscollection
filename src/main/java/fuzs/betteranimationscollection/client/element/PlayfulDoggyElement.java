package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.PlayfulDoggyModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class PlayfulDoggyElement extends ModelElement {

    public int tailLength;
    public boolean fluffyTail;
    public int animationSpeed;
    public SittingBehaviour sittingBehaviour;

    @Override
    public String[] getDescription() {

        return new String[]{"Changes wolf tails to be fluffier and flowier, wagging realistically while they stand and run.",
                "Also makes tamed wolves lie down instead of sitting. Hold up some meat and they'll roll over, too."};
    }

    @Override
    public void setupModelConfig(OptionsBuilder builder) {

        builder.define("Tail Length", 7).min(1).max(7).comment("Define tail length.").sync(v -> this.tailLength = v).restart();
        builder.define("Fluffy Tail", true).comment("Make wolf tail fluffy.").sync(v -> this.fluffyTail = v).restart();
        builder.define("Animation Speed", 5).min(1).max(20).comment("Animation swing speed for tail.").sync(v -> this.animationSpeed = v);
        builder.define("Sitting Behaviour", SittingBehaviour.LIE_DOWN_AND_BEG_FOR_MEAT).comment("Pose and behaviour when sitting.", "By default makes wolves lie down instead, and roll over when a nearby player is holding a piece meat.").sync(v -> this.sittingBehaviour = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new PlayfulDoggyModel<>();
    }

    @SuppressWarnings("unused")
    public enum SittingBehaviour {

        DEFAULT(false, false, false),
        LIE_DOWN(true, false, false),
        ROLL_OVER(true, true, false),
        LIE_DOWN_AND_BEG_FOR_MEAT(true, true, true);

        private final boolean lieDown;
        private final boolean rollOver;
        private final boolean begForMeat;

        SittingBehaviour(boolean lieDown, boolean rollOver, boolean begForMeat) {

            this.lieDown = lieDown;
            this.rollOver = rollOver;
            this.begForMeat = begForMeat;
        }

        public boolean lieDown() {

            return this.lieDown;
        }

        public boolean rollOver() {

            return this.rollOver;
        }

        public boolean begForMeat() {

            return this.begForMeat;
        }

    }

}
