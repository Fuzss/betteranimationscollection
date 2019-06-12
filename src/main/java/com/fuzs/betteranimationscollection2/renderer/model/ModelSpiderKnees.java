package com.fuzs.betteranimationscollection2.renderer.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSpiderKnees extends ModelSpider
{
    public ModelRenderer spiderLeg9;
    public ModelRenderer spiderLeg10;

    public ModelSpiderKnees()
    {
        super();
        this.spiderLeg1 = new ModelRenderer(this, 18, 0);
        this.spiderLeg1.addBox(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg1.setRotationPoint(-4.0F, 15.0F, 2.0F);
        this.spiderLeg2 = new ModelRenderer(this, 18, 0);
        this.spiderLeg2.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg2.setRotationPoint(4.0F, 15.0F, 2.0F);
        this.spiderLeg3 = new ModelRenderer(this, 18, 0);
        this.spiderLeg3.addBox(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg3.setRotationPoint(-4.0F, 15.0F, 1.0F);
        this.spiderLeg4 = new ModelRenderer(this, 18, 0);
        this.spiderLeg4.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg4.setRotationPoint(4.0F, 15.0F, 1.0F);
        this.spiderLeg5 = new ModelRenderer(this, 18, 0);
        this.spiderLeg5.addBox(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg5.setRotationPoint(-4.0F, 15.0F, 0.0F);
        this.spiderLeg6 = new ModelRenderer(this, 18, 0);
        this.spiderLeg6.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg6.setRotationPoint(4.0F, 15.0F, 0.0F);
        this.spiderLeg7 = new ModelRenderer(this, 18, 0);
        this.spiderLeg7.addBox(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg7.setRotationPoint(-4.0F, 15.0F, -1.0F);
        this.spiderLeg8 = new ModelRenderer(this, 18, 0);
        this.spiderLeg8.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg8.setRotationPoint(4.0F, 15.0F, -1.0F);
        this.spiderLeg9 = new ModelRenderer(this, 24, 0);
        this.spiderLeg9.addBox(-9.0F, -1.0F, -1.0F, 10, 2, 2, -0.01F);
        this.spiderLeg9.setRotationPoint(-7.0F, 0.5F, 0.0F);
        this.spiderLeg9.rotateAngleZ = -1.05F;
        this.spiderLeg10 = new ModelRenderer(this, 24, 0);
        this.spiderLeg10.addBox(-1.0F, -1.0F, -1.0F, 10, 2, 2, -0.01F);
        this.spiderLeg10.setRotationPoint(7.0F, 0.5F, 0.0F);
        this.spiderLeg10.rotateAngleZ = 1.05F;
        this.spiderLeg1.addChild(this.spiderLeg9);
        this.spiderLeg2.addChild(this.spiderLeg10);
        this.spiderLeg3.addChild(this.spiderLeg9);
        this.spiderLeg4.addChild(this.spiderLeg10);
        this.spiderLeg5.addChild(this.spiderLeg9);
        this.spiderLeg6.addChild(this.spiderLeg10);
        this.spiderLeg7.addChild(this.spiderLeg9);
        this.spiderLeg8.addChild(this.spiderLeg10);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.spiderLeg1.rotateAngleZ += 0.675F;
        this.spiderLeg2.rotateAngleZ -= 0.675F;
        this.spiderLeg3.rotateAngleZ += 0.675F;
        this.spiderLeg4.rotateAngleZ -= 0.675F;
        this.spiderLeg5.rotateAngleZ += 0.675F;
        this.spiderLeg6.rotateAngleZ -= 0.675F;
        this.spiderLeg7.rotateAngleZ += 0.675F;
        this.spiderLeg8.rotateAngleZ -= 0.675F;
    }
}