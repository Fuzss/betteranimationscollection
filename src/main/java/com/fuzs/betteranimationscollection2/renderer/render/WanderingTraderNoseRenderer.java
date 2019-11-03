package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.VillagerNoseHeldItemLayer;
import com.fuzs.betteranimationscollection2.renderer.model.VillagerNoseModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.VillagerHeldItemLayer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class WanderingTraderNoseRenderer extends MobRenderer<WanderingTraderEntity, VillagerNoseModel<WanderingTraderEntity>> {
   private static final ResourceLocation field_217780_a = new ResourceLocation("textures/entity/wandering_trader.png");

   public WanderingTraderNoseRenderer(EntityRendererManager p_i50953_1_) {
      super(p_i50953_1_, new VillagerNoseModel<>(0.0F), 0.5F);
      this.addLayer(new HeadLayer<>(this));
      this.addLayer(new VillagerNoseHeldItemLayer<>(this));
   }

   protected ResourceLocation getEntityTexture(@Nonnull WanderingTraderEntity entity) {
      return field_217780_a;
   }

   protected void preRenderCallback(WanderingTraderEntity entitylivingbaseIn, float partialTickTime) {
      GlStateManager.scalef(0.9375F, 0.9375F, 0.9375F);
   }
}