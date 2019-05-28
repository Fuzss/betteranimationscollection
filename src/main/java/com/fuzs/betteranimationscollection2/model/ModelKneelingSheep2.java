package com.fuzs.betteranimationscollection2.model;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelKneelingSheep2 extends ModelQuadruped
{
    private float headRotationAngleX;
    public ModelRenderer leg5;
    public ModelRenderer leg6;
    public float rotation;

    public ModelKneelingSheep2()
    {
        super(12, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
        this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-4.0F, -15.0F, 0.0F, 8, 16, 6, 0.0F);
        this.body.setRotationPoint(0.0F, 12.0F, 7.0F);

        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.leg4.setRotationPoint(3.0F, 12.0F, -5.0F);
        this.leg5 = new ModelRenderer(this, 0, 22);
        this.leg5.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.leg5.setTextureOffset(0, 16);
        this.leg5.addBox(-2.0F, 5.02F, -2.0F, 4, 1, 4, -0.01F);
        this.leg5.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.leg3.addChild(this.leg5);
        this.leg6 = new ModelRenderer(this, 0, 22);
        this.leg6.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.leg6.setTextureOffset(0, 16);
        this.leg6.addBox(-2.0F, 5.02F, -2.0F, 4, 1, 4, -0.01F);
        this.leg6.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.leg4.addChild(this.leg6);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        this.head.rotationPointY = 6.0F + ((EntitySheep)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
        this.headRotationAngleX = ((EntitySheep)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
        this.rotation = ((EntitySheep)entitylivingbaseIn).getHeadRotationPointY(partialTickTime);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.head.rotateAngleX = this.headRotationAngleX;

        this.body.rotateAngleX += this.rotation * 0.4F;
        this.leg3.rotationPointY = this.leg4.rotationPointY = 12.0F + this.rotation * 4.0F;
        this.leg3.rotateAngleX -= this.rotation;
        this.leg4.rotateAngleX -= this.rotation;
        this.leg5.rotateAngleX = this.leg6.rotateAngleX = this.rotation * 2.0F;
    }
}