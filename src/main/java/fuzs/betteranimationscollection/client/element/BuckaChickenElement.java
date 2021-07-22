package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.BuckaChickenModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.SoundEvents;

public class BuckaChickenElement extends SoundModelElement {

    public boolean moveHead;
    public boolean moveChin;
    public boolean moveWings;
    public int headAnimationSpeed;
    public int chinAnimationSpeed;
    public int wingAnimationSpeed;

    public BuckaChickenElement() {

        this.defaultSounds.add(SoundEvents.CHICKEN_AMBIENT.getRegistryName());
    }

    @Override
    public String[] getDescription() {

        return new String[]{"This one makes chicken beaks open and close when they cluck.", 
                "When they strut their heads move back and forth, the red thing under their beak swings around and their wings flap a little. Just like the real deal!"};
    }

    @Override
    public void setupModelConfig(OptionsBuilder builder) {

        super.setupModelConfig(builder);
        builder.define("Move Head", true).comment("Move head back and forth when the entity is walking.").sync(v -> this.moveHead = v);
        builder.define("Wiggle Chin", true).comment("Wiggle chin when the entity is walking.").sync(v -> this.moveChin = v);
        builder.define("Flap Wings", true).comment("Flap wings when the entity is walking.").sync(v -> this.moveWings = v);
        builder.define("Head Animation Speed", 4).min(1).max(20).comment("Animation swing speed for the head movement.").sync(v -> this.headAnimationSpeed = v);
        builder.define("Chin Animation Speed", 5).min(1).max(20).comment("Animation swing speed for the chin movement.").sync(v -> this.chinAnimationSpeed = v);
        builder.define("Wing Animation Speed", 3).min(1).max(20).comment("Animation swing speed of the wing flapping.").sync(v -> this.wingAnimationSpeed = v);
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
