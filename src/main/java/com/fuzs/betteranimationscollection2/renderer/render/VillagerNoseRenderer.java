package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.VillagerNoseHeldItemLayer;
import com.fuzs.betteranimationscollection2.renderer.model.VillagerNoseModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.VillagerLevelPendantLayer;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class VillagerNoseRenderer extends MobRenderer<VillagerEntity, VillagerNoseModel<VillagerEntity>> {
   private static final ResourceLocation VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/villager.png");

   public VillagerNoseRenderer(EntityRendererManager p_i50954_1_, IReloadableResourceManager p_i50954_2_) {
      super(p_i50954_1_, new VillagerNoseModel<>(0.0F), 0.5F);
      this.addLayer(new HeadLayer<>(this));
      this.addLayer(new VillagerLevelPendantLayer<>(this, p_i50954_2_, "villager"));
      this.addLayer(new VillagerNoseHeldItemLayer<>(this));
   }

   protected ResourceLocation getEntityTexture(@Nonnull VillagerEntity entity) {
      return VILLAGER_TEXTURES;
   }

   protected void preRenderCallback(VillagerEntity entitylivingbaseIn, float partialTickTime) {
      float f = 0.9375F;
      if (entitylivingbaseIn.isChild()) {
         f = (float)((double)f * 0.5D);
         this.shadowSize = 0.25F;
      } else {
         this.shadowSize = 0.5F;
      }

      GlStateManager.scalef(f, f, f);
   }
}