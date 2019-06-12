package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureSnowman;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelAnimatedSnowManStick extends ModelSnowMan
{
    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        // this only works because EntityLiving#livingSoundTime is manually being synced to the client in SoundEventHandler
        if (entityIn instanceof EntityLiving) {

            EntityLiving entitylivingIn = (EntityLiving) entityIn;
            int time = entitylivingIn.livingSoundTime + entitylivingIn.getTalkInterval();
            if (time > 8) {
                time = 0;
            }

            // makes 5% of snowman render left handed, like for skeletons in vanilla
            boolean left = Math.abs(entitylivingIn.getUniqueID().getLeastSignificantBits() % 100) < FeatureSnowman.leftChance;
            ModelRenderer hand = left ? this.rightHand : this.leftHand;

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