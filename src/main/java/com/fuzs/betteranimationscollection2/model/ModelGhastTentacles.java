package com.fuzs.betteranimationscollection2.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class ModelGhastTentacles extends ModelBase
{
    ModelRenderer body;
    ModelRenderer[] tentacles = new ModelRenderer[9];
    ModelRenderer[][] tentacles2 = new ModelRenderer[9][17];

    public ModelGhastTentacles()
    {
        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
        this.body.rotationPointY += 8.0F;
        Random random = new Random(1660L);

        for (int j = 0; j < this.tentacles.length; ++j)
        {
            this.tentacles[j] = new ModelRenderer(this, 0, 0);
            float f = (((float)(j % 3) - (float)(j / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
            float f1 = ((float)(j / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
            int k = random.nextInt(7) + 8;
            this.tentacles[j].addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
            this.tentacles[j].rotationPointX = f;
            this.tentacles[j].rotationPointZ = f1;
            this.tentacles[j].rotationPointY = 15.0F;

            for(int i = 0; i < k - 1; ++i) {
                this.tentacles2[j][i] = new ModelRenderer(this, 0, 1 + i);
                this.tentacles2[j][i].addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
                this.tentacles2[j][i].rotationPointY = 1.0F;
                if (i == 0) {
                    this.tentacles[j].addChild(this.tentacles2[j][i]);
                } else {
                    this.tentacles2[j][i - 1].addChild(this.tentacles2[j][i]);
                }
            }
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        for (int i = 0; i < this.tentacles.length; ++i)
        {
            this.tentacles[i].rotateAngleX = 0.2F * MathHelper.sin(ageInTicks * 0.2F + (float)i) + 0.4F;

            int l = this.tentacles2[i].length;
            for(int j = 0; j < l - 1; ++j) {
                if (this.tentacles2[i][j] != null)
                this.tentacles2[i][j].rotateAngleX = 0.2F * MathHelper.sin(ageInTicks * 0.2F + (float)i - (float)j / 2.0F);
            }
        }
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.6F, 0.0F);
        this.body.render(scale);

        for (ModelRenderer modelrenderer : this.tentacles)
        {
            modelrenderer.render(scale);
        }

        GlStateManager.popMatrix();
    }
}