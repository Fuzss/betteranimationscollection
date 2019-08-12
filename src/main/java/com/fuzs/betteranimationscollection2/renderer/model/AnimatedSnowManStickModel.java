package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureSnowman;
import com.fuzs.betteranimationscollection2.helper.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.SnowManModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnimatedSnowManStickModel<T extends Entity> extends SnowManModel<T>
{
    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        // this only works because EntityLiving#livingSoundTime is manually being synced to the client in SoundEventHandler
        if (entityIn instanceof MobEntity) {

            MobEntity entitylivingIn = (MobEntity) entityIn;
            int time = entitylivingIn.livingSoundTime + entitylivingIn.getTalkInterval();
            if (time > 8) {
                time = 0;
            }

            // makes 5% of snowman render left handed, like for skeletons in vanilla
            boolean left = Math.abs(entitylivingIn.getUniqueID().getLeastSignificantBits() % 100) < FeatureSnowman.leftChance.get();
            RendererModel hand = left ? ReflectionHelper.getModelPart(this, ReflectionHelper
                    .SNOWMANMODEL_RIGHTHAND) : ReflectionHelper.getModelPart(this, ReflectionHelper.SNOWMANMODEL_LEFTHAND);

            if (time > 0 && time < 8) {
                hand.rotateAngleX = 1.5F - (float) time * 1.5F / 8.0F;
                if (left) {
                    hand.rotateAngleY += (1.0F - (float) time / 8.0F) * 2.0F;
                } else {
                    hand.rotateAngleY -= (1.0F - (float) time / 8.0F) * 2.0F;
                }
            } else {
                hand.rotateAngleX = 0.0F;
            }

        }
    }

}