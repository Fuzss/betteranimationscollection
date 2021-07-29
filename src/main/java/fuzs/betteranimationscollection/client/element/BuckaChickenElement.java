package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.BuckaChickenModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.SoundEvents;

public class BuckaChickenElement extends SoundModelElement {

    public boolean slimBill;
    public boolean moveHead;
    public boolean moveWattles;
    public boolean moveWings;
    public int headAnimationSpeed;
    public int wattlesAnimationSpeed;
    public int wingAnimationSpeed;

    public BuckaChickenElement() {

        this.defaultSounds.add(SoundEvents.CHICKEN_AMBIENT);
    }

    @Override
    public String[] getDescription() {

        return new String[]{"This one makes chicken beaks open and close when they cluck.", 
                "When they strut their heads move back and forth, the red thing under their beak swings around and their wings flap a little. Just like the real deal!"};
    }

    @Override
    public void setupModelConfig(OptionsBuilder builder) {

        super.setupModelConfig(builder);
        builder.define("Slim Bill", true).comment("Make bill a lot slimmer so chickens look less like ducks.").sync(v -> this.slimBill = v).restart();
        builder.define("Move Head", true).comment("Move head back and forth when chicken is walking.").sync(v -> this.moveHead = v);
        builder.define("Wiggle Wattles", true).comment("Wiggle chin when chicken is walking.").sync(v -> this.moveWattles = v);
        builder.define("Flap Wings", true).comment("Flap wings when chicken is walking.").sync(v -> this.moveWings = v);
        builder.define("Head Animation Speed", 4).min(1).max(20).comment("Animation swing speed for head movement.").sync(v -> this.headAnimationSpeed = v);
        builder.define("Wattles Animation Speed", 5).min(1).max(20).comment("Animation swing speed for wattles movement.").sync(v -> this.wattlesAnimationSpeed = v);
        builder.define("Wing Animation Speed", 3).min(1).max(20).comment("Animation swing speed of wing flapping.").sync(v -> this.wingAnimationSpeed = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new BuckaChickenModel<>();
    }

    @Override
    protected Class<? extends MobEntity> getMobClazz() {

        return ChickenEntity.class;
    }

}
