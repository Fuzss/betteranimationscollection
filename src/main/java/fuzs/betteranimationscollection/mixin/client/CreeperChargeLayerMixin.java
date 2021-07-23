package fuzs.betteranimationscollection.mixin.client;

import fuzs.betteranimationscollection.client.renderer.entity.layers.ILayerModelAccessor;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperChargeLayer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.entity.monster.CreeperEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(CreeperChargeLayer.class)
public abstract class CreeperChargeLayerMixin extends EnergyLayer<CreeperEntity, CreeperModel<CreeperEntity>> implements ILayerModelAccessor<CreeperModel<CreeperEntity>> {

    @Shadow
    @Final
    @Mutable
    private CreeperModel<CreeperEntity> model;

    public CreeperChargeLayerMixin(IEntityRenderer<CreeperEntity, CreeperModel<CreeperEntity>> p_i50947_1_) {

        super(p_i50947_1_);
    }

    @Override
    public CreeperModel<CreeperEntity> getModel() {

        return this.model;
    }

    @Override
    public void setModel(CreeperModel<CreeperEntity> model) {

        this.model = model;
    }

}
