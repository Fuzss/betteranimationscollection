package com.fuzs.betteranimationscollection2.renderer.layer;

import com.fuzs.betteranimationscollection2.renderer.model.WolfTailModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WolfTailCollarLayer extends LayerRenderer<WolfEntity, WolfTailModel<WolfEntity>> {
   private static final ResourceLocation WOLF_COLLAR = new ResourceLocation("textures/entity/wolf/wolf_collar.png");

   public WolfTailCollarLayer(IEntityRenderer<WolfEntity, WolfTailModel<WolfEntity>> p_i50914_1_) {
      super(p_i50914_1_);
   }

   public void render(WolfEntity entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
      if (entityIn.isTamed() && !entityIn.isInvisible()) {
         this.bindTexture(WOLF_COLLAR);
         float[] afloat = entityIn.getCollarColor().getColorComponentValues();
         GlStateManager.color3f(afloat[0], afloat[1], afloat[2]);
         this.getEntityModel().render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
      }
   }

   public boolean shouldCombineTextures() {
      return true;
   }
}