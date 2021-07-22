package fuzs.betteranimationscollection.mixin.client;

import fuzs.betteranimationscollection.client.renderer.entity.layers.ILayerModelAccessor;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeGelLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(SlimeGelLayer.class)
public abstract class SlimeGelLayerMixin<T extends LivingEntity> extends LayerRenderer<T, SlimeModel<T>> implements ILayerModelAccessor<EntityModel<T>> {

    @Shadow
    @Final
    @Mutable
    private EntityModel<T> model;

    public SlimeGelLayerMixin(IEntityRenderer<T, SlimeModel<T>> p_i50926_1_) {

        super(p_i50926_1_);
    }

    @Override
    public EntityModel<T> getModel() {

        return this.model;
    }

    @Override
    public void setModel(EntityModel<T> model) {

        this.model = model;
    }

}
