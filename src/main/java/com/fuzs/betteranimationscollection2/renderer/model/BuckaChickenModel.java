package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.ChickenFeature;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.ChickenModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class BuckaChickenModel<T extends Entity> extends ChickenModel<T> {

    protected ModelRenderer billBottom;

    public BuckaChickenModel() {

        ModelRenderer billTop = new ModelRenderer(this, 14, 0);
        billTop.addBox(-2.0F, -4.0F, -4.0F, 4, 1, 2, 0.0F);
        this.head.addChild(billTop);

        this.billBottom = new ModelRenderer(this, 14, 1);
        this.billBottom.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 2, 0.0F);
        this.billBottom.setRotationPoint(0.0F, -3.0F, -2.0F);
        billTop.addChild(this.billBottom);

        this.chin = new ModelRenderer(this, 14, 4);
        this.chin.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.chin.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.billBottom.addChild(this.chin);

        // fix rotation point to be at the body and not in the air
        this.rightWing = new ModelRenderer(this, 24, 13);
        this.rightWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6);
        this.rightWing.setRotationPoint(-3.0F, 13.0F, 0.0F);
        this.leftWing = new ModelRenderer(this, 24, 13);
        this.leftWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6);
        this.leftWing.setRotationPoint(3.0F, 13.0F, 0.0F);

    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
//    public void func_225598_a_(@Nonnull MatrixStack p_225598_1_, @Nonnull IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
//
//        float scale = 1.0F / 16.0F;
//        if (this.isChild) {
//            p_225598_1_.func_227860_a_();
//            p_225598_1_.func_227861_a_(0.0F, 5.0F * scale, 2.0F * scale);
//            this.head.func_228309_a_(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
//            p_225598_1_.func_227865_b_();
//            p_225598_1_.func_227860_a_();
//            p_225598_1_.func_227862_a_(0.5F, 0.5F, 0.5F);
//            p_225598_1_.func_227861_a_(0.0F, 24.0F * scale, 0.0F);
//            ImmutableList.of(this.body, this.field_78137_g, this.field_78143_h, this.rightWing, this.leftWing).forEach((p_228228_8_) -> {
//                p_228228_8_.func_228309_a_(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
//            });
//            p_225598_1_.func_227865_b_();
//        } else {
//            ImmutableList.of(this.head, this.body, this.field_78137_g, this.field_78143_h, this.rightWing, this.leftWing).forEach((p_228228_8_) -> {
//                p_228228_8_.func_228309_a_(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
//            });
//        }
//    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleX = ((float) Math.PI / 2F);
//        this.field_78137_g.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        this.field_78143_h.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        if (ChickenFeature.moveWings.get() && ageInTicks == 0) {
            float f = limbSwingAmount * ChickenFeature.wingSpeed.get() * 0.1F;
            this.rightWing.rotateAngleZ = MathHelper.sin(limbSwing) * f + f;
            this.leftWing.rotateAngleZ = -(MathHelper.sin(limbSwing) * f + f);
        } else {
            this.rightWing.rotateAngleZ = ageInTicks;
            this.leftWing.rotateAngleZ = -ageInTicks;
        }
        if (ChickenFeature.moveHead.get()) {
            this.head.rotationPointZ = -4.0F + MathHelper.cos(limbSwing) * ChickenFeature.headSpeed.get() * 0.5F * limbSwingAmount;
        }
        if (ChickenFeature.moveChin.get()) {
            this.chin.rotateAngleX = MathHelper.sin(limbSwing) * (float) ChickenFeature.chinSpeed.get() * 0.1F * limbSwingAmount;
            this.chin.rotateAngleX -= this.billBottom.rotateAngleX;
        }
    }

    public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        // this only works because MobEntity#livingSoundTime is manually being synced to the client in SoundEventHandler
        if (entitylivingbaseIn instanceof MobEntity) {

            MobEntity entitylivingIn = (MobEntity) entitylivingbaseIn;
            int time = entitylivingIn.livingSoundTime + entitylivingIn.getTalkInterval();
            if (time > 8) {
                time = 0;
            }

            if (time > 0 && time < 8) {
                float rotate = Math.abs(MathHelper.sin((float) time * (float) Math.PI / 5.0F));
                this.billBottom.rotateAngleX = rotate * 0.75F;
            } else {
                this.billBottom.rotateAngleX = 0.0F;
            }
        }

    }

}