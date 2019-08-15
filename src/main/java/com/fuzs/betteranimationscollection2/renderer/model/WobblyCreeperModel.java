package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureCreeper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("WeakerAccess")
@OnlyIn(Dist.CLIENT)
public class WobblyCreeperModel<T extends Entity> extends EntityModel<T> {

    private final RendererModel head;
    protected final RendererModel[] bodyParts;
    private final RendererModel body;
    protected final RendererModel leg1;
    protected final RendererModel leg2;
    protected final RendererModel leg3;
    protected final RendererModel leg4;

    public WobblyCreeperModel()
    {
        this(0.0F);
    }

    public WobblyCreeperModel(float scale) {
        head = new RendererModel(this, 0, 0);
        head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale);
        head.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.body = new RendererModel(this, 16, 27);
        this.body.addBox(-4.0F, 11.0F, -2.0F, 8, 1, 4, scale);
        this.body.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.bodyParts = new RendererModel[11];
        for(int i = 0; i < 11; ++i) {
            this.bodyParts[i] = new RendererModel(this, 16, 26 - i);
            this.bodyParts[i].addBox(-4.0F, 1.0F, -2.0F, 8, 1, 4, scale);
            if (i == 0) {
                this.bodyParts[i].setRotationPoint(0.0F, 9.0F, 0.0F);
                this.body.addChild(this.bodyParts[i]);
            } else {
                this.bodyParts[i].setRotationPoint(0.0F, -1.0F, 0.0F);
                this.bodyParts[i - 1].addChild(this.bodyParts[i]);
            }
        }
        this.bodyParts[10].addChild(this.head);
        this.leg1 = new RendererModel(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, 0.0F, 4, 6, 4, scale);
        this.leg1.setRotationPoint(-2.0F, 18.0F, 2.0F);
        this.leg2 = new RendererModel(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, 0.0F, 4, 6, 4, scale);
        this.leg2.setRotationPoint(2.0F, 18.0F, 2.0F);
        this.leg3 = new RendererModel(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -4.0F, 4, 6, 4, scale);
        this.leg3.setRotationPoint(-2.0F, 18.0F, -2.0F);
        this.leg4 = new RendererModel(this, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -4.0F, 4, 6, 4, scale);
        this.leg4.setRotationPoint(2.0F, 18.0F, -2.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.body.render(scale);
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
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {

        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        limbSwingAmount *= 3.5F;
        float magnitude = this.getMagnitude(limbSwingAmount);

        for(int i = 0; i < 11; ++i) {

            this.bodyParts[i].rotateAngleX = this.bodyParts[i].rotateAngleZ = 0.0F;
            int mode = FeatureCreeper.mode.get().ordinal();

            if (mode == 3) {
                this.rotate(magnitude, limbSwing, i, (int) Math.abs(entityIn.getUniqueID().getLeastSignificantBits() % 3));
            } else {
                this.rotate(magnitude, limbSwing, i, mode);
            }

        }

    }

    private void rotate(float magnitude, float limbSwing, int i, int mode) {

        if (mode == 0) {
            this.bodyParts[i].rotateAngleZ = magnitude * MathHelper.cos(limbSwing * 0.6662F);
        } else if (mode == 1) {
            this.bodyParts[i].rotateAngleX = magnitude * MathHelper.cos(limbSwing * 0.6662F);
        } else if (mode == 2) {
            this.bodyParts[i].rotateAngleX = magnitude * MathHelper.sin(limbSwing * 0.6662F);
            this.bodyParts[i].rotateAngleZ = magnitude * MathHelper.cos(limbSwing * 0.6662F);
        }

    }

    private float getMagnitude(float limbSwingAmount) {

        float magnitude;

        if (limbSwingAmount < 0.0F) {
            magnitude = 0.0F;
        } else if (limbSwingAmount < 0.6F) {
            magnitude = 0.125F * limbSwingAmount;
        } else {
            magnitude = 0.075F;
        }

        return magnitude;

    }

}