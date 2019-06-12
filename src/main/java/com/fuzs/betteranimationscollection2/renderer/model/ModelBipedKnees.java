package com.fuzs.betteranimationscollection2.renderer.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBipedKnees extends ModelBiped
{
    /** The Biped's Right Leg */
    public ModelRenderer bipedRightLowerLeg;
    /** The Biped's Left Leg */
    public ModelRenderer bipedLeftLowerLeg;

    public ModelBipedKnees(float modelSize, float offset, int textureWidthIn, int textureHeightIn)
    {
        super(modelSize, offset, textureWidthIn, textureHeightIn);

        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSize);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + offset, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSize);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + offset, 0.0F);

        this.bipedRightLowerLeg = new ModelRenderer(this, 0, 22);
        this.bipedRightLowerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSize);
        this.bipedRightLowerLeg.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.bipedLeftLowerLeg = new ModelRenderer(this, 0, 22);
        this.bipedLeftLowerLeg.mirror = true;
        this.bipedLeftLowerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSize);
        this.bipedLeftLowerLeg.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.bipedRightLeg.addChild(this.bipedRightLowerLeg);
        this.bipedLeftLeg.addChild(this.bipedLeftLowerLeg);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getTicksElytraFlying() > 4;
        float f = 1.0F;

        if (flag)
        {
            f = (float)(entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F)
        {
            f = 1.0F;
        }

        this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * 0.75F * limbSwingAmount / f;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * 0.75F * limbSwingAmount / f;
        this.bipedRightLowerLeg.rotateAngleX = 0.0F;
        this.bipedLeftLowerLeg.rotateAngleX = 0.0F;

        if (this.isRiding)
        {
            this.bipedRightLeg.rotateAngleX -= 0.6F;
            this.bipedLeftLeg.rotateAngleX -= 0.6F;
            this.bipedRightLowerLeg.rotateAngleX = 0.9F;
            this.bipedLeftLowerLeg.rotateAngleX = 0.9F;
        }
        if (this.isSneak)
        {
            this.bipedRightLeg.rotateAngleX -= 0.275F;
            this.bipedLeftLeg.rotateAngleX -= 0.275F;
            this.bipedRightLowerLeg.rotateAngleX = 0.275F;
            this.bipedLeftLowerLeg.rotateAngleX = 0.275F;
        }

        this.bipedRightLowerLeg.rotateAngleX += Math.max(0.0F, MathHelper.sin(limbSwing * 0.6662F)) * 1.5F * limbSwingAmount / f;
        this.bipedLeftLowerLeg.rotateAngleX += Math.max(0.0F, MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI)) * 1.5F * limbSwingAmount / f;
    }
}