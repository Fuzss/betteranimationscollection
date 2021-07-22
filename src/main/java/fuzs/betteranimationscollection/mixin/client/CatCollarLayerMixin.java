package fuzs.betteranimationscollection.mixin.client;

import fuzs.betteranimationscollection.client.renderer.entity.layers.ILayerModelAccessor;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CatCollarLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.CatModel;
import net.minecraft.entity.passive.CatEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(CatCollarLayer.class)
public abstract class CatCollarLayerMixin extends LayerRenderer<CatEntity, CatModel<CatEntity>> implements ILayerModelAccessor<CatModel<CatEntity>> {

    @Shadow
    @Final
    @Mutable
    private CatModel<CatEntity> catModel;

    public CatCollarLayerMixin(IEntityRenderer<CatEntity, CatModel<CatEntity>> p_i50926_1_) {

        super(p_i50926_1_);
    }

    @Override
    public CatModel<CatEntity> getModel() {

        return this.catModel;
    }

    @Override
    public void setModel(CatModel<CatEntity> model) {

        this.catModel = model;
    }

}
