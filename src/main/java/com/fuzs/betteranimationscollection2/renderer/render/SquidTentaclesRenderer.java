package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.model.SquidTentaclesModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class SquidTentaclesRenderer extends MobRenderer<SquidEntity, SquidTentaclesModel<SquidEntity>> {
   private static final ResourceLocation SQUID_TEXTURES = new ResourceLocation("textures/entity/squid.png");

   public SquidTentaclesRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new SquidTentaclesModel<>(), 0.7F);
   }

   protected ResourceLocation getEntityTexture(@Nonnull SquidEntity entity) {
      return SQUID_TEXTURES;
   }

   protected void applyRotations(SquidEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      float f = MathHelper.lerp(partialTicks, entityLiving.prevSquidPitch, entityLiving.squidPitch);
      float f1 = MathHelper.lerp(partialTicks, entityLiving.prevSquidYaw, entityLiving.squidYaw);
      GlStateManager.translatef(0.0F, 0.5F, 0.0F);
      GlStateManager.rotatef(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotatef(f, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotatef(f1, 0.0F, 1.0F, 0.0F);
      GlStateManager.translatef(0.0F, -1.2F, 0.0F);
   }

   protected float handleRotationFloat(SquidEntity livingBase, float partialTicks) {
      return MathHelper.lerp(partialTicks, livingBase.lastTentacleAngle, livingBase.tentacleAngle);
   }
}