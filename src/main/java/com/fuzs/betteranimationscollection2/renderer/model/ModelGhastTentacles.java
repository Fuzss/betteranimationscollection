package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureGhast;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Modified copy of ModelGhast as model parts are inaccessible in the vanilla class
 */
@SideOnly(Side.CLIENT)
public class ModelGhastTentacles extends ModelGhast
{
    public ModelRenderer body;
    public ModelRenderer[] tentacles = new ModelRenderer[9];
    public ModelRenderer[][] tentacles2 = new ModelRenderer[9][];

    public ModelGhastTentacles()
    {
        Random random = new Random(1660L);

        this.body = new ModelRenderer(this, 0, 0);
        this.body.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
        this.body.rotationPointY += 8.0F;

        for (int i = 0; i < this.tentacles.length; ++i)
        {
            this.tentacles[i] = new ModelRenderer(this, 0, 0);
            float f = (((float)(i % 3) - (float)(i / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
            float f1 = ((float)(i / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
            int k = random.nextInt(FeatureGhast.length / 2) + FeatureGhast.length / 2 + 1;
            this.tentacles[i].addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
            this.tentacles[i].rotationPointX = f;
            this.tentacles[i].rotationPointZ = f1;
            this.tentacles[i].rotationPointY = 15.0F;
            this.tentacles2[i] = new ModelRenderer[k - 1];

            for(int j = 0; j < this.tentacles2[i].length; ++j) {

                this.tentacles2[i][j] = new ModelRenderer(this, 0, 1 + j);
                this.tentacles2[i][j].addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
                this.tentacles2[i][j].rotationPointY = 1.0F;

                if (j == 0) {
                    this.tentacles[i].addChild(this.tentacles2[i][j]);
                } else {
                    this.tentacles2[i][j - 1].addChild(this.tentacles2[i][j]);
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
        float speed = 1.0F / (float) FeatureGhast.speed;

        for (int i = 0; i < this.tentacles.length; ++i)
        {
            this.tentacles[i].rotateAngleX = speed * MathHelper.sin(ageInTicks * speed + (float) i) + 0.4F;

            for(int j = 0; j < this.tentacles2[i].length; ++j) {
                this.tentacles2[i][j].rotateAngleX = speed * MathHelper.sin(ageInTicks * speed + (float) i - (float) j / 2.0F);
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