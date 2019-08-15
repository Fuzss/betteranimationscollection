package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureEnderman;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlailingEndermanModel<T extends LivingEntity> extends EndermanModel<T> {

    private final int armLength = 12;
    private final RendererModel[] rightArmParts = new RendererModel[this.armLength];
    private final RendererModel[] leftArmParts = new RendererModel[this.armLength];

    public FlailingEndermanModel(float scale)
    {
        super(scale);
        this.bipedRightArm = new RendererModel(this, 56, 0);
        this.bipedRightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, scale);
        this.bipedRightArm.setRotationPoint(-3.0F, -13.0F, 0.0F);
        this.bipedLeftArm = new RendererModel(this, 56, 0);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, scale);
        this.bipedLeftArm.setRotationPoint(5.0F, -13.0F, 0.0F);

        for(int i = 0; i < this.armLength; ++i) {
            this.rightArmParts[i] = new RendererModel(this, 56, 2 + i * 2);
            this.rightArmParts[i].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, scale);
            this.leftArmParts[i] = new RendererModel(this, 56, 2 + i * 2);
            this.leftArmParts[i].mirror = true;
            this.leftArmParts[i].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, scale);
            if (i == 0) {
                this.rightArmParts[i].setRotationPoint(0.0F, 1.0F, 0.0F);
                this.leftArmParts[i].setRotationPoint(0.0F, 1.0F, 0.0F);
                this.bipedRightArm.addChild(this.rightArmParts[i]);
                this.bipedLeftArm.addChild(this.leftArmParts[i]);
            } else {
                this.rightArmParts[i].setRotationPoint(0.0F, 2.0F, 0.0F);
                this.leftArmParts[i].setRotationPoint(0.0F, 2.0F, 0.0F);
                this.rightArmParts[i - 1].addChild(this.rightArmParts[i]);
                this.leftArmParts[i - 1].addChild(this.leftArmParts[i]);
            }
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        if (this.isAttacking && (!this.isCarrying || FeatureEnderman.whileCarrying.get())) {

            float f = (float) FeatureEnderman.speed.get() * 0.025F;
            if (!this.isCarrying) {

                this.bipedRightArm.rotateAngleZ = 2.6F;
                this.bipedLeftArm.rotateAngleZ = -2.6F;
                this.bipedRightArm.rotateAngleX = 0.0F;
                this.bipedLeftArm.rotateAngleX = 0.0F;

                for (int i = 0; i < this.armLength; ++i) {
                    this.rightArmParts[i].rotateAngleZ = MathHelper.sin(ageInTicks * f * 7 + (float) i * 0.45F) * ((float) (i + 8) / 8.0F) * f;
                    this.leftArmParts[i].rotateAngleZ = -this.rightArmParts[i].rotateAngleZ;
                }

            } else {

                for (int i = 0; i < this.armLength; ++i) {
                    int j = i > this.armLength / 2 ? this.armLength - i : i;
                    this.rightArmParts[i].rotateAngleZ = MathHelper.sin(ageInTicks * f * 5 + (float) j * 0.45F) * ((float) (j + 8) / 8.0F) * f;
                    this.leftArmParts[i].rotateAngleZ = -this.rightArmParts[i].rotateAngleZ;
                    if (i == j) {
                        this.leftArmParts[i].rotateAngleZ = -this.leftArmParts[i].rotateAngleZ;
                    } else {
                        this.rightArmParts[i].rotateAngleZ = -this.rightArmParts[i].rotateAngleZ;
                    }
                }

            }

        } else {

            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;

            for(int i = 0; i < this.armLength; ++i) {
                this.rightArmParts[i].rotateAngleZ = 0.0F;
                this.leftArmParts[i].rotateAngleZ = 0.0F;
            }

        }
    }
}