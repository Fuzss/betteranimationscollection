package com.fuzs.betteranimationscollection2.renderer.model;

import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWigglyIronGolemNose extends ModelIronGolem
{
    /** The head model for the iron golem. */
    public ModelRenderer ironGolemNose;

    public ModelWigglyIronGolemNose()
    {
        this(0.0F, -7.0F);
    }

    public ModelWigglyIronGolemNose(float scale, float offset)
    {
        super(scale, offset);
        this.ironGolemHead = (new ModelRenderer(this)).setTextureSize(128, 128);
        this.ironGolemHead.setRotationPoint(0.0F, 0.0F + offset, -2.0F);
        this.ironGolemHead.setTextureOffset(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8, scale);
        // separate nose from head model
        this.ironGolemNose = (new ModelRenderer(this)).setTextureSize(128, 128);
        this.ironGolemNose.setRotationPoint(0.0F, 3.0F + offset, 0.0F);
        this.ironGolemNose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -7.5F, 2, 4, 2, scale);
        this.ironGolemHead.addChild(this.ironGolemNose);

    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        // this only works because EntityLiving#livingSoundTime is manually being synced to the client in SoundEventHandler
        if (entitylivingbaseIn instanceof EntityLiving) {

            EntityLiving entitylivingIn = (EntityLiving)entitylivingbaseIn;
            int time = entitylivingIn.livingSoundTime + entitylivingIn.getTalkInterval();
            if (time > 20) {
                time = 0;
            }

            if (time > 0 && time < 20) {
                float rotate = MathHelper.sin((float)time * (float)((3.0F * Math.PI) / 20.0F));
                this.ironGolemNose.rotateAngleZ = rotate * 0.75F * ((float)(20 - time) / 20.0F);
            } else {
                this.ironGolemNose.rotateAngleZ = 0.0F;
            }

        }

    }

}