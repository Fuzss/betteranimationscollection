package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureWolf;
import com.fuzs.betteranimationscollection2.helper.ReflectionHelper;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WolfTailModel<T extends WolfEntity> extends WolfModel<T> {

    private final RendererModel head;
    private final RendererModel body;
    private final RendererModel leg1;
    private final RendererModel leg2;
    private final RendererModel leg3;
    private final RendererModel leg4;
    private final RendererModel tail;
    private final RendererModel mane;

    private final RendererModel[] tailParts = new RendererModel[7];

    public WolfTailModel() {
        this.tail = new RendererModel(this, 9, 18);
        this.tail.addBox(0.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.tail.setRotationPoint(-1.0F, 12.0F, 8.0F);

        for(int i = 0; i < this.tailParts.length; ++i) {
            this.tailParts[i] = new RendererModel(this, 9, Math.min(19 + i, 25));
            float fluffy = 0.0F;

            if (FeatureWolf.fluffy.get()) {
                if (i < 5) {
                    fluffy = 0.1F + 0.1F * (float) i;
                } else if (i == 5) {
                    fluffy = 0.4F;
                } else {
                    fluffy = 0.15F;
                }
            }

            this.tailParts[i].addBox(0.0F, 0.0F, -1.0F, 2, 1, 2, fluffy);
            this.tailParts[i].setRotationPoint(0.0F, 1.0F + fluffy, 0.0F);
            if (i == 0) {
                this.tail.addChild(this.tailParts[i]);
            } else {
                this.tailParts[i - 1].addChild(this.tailParts[i]);
            }
        }

        this.head = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.WOLFMODEL_HEAD);
        this.body = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.WOLFMODEL_BODY);
        this.leg1 = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.WOLFMODEL_LEG1);
        this.leg2 = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.WOLFMODEL_LEG2);
        this.leg3 = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.WOLFMODEL_LEG3);
        this.leg4 = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.WOLFMODEL_LEG4);
        this.mane = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.WOLFMODEL_MANE);
        ReflectionHelper.setModelPart(this, this.tail, ReflectionHelper.WOLFMODEL_TAIL);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        GlStateManager.pushMatrix();

        if (FeatureWolf.lieDown.get() && FeatureWolf.rollOver.get() && entityIn.isSitting() && entityIn.isBegging()) {
            float f = entityIn.getInterestedAngle(1.0F) + entityIn.getShakeAngle(1.0F, 0.0F);
            GlStateManager.translatef(0.0F, 1.25F, 0.0F);
            GlStateManager.rotatef(f * 180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translatef(0.0F, -1.25F, 0.0F);
            this.leg1.rotateAngleX += f * 1.5F;
            this.leg2.rotateAngleX += f * 1.5F;
            this.leg3.rotateAngleX += f * 1.5F;
            this.leg4.rotateAngleX += f * 1.5F;
            this.leg1.rotationPointY -= f * 1.75F;
            this.leg2.rotationPointY -= f * 1.75F;
            this.leg3.rotationPointY -= f * 1.75F;
            this.leg4.rotationPointY -= f * 1.75F;
            this.head.rotateAngleZ = -f * 1.5F;
        }

        if (this.isChild) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0F, 5.0F * scale, 2.0F * scale);
            this.head.renderWithRotation(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 24.0F * scale, 0.0F);
            this.body.render(scale);
            this.leg1.render(scale);
            this.leg2.render(scale);
            this.leg3.render(scale);
            this.leg4.render(scale);
            this.tail.render(scale);
            this.mane.render(scale);
            GlStateManager.popMatrix();
        } else {
            this.head.renderWithRotation(scale);
            this.body.render(scale);
            this.leg1.render(scale);
            this.leg2.render(scale);
            this.leg3.render(scale);
            this.leg4.render(scale);
            this.tail.render(scale);
            this.mane.render(scale);
        }
        GlStateManager.popMatrix();
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);

        if (entitylivingbaseIn.isSitting()) {
            if (FeatureWolf.lieDown.get()) {
                this.mane.setRotationPoint(-1.0F, 17.5F, -3.0F);
                this.mane.rotateAngleX = ((float) Math.PI / 2F);
                this.mane.rotateAngleY = 0.0F;
                this.body.setRotationPoint(0.0F, 17.5F, 0.0F);
                this.body.rotateAngleX = ((float) Math.PI / 7F * 3F);
                this.tail.setRotationPoint(-1.0F, 19.0F, 6.0F);
                this.leg1.setRotationPoint(-2.5F, 22.0F, 5.25F);
                this.leg1.rotateAngleX = ((float) Math.PI * 3F / 2F);
                this.leg1.rotateAngleY = 0.4F;
                this.leg2.setRotationPoint(0.5F, 22.0F, 5.25F);
                this.leg2.rotateAngleX = ((float) Math.PI * 3F / 2F);
                this.leg2.rotateAngleY = -0.4F;
                this.leg3.rotateAngleX = ((float) Math.PI * 3F / 2F);
                this.leg3.setRotationPoint(-2.49F, 21.5F, -2.0F);
                this.leg3.rotateAngleY = 0.15F;
                this.leg4.rotateAngleX = ((float) Math.PI * 3F / 2F);
                this.leg4.setRotationPoint(0.51F, 21.5F, -2.0F);
                this.leg4.rotateAngleY = -0.15F;
                this.head.rotationPointY = 17.0F;
            } else {
                this.mane.setRotationPoint(-1.0F, 16.0F, -3.0F);
                this.mane.rotateAngleX = ((float) Math.PI * 2F / 5F);
                this.mane.rotateAngleY = 0.0F;
                this.tail.setRotationPoint(-1.0F, 21.0F, 6.0F);
            }
        } else {
            this.mane.setRotationPoint(-1.0F, 14.0F, -3.0F);
            this.mane.rotateAngleX = this.body.rotateAngleX;
            this.tail.setRotationPoint(-1.0F, 12.0F, 8.0F);
            this.head.rotationPointY = 13.5F;
            this.leg1.rotateAngleY = this.leg2.rotateAngleY = this.leg3.rotateAngleY = this.leg4.rotateAngleY = 0.0F;
        }

        this.mane.rotateAngleZ = entitylivingbaseIn.getShakeAngle(partialTickTime, -0.08F);
        this.tail.rotateAngleZ = entitylivingbaseIn.getShakeAngle(partialTickTime, -0.2F);

        float progress = ((float) entitylivingbaseIn.ticksExisted + partialTickTime) / 3.978873F;
        float magnitude = (0.5F + Math.max(limbSwingAmount, entitylivingbaseIn.getInterestedAngle(partialTickTime) * 1.5F)) * 0.25F;
        float amplitude = limbSwing * 0.6662F + progress * 0.6662F;

        if (!entitylivingbaseIn.isTamed()) {
            this.tail.rotateAngleY = 0.0F;
            this.tail.rotateAngleX += MathHelper.sin(amplitude) * magnitude;

            for(int i = 0; i < this.tailParts.length; ++i) {
                this.tailParts[i].rotateAngleZ = 0.0F;
                this.tailParts[i].rotateAngleX = MathHelper.sin(amplitude - (float)(i + 1) * (float) FeatureWolf.swing.get() * 0.15F) * magnitude;
            }
        } else {
            this.tail.rotateAngleY = MathHelper.sin(amplitude) * magnitude;

            for(int i = 0; i < this.tailParts.length; ++i) {
                this.tailParts[i].rotateAngleX = 0.0F;
                this.tailParts[i].rotateAngleZ = MathHelper.sin(amplitude - (float)(i + 1) * (float) FeatureWolf.swing.get() * 0.15F) * magnitude;
            }
        }
    }

}