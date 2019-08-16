package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureOcelot;
import net.minecraft.block.BlockBed;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Modified copy of ModelOcelot as model parts are inaccessible in the vanilla class
 */
@SideOnly(Side.CLIENT)
public class ModelFlowyOcelotTails extends ModelBase
{
    /** The back left leg model for the Ocelot. */
    public final ModelRenderer ocelotBackLeftLeg;
    /** The back right leg model for the Ocelot. */
    public final ModelRenderer ocelotBackRightLeg;
    /** The front left leg model for the Ocelot. */
    public final ModelRenderer ocelotFrontLeftLeg;
    /** The front right leg model for the Ocelot. */
    public final ModelRenderer ocelotFrontRightLeg;
    /** The tail model for the Ocelot. */
    public final ModelRenderer ocelotTail;
    /** The head model for the Ocelot. */
    public final ModelRenderer ocelotHead;
    /** The body model for the Ocelot. */
    public final ModelRenderer ocelotBody;
    private int state = 1;

    public final ModelRenderer[] ocelotTailParts = new ModelRenderer[15];
    private float progress;

    public ModelFlowyOcelotTails()
    {
        this.setTextureOffset("head.main", 0, 0);
        this.setTextureOffset("head.nose", 0, 24);
        this.setTextureOffset("head.ear1", 0, 10);
        this.setTextureOffset("head.ear2", 6, 10);
        this.ocelotHead = new ModelRenderer(this, "head");
        this.ocelotHead.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
        this.ocelotHead.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
        this.ocelotHead.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
        this.ocelotHead.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
        this.ocelotHead.setRotationPoint(0.0F, 15.0F, -9.0F);
        this.ocelotBody = new ModelRenderer(this, 20, 0);
        this.ocelotBody.addBox(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0.0F);
        this.ocelotBody.setRotationPoint(0.0F, 12.0F, -10.0F);
        this.ocelotTail = new ModelRenderer(this, 0, 15);
        this.ocelotTail.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
        this.ocelotTail.rotateAngleX = 0.9F;
        this.ocelotTail.setRotationPoint(0.0F, 15.0F, 8.0F);

        for(int i = 0; i < this.ocelotTailParts.length; ++i) {
            if (i < this.ocelotTailParts.length / 2) {
                this.ocelotTailParts[i] = new ModelRenderer(this, 0, 16 + i);
                this.ocelotTailParts[i].addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
                this.ocelotTailParts[i].setRotationPoint(0.0F, 1.0F, 0.0F);
                if (i == 0) {
                    this.ocelotTail.addChild(this.ocelotTailParts[i]);
                } else {
                    this.ocelotTailParts[i - 1].addChild(this.ocelotTailParts[i]);
                }
            } else {
                this.ocelotTailParts[i] = new ModelRenderer(this, 4, 8 + i);
                this.ocelotTailParts[i].addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
                this.ocelotTailParts[i].setRotationPoint(0.0F, 1.0F, 0.0F);
                this.ocelotTailParts[i - 1].addChild(this.ocelotTailParts[i]);
            }
        }

        this.ocelotBackLeftLeg = new ModelRenderer(this, 8, 13);
        this.ocelotBackLeftLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.ocelotBackLeftLeg.setRotationPoint(1.1F, 18.0F, 5.0F);
        this.ocelotBackRightLeg = new ModelRenderer(this, 8, 13);
        this.ocelotBackRightLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
        this.ocelotBackRightLeg.setRotationPoint(-1.1F, 18.0F, 5.0F);
        this.ocelotFrontLeftLeg = new ModelRenderer(this, 40, 0);
        this.ocelotFrontLeftLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.ocelotFrontLeftLeg.setRotationPoint(1.2F, 13.8F, -5.0F);
        this.ocelotFrontRightLeg = new ModelRenderer(this, 40, 0);
        this.ocelotFrontRightLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
        this.ocelotFrontRightLeg.setRotationPoint(-1.2F, 13.8F, -5.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        this.progress = ageInTicks / 4.66F;

        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            GlStateManager.translate(0.0F, 10.0F * scale, 4.0F * scale);
            this.ocelotHead.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.ocelotBody.render(scale);
            this.ocelotBackLeftLeg.render(scale);
            this.ocelotBackRightLeg.render(scale);
            this.ocelotFrontLeftLeg.render(scale);
            this.ocelotFrontRightLeg.render(scale);
            this.ocelotTail.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.ocelotHead.render(scale);
            this.ocelotBody.render(scale);
            this.ocelotTail.render(scale);
            this.ocelotBackLeftLeg.render(scale);
            this.ocelotBackRightLeg.render(scale);
            this.ocelotFrontLeftLeg.render(scale);
            this.ocelotFrontRightLeg.render(scale);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.ocelotHead.rotateAngleX = headPitch * 0.017453292F;
        this.ocelotHead.rotateAngleY = netHeadYaw * 0.017453292F;

        if (this.state != 3)
        {
            this.ocelotBody.rotateAngleX = ((float)Math.PI / 2F);

            if (this.state == 2)
            {
                this.ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
                this.ocelotBackRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 0.3F) * limbSwingAmount;
                this.ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI + 0.3F) * limbSwingAmount;
                this.ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
            }
            else
            {
                this.ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
                this.ocelotBackRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
                this.ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
                this.ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
            }
        }
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        EntityOcelot entityocelot = (EntityOcelot)entitylivingbaseIn;
        BlockPos pos = new BlockPos((int) entityocelot.posX, (int) entityocelot.posY, (int) entityocelot.posZ);
        boolean flag = FeatureOcelot.lieDown && (!FeatureOcelot.lieBedOnly || entityocelot.worldObj.getBlockState(pos).getBlock() instanceof BlockBed);

