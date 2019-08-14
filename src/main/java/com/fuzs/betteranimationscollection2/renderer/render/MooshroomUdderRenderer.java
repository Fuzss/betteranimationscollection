package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.MooshroomUdderMushroomLayer;
import com.fuzs.betteranimationscollection2.renderer.model.MooshroomUdderModel;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class MooshroomUdderRenderer extends MobRenderer<MooshroomEntity, MooshroomUdderModel<MooshroomEntity>> {

   private static final Map<MooshroomEntity.Type, ResourceLocation> MOOSHROOM_TEXTURES = Util.make(Maps.newHashMap(), (p_217773_0_) -> {
      p_217773_0_.put(MooshroomEntity.Type.BROWN, new ResourceLocation("textures/entity/cow/brown_mooshroom.png"));
      p_217773_0_.put(MooshroomEntity.Type.RED, new ResourceLocation("textures/entity/cow/red_mooshroom.png"));
   });

   public MooshroomUdderRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new MooshroomUdderModel<>(), 0.7F);
      this.addLayer(new MooshroomUdderMushroomLayer<>(this));
   }

   protected ResourceLocation getEntityTexture(MooshroomEntity entity) {
      return MOOSHROOM_TEXTURES.get(entity.getMooshroomType());
   }
}