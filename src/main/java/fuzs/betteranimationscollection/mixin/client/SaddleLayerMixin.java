package fuzs.betteranimationscollection.mixin.client;

import fuzs.betteranimationscollection.client.renderer.entity.layers.LayerModelAccessor;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEquipable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(SaddleLayer.class)
public abstract class SaddleLayerMixin<T extends Entity & IEquipable, M extends EntityModel<T>> extends LayerRenderer<T, M> implements LayerModelAccessor<M> {

    @Shadow
    @Final
    @Mutable
    private M model;

    public SaddleLayerMixin(IEntityRenderer<T, M> p_i50926_1_) {

        super(p_i50926_1_);
    }

    @Override
    public M getModel() {

        return this.model;
    }

    @Override
    public void setModel(M model) {

        this.model = model;
    }

}