        // reset
        this.ocelotHead.rotateAngleX = 0.0F;
        this.ocelotHead.rotateAngleZ = 0.0F;
        this.ocelotFrontLeftLeg.rotateAngleX = 0.0F;
        this.ocelotFrontLeftLeg.rotateAngleZ = 0.0F;
        this.ocelotFrontRightLeg.rotateAngleX = 0.0F;
        this.ocelotFrontRightLeg.rotateAngleZ = 0.0F;
        this.ocelotFrontRightLeg.rotationPointX = -1.2F;
        this.ocelotBackLeftLeg.rotateAngleX = 0.0F;
        this.ocelotBackRightLeg.rotateAngleX = 0.0F;
        this.ocelotBackRightLeg.rotateAngleZ = 0.0F;
        this.ocelotBackRightLeg.rotationPointX = -1.1F;
        this.ocelotBackRightLeg.rotationPointY = 18.0F;
        this.ocelotBody.rotationPointY = 12.0F;
        this.ocelotBody.rotationPointZ = -10.0F;
        this.ocelotHead.rotationPointY = 15.0F;
        this.ocelotHead.rotationPointZ = -9.0F;
        this.ocelotTail.rotationPointY = 15.0F;
        this.ocelotTail.rotationPointZ = 8.0F;
        this.ocelotFrontLeftLeg.rotationPointY = 13.8F;
        this.ocelotFrontLeftLeg.rotationPointZ = -5.0F;
        this.ocelotFrontRightLeg.rotationPointY = 13.8F;
        this.ocelotFrontRightLeg.rotationPointZ = -5.0F;
        this.ocelotBackLeftLeg.rotationPointY = 18.0F;
        this.ocelotBackLeftLeg.rotationPointZ = 5.0F;
        this.ocelotBackRightLeg.rotationPointZ = 5.0F;
        this.ocelotTail.rotateAngleX = 0.9F;
        this.ocelotTail.rotateAngleY = 0.0F;
        this.ocelotTail.rotateAngleZ = 0.0F;

