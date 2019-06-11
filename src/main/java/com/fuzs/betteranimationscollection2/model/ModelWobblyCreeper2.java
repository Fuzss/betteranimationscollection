package com.fuzs.betteranimationscollection2.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWobblyCreeper2 extends ModelWobblyCreeper1
{
    public ModelRenderer body1;
    public ModelRenderer body2;

    /**
     * Replaces the charged model with a much simpler one as the default one has way too many elements now which would overlap due to the doubles size
     */
    public ModelWobblyCreeper2(float scale)
    {
        super(scale);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -7.0F, -4.0F, 8, 8, 8, scale);
        this.head.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.body1 = new ModelRenderer(this, 16, 22);
        this.body1.addBox(-4.0F, -6.0F, -2.0F, 8, 6, 4, scale);
        this.body1.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.body2 = new ModelRenderer(this, 16, 16);
        this.body2.addBox(-4.0F, -6.0F, -2.0F, 8, 6, 4, scale);
        this.body2.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.body1.addChild(this.body2);
        this.body2.addChild(this.head);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.body1.render(scale);
        this.leg1.render(scale);
        this.leg2.render(scale);
        this.leg3.render(scale);
        this.leg4.render(scale);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        this.body1.rotateAngleX = this.bodyparts[2].rotateAngleX * 3;
        this.body2.rotateAngleX = this.bodyparts[8].rotateAngleX * 6;
        this.body1.rotateAngleZ = this.bodyparts[2].rotateAngleZ * 3;
        this.body2.rotateAngleZ = this.bodyparts[8].rotateAngleZ * 6;
        this.head.rotateAngleX *= 3;
        this.head.rotateAngleZ *= 3;
    }
}