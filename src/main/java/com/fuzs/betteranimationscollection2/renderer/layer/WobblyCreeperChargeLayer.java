package com.fuzs.betteranimationscollection2.renderer.layer;

import com.fuzs.betteranimationscollection2.renderer.model.WobblyCreeperChargeModel;
import com.fuzs.betteranimationscollection2.renderer.model.WobblyCreeperModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WobblyCreeperChargeLayer extends LayerRenderer<CreeperEntity, WobblyCreeperModel<CreeperEntity>> {
   private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
   private final WobblyCreeperChargeModel<CreeperEntity> creeperModel = new WobblyCreeperChargeModel<>(2.0F);

   public WobblyCreeperChargeLayer(IEntityRenderer<CreeperEntity, WobblyCreeperModel<CreeperEntity>> p_i50947_1_) {
      super(p_i50947_1_);
   }

   public void render(CreeperEntity entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
      if (entityIn.getPowered()) {
         boolean flag = entityIn.isInvisible();
         GlStateManager.depthMask(!flag);
         this.bindTexture(LIGHTNING_TEXTURE);
         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         float f = (float)entityIn.ticksExisted + p_212842_4_;
         GlStateManager.translatef(f * 0.01F, f * 0.01F, 0.0F);
         GlStateManager.matrixMode(5888);
         GlStateManager.enableBlend();
         GlStateManager.color4f(0.5F, 0.5F, 0.5F, 1.0F);
         GlStateManager.disableLighting();
         GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
         this.getEntityModel().setModelAttributes(this.creeperModel);
         GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
         gamerenderer.setupFogColor(true);
         this.creeperModel.render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
         gamerenderer.setupFogColor(false);
         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         GlStateManager.matrixMode(5888);
         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
      }
   }

   public boolean shouldCombineTextures() {
      return false;
   }
}