        if (entityocelot.isSneaking())
        {
            ++this.ocelotBody.rotationPointY;
            this.ocelotHead.rotationPointY += 2.0F;
            ++this.ocelotTail.rotationPointY;
            this.ocelotTail.rotateAngleX = ((float)Math.PI / 2F);
            this.state = 0;
        }
        else if (entityocelot.isSprinting())
        {
            this.ocelotTail.rotateAngleX = ((float)Math.PI / 2F);
            this.state = 2;
        }
        else if (entityocelot.isSitting())
        {
            if (flag) {
                this.ocelotBody.rotateAngleX = ((float)Math.PI / 2F);
                this.ocelotTail.rotateAngleX = ((float)Math.PI / 2.5F);
                this.ocelotTail.rotateAngleY = 0.0F;
                this.ocelotTail.rotateAngleZ = -((float)Math.PI / 2F);
                this.ocelotHead.rotateAngleZ = -1.2707963F;
                this.ocelotHead.rotateAngleY = 1.2707963F;
                this.ocelotFrontLeftLeg.rotateAngleX = -1.2707963F;
                this.ocelotFrontRightLeg.rotateAngleX = -0.47079635F;
                this.ocelotFrontRightLeg.rotateAngleZ = -0.2F;
                this.ocelotFrontRightLeg.rotationPointX = -0.2F;
                this.ocelotBackLeftLeg.rotateAngleX = -0.4F;
                this.ocelotBackRightLeg.rotateAngleX = 0.5F;
                this.ocelotBackRightLeg.rotateAngleZ = -0.5F;
                this.ocelotBackRightLeg.rotationPointX = -0.3F;
                this.ocelotBackRightLeg.rotationPointY = 20.0F;
            } else {
                this.ocelotBody.rotateAngleX = ((float)Math.PI / 4F);
                this.ocelotBody.rotationPointY += -4.0F;
                this.ocelotBody.rotationPointZ += 5.0F;
                this.ocelotHead.rotationPointY += -3.3F;
                ++this.ocelotHead.rotationPointZ;
                this.ocelotTail.rotationPointY += 8.0F;
                this.ocelotTail.rotationPointZ += -2.0F;
                this.ocelotTail.rotateAngleX = 1.7278761F;
                this.ocelotTail.rotateAngleY = -1.0F;
                this.ocelotFrontLeftLeg.rotateAngleX = -0.15707964F;
                this.ocelotFrontLeftLeg.rotationPointY = 15.8F;
                this.ocelotFrontLeftLeg.rotationPointZ = -7.0F;
                this.ocelotFrontRightLeg.rotateAngleX = -0.15707964F;
                this.ocelotFrontRightLeg.rotationPointY = 15.8F;
                this.ocelotFrontRightLeg.rotationPointZ = -7.0F;
                this.ocelotBackLeftLeg.rotateAngleX = -((float)Math.PI / 2F);
                this.ocelotBackLeftLeg.rotationPointY = 21.0F;
                this.ocelotBackLeftLeg.rotationPointZ = 1.0F;
                this.ocelotBackRightLeg.rotateAngleX = -((float)Math.PI / 2F);
                this.ocelotBackRightLeg.rotationPointY = 21.0F;
                this.ocelotBackRightLeg.rotationPointZ = 1.0F;
            }
            this.state = 3;
        }
        else
        {
            this.state = 1;
        }

        // rotate tail
        if (entityocelot.isSitting()) {
            for(int i = 0; i < this.ocelotTailParts.length; ++i) {
                this.ocelotTailParts[i].rotateAngleX = 0.0F;
                this.ocelotTailParts[i].rotateAngleZ = (15.0F - (float)i) / 50.0F;
            }
        } else {
            float magnitude = (0.5F + limbSwingAmount) * 0.125F;
            float amplitude = limbSwing * 0.6662F + this.progress * 0.6662F;
            this.ocelotTail.rotateAngleX += MathHelper.sin(amplitude) * magnitude;

            for(int i = 0; i < this.ocelotTailParts.length; ++i) {
                this.ocelotTailParts[i].rotateAngleZ = 0.0F;
                this.ocelotTailParts[i].rotateAngleX = 0.05F;
                this.ocelotTailParts[i].rotateAngleX += MathHelper.sin(amplitude - (float)(i + 1) * (float) FeatureOcelot.swing * 0.05F) * magnitude;
            }
        }
    }
}