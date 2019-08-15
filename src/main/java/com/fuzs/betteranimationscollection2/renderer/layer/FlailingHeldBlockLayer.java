package com.fuzs.betteranimationscollection2.renderer.layer;

import com.fuzs.betteranimationscollection2.feature.FeatureEnderman;
import com.fuzs.betteranimationscollection2.renderer.model.FlailingEndermanModel;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlailingHeldBlockLayer extends LayerRenderer<EndermanEntity, FlailingEndermanModel<EndermanEntity>> {
   public FlailingHeldBlockLayer(IEntityRenderer<EndermanEntity, FlailingEndermanModel<EndermanEntity>> p_i50949_1_) {
      super(p_i50949_1_);
   }

   public void render(EndermanEntity entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
      BlockState blockstate = entityIn.getHeldBlockState();
      if (blockstate != null) {
         GlStateManager.enableRescaleNormal();
         GlStateManager.pushMatrix();
         float f = 0.0F;
         if (FeatureEnderman.whileCarrying.get() && entityIn.isScreaming()) {
            float f1 = (float) FeatureEnderman.speed.get() * 0.025F;
            f = MathHelper.cos(p_212842_5_ * f1 * 5 + (float) 13 * 0.45F) * ((float) (13 + 8) / 8.0F) * f1;
         }
         GlStateManager.translatef(-2.0F * f, 0.6875F, -0.75F);
         GlStateManager.rotatef(20.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translatef(0.25F, 0.1875F, 0.25F);
         GlStateManager.scalef(-0.5F, -0.5F, 0.5F);
         int i = entityIn.getBrightnessForRender();
         int j = i % 65536;
         int k = i / 65536;
         GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float)j, (float)k);
         GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
         Minecraft.getInstance().getBlockRendererDispatcher().renderBlockBrightness(blockstate, 1.0F);
         GlStateManager.popMatrix();
         GlStateManager.disableRescaleNormal();
      }
   }

   public boolean shouldCombineTextures() {
      return false;
   }
}