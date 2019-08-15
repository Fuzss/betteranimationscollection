package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureGhast;
import com.fuzs.betteranimationscollection2.helper.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.GhastModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class GhastTentaclesModel<T extends Entity> extends GhastModel<T>
{
    private final RendererModel[] tentacles = new RendererModel[9];
    private final RendererModel[][] tentacles2 = new RendererModel[9][];

    public GhastTentaclesModel() {
        Random random = new Random(1660L);

        for (int i = 0; i < this.tentacles.length; ++i)
        {
            this.tentacles[i] = new RendererModel(this, 0, 0);
            float f = (((float)(i % 3) - (float)(i / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
            float f1 = ((float)(i / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
            int k = random.nextInt(FeatureGhast.length.get() / 2) + FeatureGhast.length.get() / 2 + 1;
            this.tentacles[i].addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
            this.tentacles[i].rotationPointX = f;
            this.tentacles[i].rotationPointZ = f1;
            this.tentacles[i].rotationPointY = 15.0F;
            this.tentacles2[i] = new RendererModel[k - 1];

            for(int j = 0; j < this.tentacles2[i].length; ++j) {

                this.tentacles2[i][j] = new RendererModel(this, 0, 1 + j);
                this.tentacles2[i][j].addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
                this.tentacles2[i][j].rotationPointY = 1.0F;

                if (j == 0) {
                    this.tentacles[i].addChild(this.tentacles2[i][j]);
                } else {
                    this.tentacles2[i][j - 1].addChild(this.tentacles2[i][j]);
                }

            }
        }

        ReflectionHelper.setModelPart(this, this.tentacles, ReflectionHelper.GHASTMODEL_TENTACLES);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        float speed = 1.0F / (float) FeatureGhast.speed.get();

        for (int i = 0; i < this.tentacles.length; ++i) {
            this.tentacles[i].rotateAngleX = speed * MathHelper.sin(ageInTicks * speed + (float) i) + 0.4F;

            for(int j = 0; j < this.tentacles2[i].length; ++j) {
                this.tentacles2[i][j].rotateAngleX = speed * MathHelper.sin(ageInTicks * speed + (float) i - (float) j / 2.0F);
            }
        }
    }
}