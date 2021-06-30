package fuzs.betteranimationscollection.mixin.client.accessor;

import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEquipable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SaddleLayer.class)
public interface ISaddleLayerAccessor<T extends Entity & IEquipable, M extends EntityModel<T>> {

    @Accessor
    void setModel(M model);

}
