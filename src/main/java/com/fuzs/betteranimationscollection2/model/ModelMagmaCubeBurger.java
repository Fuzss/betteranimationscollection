package com.fuzs.betteranimationscollection2.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMagmaCubeBurger extends ModelBase
{
    public ModelRenderer[] segments = new ModelRenderer[8];
    public ModelRenderer core;

    public ModelMagmaCubeBurger()
    {
        for (int i = 0; i < this.segments.length; ++i)
        {
            int j = 0;
            int k = i;

            if (i == 2)
            {
                j = 24;
                k = 10;
            }
            else if (i == 3)
            {
                j = 24;
                k = 19;
            }

            this.segments[i] = new ModelRenderer(this, j, k);
            this.segments[i].addBox(-4.0F, (float)(16 + i), -4.0F, 8, 1, 8);
        }

        this.core = new ModelRenderer(this, 0, 16);
        this.core.addBox(-2.0F, 18.0F, -2.0F, 4, 4, 4);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        EntityMagmaCube entitymagmacube = (EntityMagmaCube)entitylivingbaseIn;

        float progress = 0.0F;
        if (entitylivingbaseIn != null) {
            progress = (float)entitylivingbaseIn.deathTime;
            if (progress > 10.0F) {
                progress = 10.0F;
            }

            this.core.isHidden = progress > 0.0F;
        }

        float f = entitymagmacube.prevSquishFactor + (entitymagmacube.squishFactor - entitymagmacube.prevSquishFactor) * partialTickTime;

        if (f < 0.0F)
        {
            f = 0.0F;
        }

        for (int i = 0; i < this.segments.length; ++i)
        {
            this.segments[i].rotationPointY = (float)(-(4 - i)) * f * 1.7F;
            this.segments[i].rotationPointY += (float)(10 - i) * progress * 0.325F;
            this.segments[i].rotationPointX = progress * 2.0F - (progress <= 0.0F ? 0.0F : (float)(7 - i) * 0.675F);
            this.segments[i].rotateAngleZ = progress * 0.195F;
            this.segments[i].rotateAngleY = (float)(5 * i % 7 - 3) * progress * 0.05F;
        }
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.core.render(scale);

        for (ModelRenderer modelrenderer : this.segments)
        {
            modelrenderer.render(scale);
        }
    }
}