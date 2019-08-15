package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.WobblyCreeperChargeLayer;
import com.fuzs.betteranimationscollection2.renderer.model.WobblyCreeperModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class WobblyCreeperRenderer extends MobRenderer<CreeperEntity, WobblyCreeperModel<CreeperEntity>> {
   private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation("textures/entity/creeper/creeper.png");

   public WobblyCreeperRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new WobblyCreeperModel<>(), 0.5F);
      this.addLayer(new WobblyCreeperChargeLayer(this));
   }

   protected void preRenderCallback(CreeperEntity entitylivingbaseIn, float partialTickTime) {
      float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
      float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
      f = MathHelper.clamp(f, 0.0F, 1.0F);
      f = f * f;
      f = f * f;
      float f2 = (1.0F + f * 0.4F) * f1;
      float f3 = (1.0F + f * 0.1F) / f1;
      GlStateManager.scalef(f2, f3, f2);
   }

   protected int getColorMultiplier(CreeperEntity entitylivingbaseIn, float lightBrightness, float partialTickTime) {
      float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
      if ((int)(f * 10.0F) % 2 == 0) {
         return 0;
      } else {
         int i = (int)(f * 0.2F * 255.0F);
         i = MathHelper.clamp(i, 0, 255);
         return i << 24 | 822083583;
      }
   }

   protected ResourceLocation getEntityTexture(@Nonnull CreeperEntity entity) {
      return CREEPER_TEXTURES;
   }
}