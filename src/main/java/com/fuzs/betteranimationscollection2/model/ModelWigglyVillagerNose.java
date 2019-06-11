package com.fuzs.betteranimationscollection2.model;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWigglyVillagerNose extends ModelVillager
{

    public ModelWigglyVillagerNose(float scale)
    {
        super(scale);
    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        // this only works because EntityLiving#livingSoundTime is manually being synced to the client in SoundEventHandler
        if (entitylivingbaseIn instanceof EntityLiving) {

            EntityLiving entitylivingIn = (EntityLiving)entitylivingbaseIn;
            int time = entitylivingIn.livingSoundTime + entitylivingIn.getTalkInterval();
            if (time > 20) {
                time = 0;
            }

            if (time > 0 && time < 20) {
                float rotate = MathHelper.sin((float)time * (float)((3.0F * Math.PI) / 20.0F));
                this.villagerNose.rotateAngleZ = rotate * 0.75F * ((float)(20 - time) / 20.0F);
            } else {
                this.villagerNose.rotateAngleZ = 0.0F;
            }

        }

    }

}