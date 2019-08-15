package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.FlailingEndermanEyesLayer;
import com.fuzs.betteranimationscollection2.renderer.layer.FlailingHeldBlockLayer;
import com.fuzs.betteranimationscollection2.renderer.model.FlailingEndermanModel;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class FlailingEndermanRenderer extends MobRenderer<EndermanEntity, FlailingEndermanModel<EndermanEntity>> {
   private static final ResourceLocation ENDERMAN_TEXTURES = new ResourceLocation("textures/entity/enderman/enderman.png");
   private final Random rnd = new Random();

   public FlailingEndermanRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new FlailingEndermanModel<>(0.0F), 0.5F);
      this.addLayer(new FlailingEndermanEyesLayer<>(this));
      this.addLayer(new FlailingHeldBlockLayer(this));
   }

   public void doRender(EndermanEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
      BlockState blockstate = entity.getHeldBlockState();
      FlailingEndermanModel<EndermanEntity> endermanmodel = this.getEntityModel();
      endermanmodel.isCarrying = blockstate != null;
      endermanmodel.isAttacking = entity.isScreaming();
      if (entity.isScreaming()) {
         x += this.rnd.nextGaussian() * 0.02D;
         z += this.rnd.nextGaussian() * 0.02D;
      }

      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(@Nonnull EndermanEntity entity) {
      return ENDERMAN_TEXTURES;
   }
}