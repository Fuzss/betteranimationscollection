package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.helper.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpiderKneesModel<T extends Entity> extends SpiderModel<T>
{
    private final RendererModel spiderLeg1;
    private final RendererModel spiderLeg2;
    private final RendererModel spiderLeg3;
    private final RendererModel spiderLeg4;
    private final RendererModel spiderLeg5;
    private final RendererModel spiderLeg6;
    private final RendererModel spiderLeg7;
    private final RendererModel spiderLeg8;

    public SpiderKneesModel() {
        this.spiderLeg1 = new RendererModel(this, 18, 0);
        this.spiderLeg1.addBox(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg1.setRotationPoint(-4.0F, 15.0F, 2.0F);
        this.spiderLeg2 = new RendererModel(this, 18, 0);
        this.spiderLeg2.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg2.setRotationPoint(4.0F, 15.0F, 2.0F);
        this.spiderLeg3 = new RendererModel(this, 18, 0);
        this.spiderLeg3.addBox(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg3.setRotationPoint(-4.0F, 15.0F, 1.0F);
        this.spiderLeg4 = new RendererModel(this, 18, 0);
        this.spiderLeg4.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg4.setRotationPoint(4.0F, 15.0F, 1.0F);
        this.spiderLeg5 = new RendererModel(this, 18, 0);
        this.spiderLeg5.addBox(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg5.setRotationPoint(-4.0F, 15.0F, 0.0F);
        this.spiderLeg6 = new RendererModel(this, 18, 0);
        this.spiderLeg6.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg6.setRotationPoint(4.0F, 15.0F, 0.0F);
        this.spiderLeg7 = new RendererModel(this, 18, 0);
        this.spiderLeg7.addBox(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg7.setRotationPoint(-4.0F, 15.0F, -1.0F);
        this.spiderLeg8 = new RendererModel(this, 18, 0);
        this.spiderLeg8.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.spiderLeg8.setRotationPoint(4.0F, 15.0F, -1.0F);

        RendererModel spiderLeg9 = new RendererModel(this, 24, 0);
        spiderLeg9.addBox(-9.0F, -1.0F, -1.0F, 10, 2, 2, -0.01F);
        spiderLeg9.setRotationPoint(-7.0F, 0.5F, 0.0F);
        spiderLeg9.rotateAngleZ = -1.05F;
        RendererModel spiderLeg10 = new RendererModel(this, 24, 0);
        spiderLeg10.addBox(-1.0F, -1.0F, -1.0F, 10, 2, 2, -0.01F);
        spiderLeg10.setRotationPoint(7.0F, 0.5F, 0.0F);
        spiderLeg10.rotateAngleZ = 1.05F;

        this.spiderLeg1.addChild(spiderLeg9);
        this.spiderLeg2.addChild(spiderLeg10);
        this.spiderLeg3.addChild(spiderLeg9);
        this.spiderLeg4.addChild(spiderLeg10);
        this.spiderLeg5.addChild(spiderLeg9);
        this.spiderLeg6.addChild(spiderLeg10);
        this.spiderLeg7.addChild(spiderLeg9);
        this.spiderLeg8.addChild(spiderLeg10);

        ReflectionHelper.setModelPart(this, this.spiderLeg1, ReflectionHelper.SPIDERMODEL_SPIDERLEG1);
        ReflectionHelper.setModelPart(this, this.spiderLeg2, ReflectionHelper.SPIDERMODEL_SPIDERLEG2);
        ReflectionHelper.setModelPart(this, this.spiderLeg3, ReflectionHelper.SPIDERMODEL_SPIDERLEG3);
        ReflectionHelper.setModelPart(this, this.spiderLeg4, ReflectionHelper.SPIDERMODEL_SPIDERLEG4);
        ReflectionHelper.setModelPart(this, this.spiderLeg5, ReflectionHelper.SPIDERMODEL_SPIDERLEG5);
        ReflectionHelper.setModelPart(this, this.spiderLeg6, ReflectionHelper.SPIDERMODEL_SPIDERLEG6);
        ReflectionHelper.setModelPart(this, this.spiderLeg7, ReflectionHelper.SPIDERMODEL_SPIDERLEG7);
        ReflectionHelper.setModelPart(this, this.spiderLeg8, ReflectionHelper.SPIDERMODEL_SPIDERLEG8);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
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