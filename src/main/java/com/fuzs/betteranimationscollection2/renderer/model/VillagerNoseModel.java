package com.fuzs.betteranimationscollection2.renderer.model;

import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VillagerNoseModel<T extends Entity> extends VillagerModel<T> {

    public VillagerNoseModel(float scale) {
        super(scale);
    }

    public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        // this only works because MobEntity#livingSoundTime is manually being synced to the client in SoundEventHandler
        if (entitylivingbaseIn instanceof MobEntity) {

            MobEntity entitylivingIn = (MobEntity) entitylivingbaseIn;
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