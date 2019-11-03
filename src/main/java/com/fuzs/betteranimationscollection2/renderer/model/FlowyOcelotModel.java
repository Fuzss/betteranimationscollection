package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureOcelot;
import com.fuzs.betteranimationscollection2.helper.ReflectionHelper;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.OcelotModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;

public class FlowyOcelotModel<T extends Entity> extends OcelotModel<T> {

    private final RendererModel ocelotTail;
    protected int state = 1;

    private final RendererModel[] ocelotTailParts = new RendererModel[FeatureOcelot.length.get()];
    private float progress;

    public FlowyOcelotModel(float scale) {
        super(scale);

        this.ocelotTail = new RendererModel(this, 0, 15);
        this.ocelotTail.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
        this.ocelotTail.rotateAngleX = 0.9F;
        this.ocelotTail.setRotationPoint(0.0F, 15.0F, 8.0F);

        for(int i = 0; i < this.ocelotTailParts.length; ++i) {
            if (i < this.ocelotTailParts.length / 2) {
                this.ocelotTailParts[i] = new RendererModel(this, 0, 16 + i);
                this.ocelotTailParts[i].addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
                this.ocelotTailParts[i].setRotationPoint(0.0F, 1.0F, 0.0F);
                if (i == 0) {
                    this.ocelotTail.addChild(this.ocelotTailParts[i]);
                } else {
                    this.ocelotTailParts[i - 1].addChild(this.ocelotTailParts[i]);
                }
            } else {
                this.ocelotTailParts[i] = new RendererModel(this, 4, 8 + i);
                this.ocelotTailParts[i].addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
                this.ocelotTailParts[i].setRotationPoint(0.0F, 1.0F, 0.0F);
                this.ocelotTailParts[i - 1].addChild(this.ocelotTailParts[i]);
            }
        }

        ReflectionHelper.setModelPart(this, this.ocelotTail, ReflectionHelper.OCELOTMODEL_OCELOTTAIL);
        ((RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.OCELOTMODEL_OCELOTTAIL2)).cubeList.clear();
    }

    public void render(@Nonnull T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.progress = ageInTicks / 4.66F;
    }

    public void setRotationAngles(T p_212844_1_, float p_212844_2_, float p_212844_3_, float p_212844_4_, float p_212844_5_, float p_212844_6_, float p_212844_7_) {
        this.ocelotHead.rotateAngleX = p_212844_6_ * 0.017453292F;
        this.ocelotHead.rotateAngleY = p_212844_5_ * 0.017453292F;
        if (this.state != 3) {
            this.ocelotBody.rotateAngleX = 1.5707964F;
            if (this.state == 2) {
                this.ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(p_212844_2_ * 0.6662F) * p_212844_3_;
                this.ocelotBackRightLeg.rotateAngleX = MathHelper.cos(p_212844_2_ * 0.6662F + 0.3F) * p_212844_3_;
                this.ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(p_212844_2_ * 0.6662F + 3.1415927F + 0.3F) * p_212844_3_;
                this.ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(p_212844_2_ * 0.6662F + 3.1415927F) * p_212844_3_;
                this.ocelotTail2.rotateAngleX = 1.7278761F + 0.31415927F * MathHelper.cos(p_212844_2_) * p_212844_3_;
            } else {
                this.ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(p_212844_2_ * 0.6662F) * p_212844_3_;
                this.ocelotBackRightLeg.rotateAngleX = MathHelper.cos(p_212844_2_ * 0.6662F + 3.1415927F) * p_212844_3_;
                this.ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(p_212844_2_ * 0.6662F + 3.1415927F) * p_212844_3_;
                this.ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(p_212844_2_ * 0.6662F) * p_212844_3_;
                if (this.state == 1) {
                    this.ocelotTail2.rotateAngleX = 1.7278761F + 0.7853982F * MathHelper.cos(p_212844_2_) * p_212844_3_;
                } else {
                    this.ocelotTail2.rotateAngleX = 1.7278761F + 0.47123894F * MathHelper.cos(p_212844_2_) * p_212844_3_;
                }
            }
        }

    }

    public void setLivingAnimations(T p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
        this.ocelotBody.rotationPointY = 12.0F;
        this.ocelotBody.rotationPointZ = -10.0F;
        this.ocelotHead.rotationPointY = 15.0F;
        this.ocelotHead.rotationPointZ = -9.0F;
        this.ocelotTail.rotationPointY = 15.0F;
        this.ocelotTail.rotationPointZ = 8.0F;
        this.ocelotTail2.rotationPointY = 20.0F;
        this.ocelotTail2.rotationPointZ = 14.0F;
        this.ocelotFrontLeftLeg.rotationPointY = 14.1F;
        this.ocelotFrontLeftLeg.rotationPointZ = -5.0F;
        this.ocelotFrontRightLeg.rotationPointY = 14.1F;
        this.ocelotFrontRightLeg.rotationPointZ = -5.0F;
        this.ocelotBackLeftLeg.rotationPointY = 18.0F;
        this.ocelotBackLeftLeg.rotationPointZ = 5.0F;
        this.ocelotBackRightLeg.rotationPointY = 18.0F;
        this.ocelotBackRightLeg.rotationPointZ = 5.0F;
        this.ocelotTail.rotateAngleX = 0.9F;
        RendererModel var10000;
        if (p_212843_1_.isSneaking()) {
            ++this.ocelotBody.rotationPointY;
            var10000 = this.ocelotHead;
            var10000.rotationPointY += 2.0F;
            ++this.ocelotTail.rotationPointY;
            var10000 = this.ocelotTail2;
            var10000.rotationPointY += -4.0F;
            var10000 = this.ocelotTail2;
            var10000.rotationPointZ += 2.0F;
            this.ocelotTail.rotateAngleX = 1.5707964F;
            this.ocelotTail2.rotateAngleX = 1.5707964F;
            this.state = 0;
        } else if (p_212843_1_.isSprinting()) {
            this.ocelotTail2.rotationPointY = this.ocelotTail.rotationPointY;
            var10000 = this.ocelotTail2;
            var10000.rotationPointZ += 2.0F;
            this.ocelotTail.rotateAngleX = 1.5707964F;
            this.ocelotTail2.rotateAngleX = 1.5707964F;
            this.state = 2;
        } else {
            this.state = 1;
        }

    }
}
