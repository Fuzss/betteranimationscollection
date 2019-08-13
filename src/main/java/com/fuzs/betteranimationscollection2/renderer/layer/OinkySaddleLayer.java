package com.fuzs.betteranimationscollection2.renderer.layer;

import com.fuzs.betteranimationscollection2.renderer.model.OinkyPigModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OinkySaddleLayer extends LayerRenderer<PigEntity, OinkyPigModel<PigEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");
    private final PigModel<PigEntity> pigModel = new PigModel<>(0.5F);

    public OinkySaddleLayer(IEntityRenderer<PigEntity, OinkyPigModel<PigEntity>> p_i50927_1_) {
        super(p_i50927_1_);
    }

    public void render(PigEntity entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        if (entityIn.getSaddled()) {
            this.bindTexture(TEXTURE);
            this.getEntityModel().setModelAttributes(this.pigModel);
            this.pigModel.render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}