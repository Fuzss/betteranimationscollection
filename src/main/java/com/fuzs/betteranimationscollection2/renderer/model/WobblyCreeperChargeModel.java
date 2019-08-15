package com.fuzs.betteranimationscollection2.renderer.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WobblyCreeperChargeModel<T extends Entity> extends WobblyCreeperModel<T> {

    private final RendererModel head;
    private final RendererModel bodyTop;
    private final RendererModel bodyBottom;

    /**
     * Replaces the charged model with a much simpler one as the default one has way too many elements now which would overlap due to the doubles size
     */
    public WobblyCreeperChargeModel(float scale) {
        super(scale);
        this.head = new RendererModel(this, 0, 0);
        this.head.addBox(-4.0F, -7.0F, -4.0F, 8, 8, 8, scale);
        this.head.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.bodyTop = new RendererModel(this, 16, 22);
        this.bodyTop.addBox(-4.0F, -6.0F, -2.0F, 8, 6, 4, scale);
        this.bodyTop.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.bodyBottom = new RendererModel(this, 16, 16);
        this.bodyBottom.addBox(-4.0F, -6.0F, -2.0F, 8, 6, 4, scale);
        this.bodyBottom.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.bodyTop.addChild(this.bodyBottom);
        this.bodyBottom.addChild(this.head);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.bodyTop.render(scale);
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
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.bodyTop.rotateAngleX = this.bodyParts[2].rotateAngleX * 3;
        this.bodyBottom.rotateAngleX = this.bodyParts[8].rotateAngleX * 6;
        this.bodyTop.rotateAngleZ = this.bodyParts[2].rotateAngleZ * 3;
        this.bodyBottom.rotateAngleZ = this.bodyParts[8].rotateAngleZ * 6;
        this.head.rotateAngleX *= 3;
        this.head.rotateAngleZ *= 3;
    }

}