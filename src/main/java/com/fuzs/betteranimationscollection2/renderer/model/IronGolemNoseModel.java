package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.helper.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.IronGolemModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IronGolemNoseModel<T extends IronGolemEntity> extends IronGolemModel<T> {

    private final RendererModel nose;

    public IronGolemNoseModel()
    {
        this(0.0F, -7.0F);
    }

    public IronGolemNoseModel(float scale, float offset) {

        super(scale, offset);
        RendererModel head = new RendererModel(this).setTextureSize(128, 128);
        head.setRotationPoint(0.0F, 0.0F + offset, -2.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8, scale);

        // separate nose from head model
        this.nose = new RendererModel(this).setTextureSize(128, 128);
        this.nose.setRotationPoint(0.0F, 3.0F + offset, 0.0F);
        this.nose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -7.5F, 2, 4, 2, scale);
        head.addChild(this.nose);

        ReflectionHelper.setModelPart(this, head, ReflectionHelper.IRONGOLEMMODEL_HEAD);

    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);

        // this only works because MobEntity#livingSoundTime is manually being synced to the client in SoundEventHandler
        int time = entitylivingbaseIn.livingSoundTime + entitylivingbaseIn.getTalkInterval();
        if (time > 20) {
            time = 0;
        }

        if (time > 0 && time < 20) {
            float rotate = MathHelper.sin((float)time * (float)((3.0F * Math.PI) / 20.0F));
            this.nose.rotateAngleZ = rotate * 0.75F * ((float)(20 - time) / 20.0F);
        } else {
            this.nose.rotateAngleZ = 0.0F;
        }

    }

}