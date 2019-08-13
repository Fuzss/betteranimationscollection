package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureSlime;
import com.fuzs.betteranimationscollection2.helper.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JigglySlimeModel<T extends Entity> extends SlimeModel<T> {

    /** The slime's bodies, both the inside box and the outside box */
    private final RendererModel body;
    /** The slime's right eye */
    private final RendererModel rightEye;
    /** The slime's left eye */
    private final RendererModel leftEye;
    /** The slime's mouth */
    private final RendererModel mouth;

    public JigglySlimeModel(int size) {
        super(size);
        this.body = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.SLIMEMODEL_BODY);
        this.rightEye = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.SLIMEMODEL_RIGHTEYE);
        this.leftEye = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.SLIMEMODEL_LEFTEYE);
        this.mouth = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.SLIMEMODEL_MOUTH);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {

        if (this.rightEye != null && entityIn instanceof SlimeEntity) {

            float progress = limbSwing + ageInTicks * (float) FeatureSlime.squishiness.get() / 15.0F;
            SlimeEntity slimeEntity = (SlimeEntity) entityIn;
            float slimy = slimeEntity.squishAmount;

            float magnitude = (float) FeatureSlime.squishiness.get() / 20.0F;
            if (slimy < 0.0F) {
                magnitude += -slimy * 0.5F;
            }

            this.rightEye.rotationPointX = MathHelper.sin(progress * 0.5F + 0.5F) * magnitude - 0.125F;
            this.rightEye.rotationPointY = MathHelper.sin(progress * 0.45F + 1.5F) * magnitude;
            this.rightEye.rotationPointZ = MathHelper.sin(progress * 0.475F + 2.5F) * magnitude * 0.25F;
            this.leftEye.rotationPointX = MathHelper.sin(progress * 0.525F + 1.0F) * magnitude + 0.125F;
            this.leftEye.rotationPointY = MathHelper.sin(progress * 0.475F + 3.0F) * magnitude;
            this.leftEye.rotationPointZ = MathHelper.sin(progress * 0.425F + 2.0F) * magnitude * 0.25F;
            this.mouth.rotationPointX = MathHelper.sin(progress * 0.55F + 3.75F) * magnitude;
            this.mouth.rotationPointY = MathHelper.sin(progress * 0.625F + 1.75F) * magnitude;
            this.mouth.rotationPointZ = MathHelper.sin(progress * 0.6F + 2.75F) * magnitude * 0.25F;
            this.body.rotationPointX = MathHelper.sin(progress * 0.3F) * magnitude * 0.5F;
            this.body.rotationPointY = MathHelper.sin(progress * 0.33F) * magnitude * 0.5F;
            this.body.rotationPointZ = MathHelper.sin(progress * 0.375F) * magnitude * 0.25F;

        }

    }

}