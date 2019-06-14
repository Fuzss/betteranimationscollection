package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureWolf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFluffyWolfTail extends ModelWolf
{
    /** The wolf's tail */
    public ModelRenderer wolfTail;
    /** The wolf's mane */
    public ModelRenderer wolfMane;

    public ModelRenderer[] wolfTailParts = new ModelRenderer[7];

    public ModelFluffyWolfTail()
    {
        this.wolfMane = new ModelRenderer(this, 21, 0);
        this.wolfMane.addBox(-3.0F, -3.0F, -3.0F, 8, 6, 7, 0.0F);
        this.wolfMane.setRotationPoint(-1.0F, 14.0F, 2.0F);
        this.wolfTail = new ModelRenderer(this, 9, 18);
        this.wolfTail.addBox(0.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);

        for(int i = 0; i < this.wolfTailParts.length; ++i) {
            this.wolfTailParts[i] = new ModelRenderer(this, 9, Math.min(19 + i, 25));
            float fluffy = 0.0F;

            if (FeatureWolf.fluffy) {
                if (i < 5) {
                    fluffy = 0.1F + 0.1F * (float) i;
                } else if (i == 5) {
                    fluffy = 0.4F;
                } else {
                    fluffy = 0.15F;
                }
            }

            this.wolfTailParts[i].addBox(0.0F, 0.0F, -1.0F, 2, 1, 2, fluffy);
            this.wolfTailParts[i].setRotationPoint(0.0F, 1.0F + fluffy, 0.0F);
            if (i == 0) {
                this.wolfTail.addChild(this.wolfTailParts[i]);
            } else {
                this.wolfTailParts[i - 1].addChild(this.wolfTailParts[i]);
            }
        }
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        GlStateManager.pushMatrix();
        EntityWolf wolf = (EntityWolf) entityIn;

        if (FeatureWolf.lieDown && FeatureWolf.rollOver && wolf.isSitting() && wolf.isBegging()) {
            float f = wolf.getInterestedAngle(1.0F) + wolf.getShakeAngle(1.0F, 0.0F);
            GlStateManager.translate(0.0F, 1.25F, 0.0F);
            GlStateManager.rotate(f * 180.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(0.0F, -1.25F, 0.0F);
            this.wolfLeg1.rotateAngleX += f * 1.5F;
            this.wolfLeg2.rotateAngleX += f * 1.5F;
            this.wolfLeg3.rotateAngleX += f * 1.5F;
            this.wolfLeg4.rotateAngleX += f * 1.5F;
            this.wolfLeg1.rotationPointY -= f * 1.75F;
            this.wolfLeg2.rotationPointY -= f * 1.75F;
            this.wolfLeg3.rotationPointY -= f * 1.75F;
            this.wolfLeg4.rotationPointY -= f * 1.75F;
            this.wolfHeadMain.rotateAngleZ = -f * 1.5F;
        }

        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 5.0F * scale, 2.0F * scale);
            this.wolfHeadMain.renderWithRotation(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.wolfBody.render(scale);
            this.wolfLeg1.render(scale);
            this.wolfLeg2.render(scale);
            this.wolfLeg3.render(scale);
            this.wolfLeg4.render(scale);
            this.wolfTail.render(scale);
            this.wolfMane.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.wolfHeadMain.renderWithRotation(scale);
            this.wolfBody.render(scale);
            this.wolfLeg1.render(scale);
            this.wolfLeg2.render(scale);
            this.wolfLeg3.render(scale);
            this.wolfLeg4.render(scale);
            this.wolfTail.render(scale);
            this.wolfMane.render(scale);
        }
        GlStateManager.popMatrix();
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        EntityWolf entitywolf = (EntityWolf) entitylivingbaseIn;

        if (entitywolf.isSitting())
        {
            if (FeatureWolf.lieDown) {
                this.wolfMane.setRotationPoint(-1.0F, 17.5F, -3.0F);
                this.wolfMane.rotateAngleX = ((float) Math.PI / 2F);
                this.wolfMane.rotateAngleY = 0.0F;
                this.wolfBody.setRotationPoint(0.0F, 17.5F, 0.0F);
                this.wolfBody.rotateAngleX = ((float) Math.PI / 7F * 3F);
                this.wolfTail.setRotationPoint(-1.0F, 19.0F, 6.0F);
                this.wolfLeg1.setRotationPoint(-2.5F, 22.0F, 5.25F);
                this.wolfLeg1.rotateAngleX = ((float) Math.PI * 3F / 2F);
                this.wolfLeg1.rotateAngleY = 0.4F;
                this.wolfLeg2.setRotationPoint(0.5F, 22.0F, 5.25F);
                this.wolfLeg2.rotateAngleX = ((float) Math.PI * 3F / 2F);
                this.wolfLeg2.rotateAngleY = -0.4F;
                this.wolfLeg3.rotateAngleX = ((float) Math.PI * 3F / 2F);
                this.wolfLeg3.setRotationPoint(-2.49F, 21.5F, -2.0F);
                this.wolfLeg3.rotateAngleY = 0.15F;
                this.wolfLeg4.rotateAngleX = ((float) Math.PI * 3F / 2F);
                this.wolfLeg4.setRotationPoint(0.51F, 21.5F, -2.0F);
                this.wolfLeg4.rotateAngleY = -0.15F;
                this.wolfHeadMain.rotationPointY = 17.0F;
            } else {
                this.wolfMane.setRotationPoint(-1.0F, 16.0F, -3.0F);
                this.wolfMane.rotateAngleX = ((float) Math.PI * 2F / 5F);
                this.wolfMane.rotateAngleY = 0.0F;
                this.wolfTail.setRotationPoint(-1.0F, 21.0F, 6.0F);
            }
        }
        else
        {
            this.wolfMane.setRotationPoint(-1.0F, 14.0F, -3.0F);
            this.wolfMane.rotateAngleX = this.wolfBody.rotateAngleX;
            this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
            this.wolfHeadMain.rotationPointY = 13.5F;
            this.wolfLeg1.rotateAngleY = this.wolfLeg2.rotateAngleY = this.wolfLeg3.rotateAngleY = this.wolfLeg4.rotateAngleY = 0.0F;
        }

        this.wolfMane.rotateAngleZ = entitywolf.getShakeAngle(partialTickTime, -0.08F);
        this.wolfTail.rotateAngleZ = entitywolf.getShakeAngle(partialTickTime, -0.2F);

        float progress = ((float) entitywolf.ticksExisted + partialTickTime) / 3.978873F;
        float magnitude = (0.5F + Math.max(limbSwingAmount, entitywolf.getInterestedAngle(partialTickTime) * 1.5F)) * 0.25F;
        float amplitude = limbSwing * 0.6662F + progress * 0.6662F;

        if (!entitywolf.isTamed()) {
            this.wolfTail.rotateAngleY = 0.0F;
            this.wolfTail.rotateAngleX += MathHelper.sin(amplitude) * magnitude;

            for(int i = 0; i < this.wolfTailParts.length; ++i) {
                this.wolfTailParts[i].rotateAngleZ = 0.0F;
                this.wolfTailParts[i].rotateAngleX = MathHelper.sin(amplitude - (float)(i + 1) * (float) FeatureWolf.swing * 0.15F) * magnitude;
            }
        } else {
            this.wolfTail.rotateAngleY = MathHelper.sin(amplitude) * magnitude;

            for(int i = 0; i < this.wolfTailParts.length; ++i) {
                this.wolfTailParts[i].rotateAngleX = 0.0F;
                this.wolfTailParts[i].rotateAngleZ = MathHelper.sin(amplitude - (float)(i + 1) * (float) FeatureWolf.swing * 0.15F) * magnitude;
            }
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.wolfTail.rotateAngleX = ageInTicks;
    }
}