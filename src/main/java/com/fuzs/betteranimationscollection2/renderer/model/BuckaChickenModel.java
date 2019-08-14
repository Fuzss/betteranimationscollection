package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureChicken;
import com.fuzs.betteranimationscollection2.helper.ReflectionHelper;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.ChickenModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class BuckaChickenModel<T extends Entity> extends ChickenModel<T> {

    private final RendererModel head;
    private final RendererModel body;
    private final RendererModel rightLeg;
    private final RendererModel leftLeg;
    private final RendererModel rightWing;
    private final RendererModel leftWing;
    private final RendererModel billBottom;
    private final RendererModel chin;

    public BuckaChickenModel() {

        super();

        this.head = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.CHICKENMODEL_HEAD);
        this.body = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.CHICKENMODEL_BODY);
        this.rightLeg = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.CHICKENMODEL_RIGHTLEG);
        this.leftLeg = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.CHICKENMODEL_LEFTLEG);

        RendererModel billTop = new RendererModel(this, 14, 0);
        billTop.addBox(-2.0F, -4.0F, -4.0F, 4, 1, 2, 0.0F);
        if (this.head != null) {
            this.head.addChild(billTop);
        }

        this.billBottom = new RendererModel(this, 14, 1);
        this.billBottom.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 2, 0.0F);
        this.billBottom.setRotationPoint(0.0F, -3.0F, -2.0F);
        billTop.addChild(this.billBottom);

        this.chin = new RendererModel(this, 14, 4);
        this.chin.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.chin.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.billBottom.addChild(this.chin);

        // fix rotation point to be at the body and not in the air
        this.rightWing = new RendererModel(this, 24, 13);
        this.rightWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6);
        this.rightWing.setRotationPoint(-3.0F, 13.0F, 0.0F);
        this.leftWing = new RendererModel(this, 24, 13);
        this.leftWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6);
        this.leftWing.setRotationPoint(3.0F, 13.0F, 0.0F);

    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(@Nonnull T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0F, 5.0F * scale, 2.0F * scale);
            this.head.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 24.0F * scale, 0.0F);
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
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        if (FeatureChicken.moveWings.get() && ageInTicks == 0) {
            float f = limbSwingAmount * FeatureChicken.wingSpeed.get() * 0.1F;
            this.rightWing.rotateAngleZ = MathHelper.sin(limbSwing) * f + f;
            this.leftWing.rotateAngleZ = -(MathHelper.sin(limbSwing) * f + f);
        } else {
            this.rightWing.rotateAngleZ = ageInTicks;
            this.leftWing.rotateAngleZ = -ageInTicks;
        }
        if (FeatureChicken.moveHead.get()) {
            this.head.rotationPointZ = -4.0F + MathHelper.cos(limbSwing) * FeatureChicken.headSpeed.get() * 0.5F * limbSwingAmount;
        }
        if (FeatureChicken.moveChin.get()) {
            this.chin.rotateAngleX = MathHelper.sin(limbSwing) * (float) FeatureChicken.chinSpeed.get() * 0.1F * limbSwingAmount;
            this.chin.rotateAngleX -= this.billBottom.rotateAngleX;
        }
    }

    public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        // this only works because MobEntity#livingSoundTime is manually being synced to the client in SoundEventHandler
        if (entitylivingbaseIn instanceof MobEntity) {

            MobEntity entitylivingIn = (MobEntity) entitylivingbaseIn;
            int time = entitylivingIn.livingSoundTime + entitylivingIn.getTalkInterval();
            if (time > 8) {
                time = 0;
            }

            if (time > 0 && time < 8) {
                float rotate = Math.abs(MathHelper.sin((float) time * (float) Math.PI / 5.0F));
                this.billBottom.rotateAngleX = rotate * 0.75F;
            } else {
                this.billBottom.rotateAngleX = 0.0F;
            }
        }

    }
}