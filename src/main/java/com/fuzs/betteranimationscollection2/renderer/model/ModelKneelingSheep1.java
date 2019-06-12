package com.fuzs.betteranimationscollection2.renderer.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSheep1;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelKneelingSheep1 extends ModelSheep1
{
    private float rotation;

    public ModelKneelingSheep1()
    {
        super();
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-4.0F, -15.0F, 0.0F, 8, 16, 6, 1.75F);
        this.body.setRotationPoint(0.0F, 12.0F, 7.0F);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        this.rotation = ((EntitySheep) entitylivingbaseIn).getHeadRotationPointY(partialTickTime);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.body.rotateAngleX += this.rotation * 0.4F;
        this.leg3.rotationPointY = this.leg4.rotationPointY = 12.0F + this.rotation * 4.0F;
        this.leg3.rotateAngleX -= this.rotation;
        this.leg4.rotateAngleX -= this.rotation;
    }
}