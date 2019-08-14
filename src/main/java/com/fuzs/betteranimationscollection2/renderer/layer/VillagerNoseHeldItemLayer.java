package com.fuzs.betteranimationscollection2.renderer.layer;

import com.fuzs.betteranimationscollection2.renderer.model.VillagerNoseModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VillagerNoseHeldItemLayer<T extends LivingEntity> extends LayerRenderer<T, VillagerNoseModel<T>> {
   private final ItemRenderer field_215347_a = Minecraft.getInstance().getItemRenderer();

   public VillagerNoseHeldItemLayer(IEntityRenderer<T, VillagerNoseModel<T>> p_i50917_1_) {
      super(p_i50917_1_);
   }

   public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
      ItemStack itemstack = entityIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
      if (!itemstack.isEmpty()) {
         Item item = itemstack.getItem();
         Block block = Block.getBlockFromItem(item);
         GlStateManager.pushMatrix();
         boolean flag = this.field_215347_a.shouldRenderItemIn3D(itemstack) && block.getRenderLayer() == BlockRenderLayer.TRANSLUCENT;
         if (flag) {
            GlStateManager.depthMask(false);
         }

         GlStateManager.translatef(0.0F, 0.4F, -0.4F);
         GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
         this.field_215347_a.renderItem(itemstack, entityIn, ItemCameraTransforms.TransformType.GROUND, false);
         if (flag) {
            GlStateManager.depthMask(true);
         }

         GlStateManager.popMatrix();
      }
   }

   public boolean shouldCombineTextures() {
      return false;
   }
}