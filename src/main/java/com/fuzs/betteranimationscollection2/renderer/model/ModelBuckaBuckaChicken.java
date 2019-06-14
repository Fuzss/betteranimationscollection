package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureChicken;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBuckaBuckaChicken extends ModelChicken
{
    public ModelRenderer bill2;

    public ModelBuckaBuckaChicken()
    {
        super();

        this.bill = new ModelRenderer(this, 14, 0);
        this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 1, 2, 0.0F);
        this.head.addChild(this.bill);

        this.bill2 = new ModelRenderer(this, 14, 1);
        this.bill2.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 2, 0.0F);
        this.bill2.setRotationPoint(0.0F, -3.0F, -2.0F);
        this.bill.addChild(this.bill2);

        this.chin = new ModelRenderer(this, 14, 4);
        this.chin.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.chin.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.bill2.addChild(this.chin);

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
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 5.0F * scale, 2.0F * scale);
            this.head.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.body.render(scale);
            this.rightLeg.render(scale);
            this.leftLeg.render(scale);
            this.rightWing.render(scale);
            this.leftWing.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.head.render(scale);
            this.body.render(scale);
            this.rightLeg.render(scale);
            this.leftLeg.render(scale);
            this.rightWing.render(scale);
            this.leftWing.render(scale);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        if (FeatureChicken.moveWings && ageInTicks == 0) {
            float f = limbSwingAmount * FeatureChicken.wingSpeed * 0.1F;
            this.rightWing.rotateAngleZ = MathHelper.sin(limbSwing) * f + f;
            this.leftWing.rotateAngleZ = -(MathHelper.sin(limbSwing) * f + f);
        } else {
            this.rightWing.rotateAngleZ = ageInTicks;
            this.leftWing.rotateAngleZ = -ageInTicks;
        }
        if (FeatureChicken.moveHead) {
            this.head.rotationPointZ = -4.0F + MathHelper.cos(limbSwing) * FeatureChicken.headSpeed * 0.5F * limbSwingAmount;
        }
        if (FeatureChicken.moveChin) {
            this.chin.rotateAngleX = MathHelper.sin(limbSwing) * (float) FeatureChicken.chinSpeed * 0.1F * limbSwingAmount;
            this.chin.rotateAngleX -= this.bill2.rotateAngleX;
        }
    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        if (entitylivingbaseIn != null) {

            EntityLiving entitylivingIn = (EntityLiving)entitylivingbaseIn;
            int time = entitylivingIn.livingSoundTime + entitylivingIn.getTalkInterval();
            if (time > 8) {
                time = 0;
            }

            if (time > 0 && time < 8) {
                float rotate = Math.abs(MathHelper.sin((float) time * (float) Math.PI / 5.0F));
                this.bill2.rotateAngleX = rotate * 0.75F;
            } else {
                this.bill2.rotateAngleX = 0.0F;
            }
        }

    }
}