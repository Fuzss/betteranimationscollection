package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.helper.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.MagmaCubeModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagmaCubeBurgerModel<T extends SlimeEntity> extends MagmaCubeModel<T> {

    private final RendererModel[] segments;
    private final RendererModel core;

    public MagmaCubeBurgerModel() {
        super();
        this.segments = (RendererModel[]) ReflectionHelper.getModelPart(this, ReflectionHelper.MAGMACUBEMODEL_SEGMENTS);
        this.core = (RendererModel) ReflectionHelper.getModelPart(this, ReflectionHelper.MAGMACUBEMODEL_CORE);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        if (entitylivingbaseIn != null) {
            float progress = (float)entitylivingbaseIn.deathTime;
            if (progress > 10.0F) {
                progress = 10.0F;
            }

            this.core.isHidden = progress > 0.0F;

            float f = MathHelper.lerp(partialTickTime, entitylivingbaseIn.prevSquishFactor, entitylivingbaseIn.squishFactor);
            if (f < 0.0F) {
                f = 0.0F;
            }

            for (int i = 0; i < this.segments.length; ++i) {
                this.segments[i].rotationPointY = (float)(-(4 - i)) * f * 1.7F;
                this.segments[i].rotationPointY += (float)(10 - i) * progress * 0.325F;
                this.segments[i].rotationPointX = progress * 2.0F - (progress <= 0.0F ? 0.0F : (float)(7 - i) * 0.675F);
                this.segments[i].rotateAngleZ = progress * 0.195F;
                this.segments[i].rotateAngleY = (float)(5 * i % 7 - 3) * progress * 0.05F;
            }
        }
    }

}