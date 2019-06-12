package com.fuzs.betteranimationscollection2.renderer.model;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelOinkyPig extends ModelQuadruped
{
    private final ModelRenderer snout;

    public ModelOinkyPig()
    {
        this(0.0F);
    }

    public ModelOinkyPig(float scale)
    {
        super(6, scale);
        this.snout = new ModelRenderer(this, 16, 16);
        this.snout.addBox(-2.0F, -3.0F, -1.0F, 4, 3, 1, scale);
        this.snout.setRotationPoint(0.0F, 3.0F, -8.0F);
        this.head.addChild(this.snout);
        this.childYOffset = 4.0F;
    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        // this only works because EntityLiving#livingSoundTime is manually being synced to the client in SoundEventHandler
        if (entitylivingbaseIn instanceof EntityLiving) {

            EntityLiving entitylivingIn = (EntityLiving) entitylivingbaseIn;
            int time = entitylivingIn.livingSoundTime + entitylivingIn.getTalkInterval();
            if (time > 8) {
                time = 0;
            }

            if (time > 0 && time < 8) {
                float rotate = MathHelper.sin((float) time * (float) (Math.PI / 8.0F));
                this.snout.rotationPointY = 3.0F - rotate;
            } else {
                this.snout.rotationPointY = 3.0F;
            }

        }

    }
}