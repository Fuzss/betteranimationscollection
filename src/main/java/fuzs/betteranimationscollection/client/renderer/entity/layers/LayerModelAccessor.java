package fuzs.betteranimationscollection.client.renderer.entity.layers;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;

public interface LayerModelAccessor<T extends EntityModel<? extends Entity>> {

    T getModel();

    void setModel(T model);

}
