package com.fuzs.betteranimationscollection2.renderer.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.SheepWoolModel;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KneelingSheepWoolModel<T extends SheepEntity> extends SheepWoolModel<T> {

    private float rotation;

    public KneelingSheepWoolModel() {
        this.field_78148_b = new RendererModel(this, 28, 8);
        this.field_78148_b.addBox(-4.0F, -15.0F, 0.0F, 8, 16, 6, 1.75F);
        this.field_78148_b.setRotationPoint(0.0F, 12.0F, 7.0F);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        this.rotation = entitylivingbaseIn.getHeadRotationPointY(partialTickTime);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        this.field_78148_b.rotateAngleX += this.rotation * 0.4F;
        this.field_78147_e.rotationPointY = this.field_78144_f.rotationPointY = 12.0F + this.rotation * 4.0F;
        this.field_78147_e.rotateAngleX -= this.rotation;
        this.field_78144_f.rotateAngleX -= this.rotation;
    }

}