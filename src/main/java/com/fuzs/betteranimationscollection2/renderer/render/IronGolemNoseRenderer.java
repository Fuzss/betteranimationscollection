package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.IronGolemNoseFlowerLayer;
import com.fuzs.betteranimationscollection2.renderer.model.IronGolemNoseModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class IronGolemNoseRenderer extends MobRenderer<IronGolemEntity, IronGolemNoseModel<IronGolemEntity>> {
   private static final ResourceLocation IRON_GOLEM_TEXTURES = new ResourceLocation("textures/entity/iron_golem.png");

   public IronGolemNoseRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new IronGolemNoseModel<>(), 0.7F);
      this.addLayer(new IronGolemNoseFlowerLayer(this));
   }

   protected ResourceLocation getEntityTexture(@Nonnull IronGolemEntity entity) {
      return IRON_GOLEM_TEXTURES;
   }

   protected void applyRotations(IronGolemEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
      if (!((double)entityLiving.limbSwingAmount < 0.01D)) {
         float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
         float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
         GlStateManager.rotatef(6.5F * f2, 0.0F, 0.0F, 1.0F);
      }
   }
}