package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.BuckaChickenModel;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.SoundEvents;

public class BuckaChickenElement extends SoundModelElement {

    public boolean moveHead;
    public boolean moveChin;
    public boolean moveWings;
    public int headSpeed;
    public int chinSpeed;
    public int wingSpeed;

    public BuckaChickenElement() {

        super(ChickenEntity.class);
        this.addDefaultSound(SoundEvents.CHICKEN_AMBIENT);
    }

    @Override
    public String[] getDescription() {

        return new String[]{"This one makes chicken beaks open and close when they cluck.", 
                "When they strut their heads move back and forth, the red thing under their beak swings around and their wings flap a little. Just like the real deal!"};
    }

    @Override
    public void setupClientConfig(OptionsBuilder builder) {

        super.setupClientConfig(builder);
        builder.define("Move Head", true).comment("Move head back and forth when the entity is walking.").sync(v -> this.moveHead = v);
        builder.define("Wiggle Chin", true).comment("Wiggle chin when the entity is walking.").sync(v -> this.moveChin = v);
        builder.define("Flap Wings", true).comment("Flap wings when the entity is walking.").sync(v -> this.moveWings = v);
        builder.define("Head Swing", 4).min(1).max(20).comment("Animation swing for the head movement.").sync(v -> this.headSpeed = v);
        builder.define("Chin Swing", 5).min(1).max(20).comment("Animation swing for the chin movement.").sync(v -> this.chinSpeed = v);
        builder.define("Wing Swing", 3).min(1).max(20).comment("Animation swing of the wing flapping.").sync(v -> this.wingSpeed = v);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new BuckaChickenModel<>();
    }

}
