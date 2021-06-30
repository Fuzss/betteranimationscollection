package fuzs.betteranimationscollection.mixin.client;

import fuzs.betteranimationscollection.client.renderer.entity.layers.LayerModelAccessor;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.SheepWoolLayer;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.client.renderer.entity.model.SheepWoolModel;
import net.minecraft.entity.passive.SheepEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(SheepWoolLayer.class)
public abstract class SheepWoolLayerMixin extends LayerRenderer<SheepEntity, SheepModel<SheepEntity>> implements LayerModelAccessor<SheepWoolModel<SheepEntity>> {

    @Shadow
    @Final
    @Mutable
    private SheepWoolModel<SheepEntity> model;

    public SheepWoolLayerMixin(IEntityRenderer<SheepEntity, SheepModel<SheepEntity>> p_i50926_1_) {

        super(p_i50926_1_);
    }

    @Override
    public SheepWoolModel<SheepEntity> getModel() {

        return this.model;
    }

    @Override
    public void setModel(SheepWoolModel<SheepEntity> model) {

        this.model = model;
    }

}
