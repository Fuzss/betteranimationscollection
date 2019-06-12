package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureEnderman;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFlailingEnderman extends ModelEnderman
{
    public ModelRenderer[] rightArm = new ModelRenderer[14];
    public ModelRenderer[] leftArm = new ModelRenderer[14];

    public ModelFlailingEnderman(float scale)
    {
        super(scale);
        this.bipedRightArm = new ModelRenderer(this, 56, 0);
        this.bipedRightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, scale);
        this.bipedRightArm.setRotationPoint(-3.0F, -13.0F, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 56, 0);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, scale);
        this.bipedLeftArm.setRotationPoint(5.0F, -13.0F, 0.0F);

        for(int i = 0; i < this.rightArm.length; ++i) {
            this.rightArm[i] = new ModelRenderer(this, 56, 2 + i * 2);
            this.rightArm[i].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, scale);
            this.leftArm[i] = new ModelRenderer(this, 56, 2 + i * 2);
            this.leftArm[i].mirror = true;
            this.leftArm[i].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, scale);
            if (i == 0) {
                this.rightArm[i].setRotationPoint(0.0F, 1.0F, 0.0F);
                this.leftArm[i].setRotationPoint(0.0F, 1.0F, 0.0F);
                this.bipedRightArm.addChild(this.rightArm[i]);
                this.bipedLeftArm.addChild(this.leftArm[i]);
            } else {
                this.rightArm[i].setRotationPoint(0.0F, 2.0F, 0.0F);
                this.leftArm[i].setRotationPoint(0.0F, 2.0F, 0.0F);
                this.rightArm[i - 1].addChild(this.rightArm[i]);
                this.leftArm[i - 1].addChild(this.leftArm[i]);
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

        if (this.isAttacking) {

            float f = (float) FeatureEnderman.speed * 0.025F;
            if (!this.isCarrying) {

                this.bipedRightArm.rotateAngleZ = 2.6F;
                this.bipedLeftArm.rotateAngleZ = -2.6F;
                this.bipedRightArm.rotateAngleX = 0.0F;
                this.bipedLeftArm.rotateAngleX = 0.0F;

                for (int i = 0; i < this.rightArm.length; ++i) {
                    this.rightArm[i].rotateAngleZ = MathHelper.sin(ageInTicks * f * 7 + (float) i * 0.45F) * ((float) (i + 8) / 8.0F) * f;
                    this.leftArm[i].rotateAngleZ = -this.rightArm[i].rotateAngleZ;
                }

            } else if (FeatureEnderman.whileCarrying) {

                for (int i = 0; i < this.rightArm.length; ++i) {
                    int j = i > this.rightArm.length / 2 ? this.rightArm.length - i : i;
                    this.rightArm[i].rotateAngleZ = MathHelper.sin(ageInTicks * f * 5 + (float) j * 0.45F) * ((float) (j + 8) / 8.0F) * f;
                    this.leftArm[i].rotateAngleZ = -this.rightArm[i].rotateAngleZ;
                    if (i == j) {
                        this.leftArm[i].rotateAngleZ = -this.leftArm[i].rotateAngleZ;
                    } else {
                        this.rightArm[i].rotateAngleZ = -this.rightArm[i].rotateAngleZ;
                    }
                }

            }

        } else {

            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;

            for(int i = 0; i < this.rightArm.length; ++i) {
                this.rightArm[i].rotateAngleZ = 0.0F;
                this.leftArm[i].rotateAngleZ = 0.0F;
            }

        }
    }
}