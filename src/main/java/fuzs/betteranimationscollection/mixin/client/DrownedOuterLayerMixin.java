package fuzs.betteranimationscollection.mixin.client;

import fuzs.betteranimationscollection.client.renderer.entity.layers.ILayerModelAccessor;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.DrownedOuterLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.DrownedModel;
import net.minecraft.entity.monster.DrownedEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(DrownedOuterLayer.class)
public abstract class DrownedOuterLayerMixin<T extends DrownedEntity> extends LayerRenderer<T, DrownedModel<T>> implements ILayerModelAccessor<DrownedModel<T>> {

    @Shadow
    @Final
    @Mutable
    private DrownedModel<T> model;

    public DrownedOuterLayerMixin(IEntityRenderer<T, DrownedModel<T>> p_i50943_1_) {

        super(p_i50943_1_);
    }

    @Override
    public DrownedModel<T> getModel() {

        return this.model;
    }

    @Override
    public void setModel(DrownedModel<T> model) {

        this.model = model;
    }
    
}
