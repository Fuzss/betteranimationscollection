package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.AnimatedSnowmanStickHeadLayer;
import com.fuzs.betteranimationscollection2.renderer.model.AnimatedSnowManStickModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SnowmanHeadLayer;
import net.minecraft.client.renderer.entity.model.SnowManModel;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnimatedSnowManStickRenderer extends MobRenderer<SnowGolemEntity, AnimatedSnowManStickModel<SnowGolemEntity>> {
   private static final ResourceLocation SNOW_MAN_TEXTURES = new ResourceLocation("textures/entity/snow_golem.png");

   public AnimatedSnowManStickRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new AnimatedSnowManStickModel<>(), 0.5F);
      this.addLayer(new AnimatedSnowmanStickHeadLayer(this));
   }

   /**
    * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
    */
   protected ResourceLocation getEntityTexture(SnowGolemEntity entity) {
      return SNOW_MAN_TEXTURES;
   }
}