package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.SnowmanStickHeadLayer;
import com.fuzs.betteranimationscollection2.renderer.model.SnowManStickModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class SnowManStickRenderer extends MobRenderer<SnowGolemEntity, SnowManStickModel<SnowGolemEntity>> {
   private static final ResourceLocation SNOW_MAN_TEXTURES = new ResourceLocation("textures/entity/snow_golem.png");

   public SnowManStickRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new SnowManStickModel<>(), 0.5F);
      this.addLayer(new SnowmanStickHeadLayer(this));
   }

   /**
    * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
    */
   protected ResourceLocation getEntityTexture(@Nonnull SnowGolemEntity entity) {
      return SNOW_MAN_TEXTURES;
   }
}