package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.OinkyPigModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public class OinkyPigElement extends ModelElement {

    @Override
    public String[] getDescription() {

        return new String[]{"A very subtle animation, makes a pig's snout move when it oinks.",
                "It only moves up and down ever so slightly, but it's there. Just a little bit more life for your livestock."};
    }

    @Override
    public void setupClient() {

        SyncSoundElement.addNoisyEntity(PigEntity.class, SoundEvents.PIG_AMBIENT);
    }

    @Override
    protected EntityModel<?> getEntityModel() {

        return new OinkyPigModel<>();
    }

}
