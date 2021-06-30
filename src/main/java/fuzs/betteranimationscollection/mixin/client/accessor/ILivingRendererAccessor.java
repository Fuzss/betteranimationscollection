package fuzs.betteranimationscollection.mixin.client.accessor;

import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(LivingRenderer.class)
public interface ILivingRendererAccessor<T extends LivingEntity, M extends EntityModel<T>> {

    @Accessor
    void setModel(M model);

    @Accessor
    List<LayerRenderer<T, M>> getLayers();

}
