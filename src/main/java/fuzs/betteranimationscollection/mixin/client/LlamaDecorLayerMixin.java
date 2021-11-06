package fuzs.betteranimationscollection.mixin.client;

import fuzs.betteranimationscollection.client.renderer.entity.layers.ILayerModelAccessor;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LlamaDecorLayer;
import net.minecraft.client.renderer.entity.layers.SheepWoolLayer;
import net.minecraft.client.renderer.entity.model.LlamaModel;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.client.renderer.entity.model.SheepWoolModel;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(LlamaDecorLayer.class)
public abstract class LlamaDecorLayerMixin extends LayerRenderer<LlamaEntity, LlamaModel<LlamaEntity>> implements ILayerModelAccessor<LlamaModel<LlamaEntity>> {

    @Shadow
    @Final
    @Mutable
    private LlamaModel<LlamaEntity> model;

    public LlamaDecorLayerMixin(IEntityRenderer<LlamaEntity, LlamaModel<LlamaEntity>> p_i50933_1_) {

        super(p_i50933_1_);
    }

    @Override
    public LlamaModel<LlamaEntity> getModel() {

        return this.model;
    }

    @Override
    public void setModel(LlamaModel<LlamaEntity> model) {

        this.model = model;
    }

}
