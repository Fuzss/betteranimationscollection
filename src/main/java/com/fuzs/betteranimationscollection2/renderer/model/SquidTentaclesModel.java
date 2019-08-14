package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureSquid;
import com.fuzs.betteranimationscollection2.helper.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SquidTentaclesModel<T extends Entity> extends SquidModel<T> {

    private final RendererModel[] tentacles = new RendererModel[8];
    private final RendererModel[][] tentacles2 = new RendererModel[8][FeatureSquid.length.get()];

    public SquidTentaclesModel() {

        for (int j = 0; j < this.tentacles.length; ++j) {
            this.tentacles[j] = new RendererModel(this, 48, 0);
            double d0 = (double)j * Math.PI * 2.0D / (double)this.tentacles.length;
            float f = (float)Math.cos(d0) * 5.0F;
            float f1 = (float)Math.sin(d0) * 5.0F;
            this.tentacles[j].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
            this.tentacles[j].rotationPointX = f;
            this.tentacles[j].rotationPointZ = f1;
            this.tentacles[j].rotationPointY = 15.0F;
            d0 = (double)j * Math.PI * -2.0D / (double)this.tentacles.length + (Math.PI / 2D);
            this.tentacles[j].rotateAngleY = (float)d0;

            for(int k = 0; k < this.tentacles2[0].length; ++k) {
                this.tentacles2[j][k] = new RendererModel(this, 48, 2 + 2 * k);
                this.tentacles2[j][k].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
                this.tentacles2[j][k].rotationPointY = 2.0F;
                if (k == 0) {
                    this.tentacles[j].addChild(this.tentacles2[j][k]);
                } else {
                    this.tentacles2[j][k - 1].addChild(this.tentacles2[j][k]);
                }
            }
        }

        ReflectionHelper.setModelPart(this, this.tentacles, ReflectionHelper.SQUIDMODEL_TENTACLES);

    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {

        if (entityIn instanceof MobEntity) {
            MobEntity entity = (MobEntity) entityIn;
            float progress = ageInTicks / 1.75F;
            float magnitude = MathHelper.sqrt(MathHelper.abs((float) entity.getMotion().x) + MathHelper.abs(
                    (float) entity.getMotion().y) + MathHelper.abs((float) entity.getMotion().z)) - 0.075F;
            magnitude *= 0.375F;
            if (magnitude < 0.0F || !entity.isInWater()) {
                magnitude = 0.0F;
            }

            for (RendererModel modelrenderer : this.tentacles) {
                modelrenderer.rotateAngleX = ageInTicks * 2.0F;
            }

            for(int i = 0; i < this.tentacles.length; ++i) {

                this.tentacles[i].rotateAngleX += (float)Math.sin((double) progress) * magnitude;

                for(int j = 0; j < this.tentacles2[0].length; ++j) {
                    this.tentacles2[i][j].rotateAngleX = -ageInTicks * 0.375F + (float)Math.sin((double)(progress + (float)(j + 1))) * magnitude;
                }

            }
        }

    }

}