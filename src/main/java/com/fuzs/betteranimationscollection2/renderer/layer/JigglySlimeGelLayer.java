package com.fuzs.betteranimationscollection2.renderer.layer;

import com.fuzs.betteranimationscollection2.renderer.model.JigglySlimeModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JigglySlimeGelLayer<T extends Entity> extends LayerRenderer<T, JigglySlimeModel<T>> {
   private final EntityModel<T> slimeModel = new JigglySlimeModel<>(0);

   public JigglySlimeGelLayer(IEntityRenderer<T, JigglySlimeModel<T>> p_i50923_1_) {
      super(p_i50923_1_);
   }

   public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
      if (!entityIn.isInvisible()) {
         GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableNormalize();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
         this.getEntityModel().setModelAttributes(this.slimeModel);
         this.slimeModel.render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
         GlStateManager.disableBlend();
         GlStateManager.disableNormalize();
      }
   }

   public boolean shouldCombineTextures() {
      return true;
   }
}