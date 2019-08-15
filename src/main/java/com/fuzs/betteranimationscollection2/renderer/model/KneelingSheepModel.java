package com.fuzs.betteranimationscollection2.renderer.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KneelingSheepModel<T extends SheepEntity> extends SheepModel<T> {

    private final RendererModel leg5;
    private final RendererModel leg6;
    private float rotation;

    public KneelingSheepModel() {
        this.field_78148_b = new RendererModel(this, 28, 8);
        this.field_78148_b.addBox(-4.0F, -15.0F, 0.0F, 8, 16, 6, 0.0F);
        this.field_78148_b.setRotationPoint(0.0F, 12.0F, 7.0F);
        this.field_78147_e = new RendererModel(this, 0, 16);
        this.field_78147_e.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.field_78147_e.setRotationPoint(-3.0F, 12.0F, -5.0F);
        this.field_78144_f = new RendererModel(this, 0, 16);
        this.field_78144_f.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.field_78144_f.setRotationPoint(3.0F, 12.0F, -5.0F);
        this.leg5 = new RendererModel(this, 0, 22);
        this.leg5.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.leg5.setTextureOffset(0, 16);
        this.leg5.addBox(-2.0F, 5.02F, -2.0F, 4, 1, 4, -0.01F);
        this.leg5.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.field_78147_e.addChild(this.leg5);
        this.leg6 = new RendererModel(this, 0, 22);
        this.leg6.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.leg6.setTextureOffset(0, 16);
        this.leg6.addBox(-2.0F, 5.02F, -2.0F, 4, 1, 4, -0.01F);
        this.leg6.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.field_78144_f.addChild(this.leg6);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        this.rotation = entitylivingbaseIn.getHeadRotationPointY(partialTickTime);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        this.field_78148_b.rotateAngleX += this.rotation * 0.4F;
        this.field_78147_e.rotationPointY = this.field_78144_f.rotationPointY = 12.0F + this.rotation * 4.0F;
        this.field_78147_e.rotateAngleX -= this.rotation;
        this.field_78144_f.rotateAngleX -= this.rotation;
        this.leg5.rotateAngleX = this.leg6.rotateAngleX = this.rotation * 2.0F;
    }
}