package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.KneelingSheepWoolLayer;
import com.fuzs.betteranimationscollection2.renderer.model.KneelingSheepModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class KneelingSheepRenderer extends MobRenderer<SheepEntity, KneelingSheepModel<SheepEntity>> {
   private static final ResourceLocation SHEARED_SHEEP_TEXTURES = new ResourceLocation("textures/entity/sheep/sheep.png");

   public KneelingSheepRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new KneelingSheepModel<>(), 0.7F);
      this.addLayer(new KneelingSheepWoolLayer(this));
   }

   protected ResourceLocation getEntityTexture(@Nonnull SheepEntity entity) {
      return SHEARED_SHEEP_TEXTURES;
   }
}