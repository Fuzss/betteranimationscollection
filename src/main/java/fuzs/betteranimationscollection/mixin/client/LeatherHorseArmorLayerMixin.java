package fuzs.betteranimationscollection.mixin.client;

import fuzs.betteranimationscollection.client.renderer.entity.layers.ILayerModelAccessor;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LeatherHorseArmorLayer;
import net.minecraft.client.renderer.entity.layers.SheepWoolLayer;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.client.renderer.entity.model.SheepWoolModel;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(LeatherHorseArmorLayer.class)
public abstract class LeatherHorseArmorLayerMixin extends LayerRenderer<HorseEntity, HorseModel<HorseEntity>> implements ILayerModelAccessor<HorseModel<HorseEntity>> {

    @Shadow
    @Final
    @Mutable
    private HorseModel<HorseEntity> model;

    public LeatherHorseArmorLayerMixin(IEntityRenderer<HorseEntity, HorseModel<HorseEntity>> p_i50937_1_) {

        super(p_i50937_1_);
    }

    @Override
    public HorseModel<HorseEntity> getModel() {

        return this.model;
    }

    @Override
    public void setModel(HorseModel<HorseEntity> model) {

        this.model = model;
    }

}
