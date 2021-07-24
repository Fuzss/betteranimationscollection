package fuzs.betteranimationscollection.client.element;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

import java.util.List;
import java.util.function.Supplier;

public abstract class MultiModelElement extends ModelElement {

    @Override
    protected final EntityModel<? extends LivingEntity> getEntityModel() {

        return null;
    }

    @Override
    protected abstract List<Supplier<EntityModel<? extends LivingEntity>>> getEntityModels();

}
