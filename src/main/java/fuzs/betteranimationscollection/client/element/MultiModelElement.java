package fuzs.betteranimationscollection.client.element;

import fuzs.puzzleslib.config.option.OptionsBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;

import java.util.List;
import java.util.function.Supplier;

public abstract class MultiModelElement extends SoundModelElement {

    @Override
    protected final EntityModel<? extends LivingEntity> getEntityModel() {

        return null;
    }

    @Override
    protected abstract List<Supplier<EntityModel<? extends LivingEntity>>> getEntityModels();

    @Override
    protected Class<? extends MobEntity> getMobClazz() {

        return null;
    }

    @Override
    public void setupModelConfig(OptionsBuilder builder) {

        if (this.getMobClazz() != null) {

            super.setupModelConfig(builder);
        }
    }

}
