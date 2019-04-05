package fuzs.tbac2.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWavingEnderman extends ModelBiped
{
    /** Is the enderman carrying a block? */
    public boolean isCarrying;
    /** Is the enderman attacking an entity? */
    public boolean isAttacking;

    public ModelRenderer[] rightArm;
    public ModelRenderer[] leftArm;

    public ModelWavingEnderman(float scale)
    {
        super(0.0F, -14.0F, 64, 32);
        float f = -14.0F;
        this.bipedHeadwear = new ModelRenderer(this, 0, 16);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale - 0.5F);
        this.bipedHeadwear.setRotationPoint(0.0F, -14.0F, 0.0F);
        this.bipedBody = new ModelRenderer(this, 32, 16);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scale);
        this.bipedBody.setRotationPoint(0.0F, -14.0F, 0.0F);

        this.bipedRightArm = new ModelRenderer(this, 56, 0);
        this.bipedRightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, scale);
        this.bipedRightArm.setRotationPoint(-3.0F, -13.0F, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 56, 0);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, scale);
        this.bipedLeftArm.setRotationPoint(5.0F, -13.0F, 0.0F);

        this.rightArm = new ModelRenderer[14];
        this.leftArm = new ModelRenderer[14];

        for(int i = 0; i < 14; ++i) {
            this.rightArm[i] = new ModelRenderer(this, 56, 2 + i * 2);
            this.rightArm[i].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, scale);
            this.leftArm[i] = new ModelRenderer(this, 56, 2 + i * 2);
            this.leftArm[i].mirror = true;
            this.leftArm[i].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, scale);
            if (i == 0) {
                this.rightArm[i].setRotationPoint(0.0F, 1.0F, 0.0F);
                this.leftArm[i].setRotationPoint(0.0F, 1.0F, 0.0F);
                this.bipedRightArm.addChild(this.rightArm[i]);
                this.bipedLeftArm.addChild(this.leftArm[i]);
            } else {
                this.rightArm[i].setRotationPoint(0.0F, 2.0F, 0.0F);
                this.leftArm[i].setRotationPoint(0.0F, 2.0F, 0.0F);
                this.rightArm[i - 1].addChild(this.rightArm[i]);
                this.leftArm[i - 1].addChild(this.leftArm[i]);
            }
        }
        
        this.bipedRightLeg = new ModelRenderer(this, 56, 0);
        this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, scale);
        this.bipedRightLeg.setRotationPoint(-2.0F, -2.0F, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 56, 0);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, scale);
        this.bipedLeftLeg.setRotationPoint(2.0F, -2.0F, 0.0F);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.bipedHead.showModel = true;
        float f = -14.0F;
        this.bipedBody.rotateAngleX = 0.0F;
        this.bipedBody.rotationPointY = -14.0F;
        this.bipedBody.rotationPointZ = -0.0F;
        this.bipedRightLeg.rotateAngleX -= 0.0F;
        this.bipedLeftLeg.rotateAngleX -= 0.0F;
        this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX * 0.5D);
        this.bipedLeftArm.rotateAngleX = (float)((double)this.bipedLeftArm.rotateAngleX * 0.5D);
        this.bipedRightLeg.rotateAngleX = (float)((double)this.bipedRightLeg.rotateAngleX * 0.5D);
        this.bipedLeftLeg.rotateAngleX = (float)((double)this.bipedLeftLeg.rotateAngleX * 0.5D);
        float f1 = 0.4F;

        if (this.bipedRightArm.rotateAngleX > 0.4F)
        {
            this.bipedRightArm.rotateAngleX = 0.4F;
        }

        if (this.bipedLeftArm.rotateAngleX > 0.4F)
        {
            this.bipedLeftArm.rotateAngleX = 0.4F;
        }

        if (this.bipedRightArm.rotateAngleX < -0.4F)
        {
            this.bipedRightArm.rotateAngleX = -0.4F;
        }

        if (this.bipedLeftArm.rotateAngleX < -0.4F)
        {
            this.bipedLeftArm.rotateAngleX = -0.4F;
        }

        if (this.bipedRightLeg.rotateAngleX > 0.4F)
        {
            this.bipedRightLeg.rotateAngleX = 0.4F;
        }

        if (this.bipedLeftLeg.rotateAngleX > 0.4F)
        {
            this.bipedLeftLeg.rotateAngleX = 0.4F;
        }

        if (this.bipedRightLeg.rotateAngleX < -0.4F)
        {
            this.bipedRightLeg.rotateAngleX = -0.4F;
        }

        if (this.bipedLeftLeg.rotateAngleX < -0.4F)
        {
            this.bipedLeftLeg.rotateAngleX = -0.4F;
        }

        if (this.isCarrying)
        {
            this.bipedRightArm.rotateAngleX = -0.5F;
            this.bipedLeftArm.rotateAngleX = -0.5F;
            this.bipedRightArm.rotateAngleZ = 0.05F;
            this.bipedLeftArm.rotateAngleZ = -0.05F;
        } else {
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
        }

        this.bipedRightArm.rotationPointZ = 0.0F;
        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedRightLeg.rotationPointZ = 0.0F;
        this.bipedLeftLeg.rotationPointZ = 0.0F;
        this.bipedRightLeg.rotationPointY = -5.0F;
        this.bipedLeftLeg.rotationPointY = -5.0F;
        this.bipedHead.rotationPointZ = -0.0F;
        this.bipedHead.rotationPointY = -13.0F;
        this.bipedHeadwear.rotationPointX = this.bipedHead.rotationPointX;
        this.bipedHeadwear.rotationPointY = this.bipedHead.rotationPointY;
        this.bipedHeadwear.rotationPointZ = this.bipedHead.rotationPointZ;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleZ = this.bipedHead.rotateAngleZ;

        if (this.isAttacking)
        {
            float f2 = 1.0F;
            this.bipedHead.rotationPointY -= 5.0F;
        }

        int i;
        if (this.isAttacking) {
            if (!this.isCarrying) {
                this.bipedRightArm.rotateAngleZ = 2.6F;
                this.bipedLeftArm.rotateAngleZ = -2.6F;
                this.bipedRightArm.rotateAngleX = 0.0F;
                this.bipedLeftArm.rotateAngleX = 0.0F;

                for (i = 0; i < 14; ++i) {
                    this.rightArm[i].rotateAngleZ = MathHelper.sin(ageInTicks * 0.875F + (float) i * 0.45F) * ((float) (i + 8) / 8.0F) * 0.1F;
                    this.leftArm[i].rotateAngleZ = -this.rightArm[i].rotateAngleZ;
                }
            } else {
                for (i = 0; i < 14; ++i) {
                    int j = i > 7 ? 14 - i : i;
                    this.rightArm[i].rotateAngleZ = MathHelper.sin(ageInTicks * 0.625F + (float) j * 0.45F) * ((float) (j + 8) / 8.0F) * 0.1F;
                    this.leftArm[i].rotateAngleZ = -this.rightArm[i].rotateAngleZ;
                    if (i > 7) {
                        this.rightArm[i].rotateAngleZ = -this.rightArm[i].rotateAngleZ;
                    } else {
                        this.leftArm[i].rotateAngleZ = -this.leftArm[i].rotateAngleZ;
                    }
                }
            }
        } else {
            for(i = 0; i < 14; ++i) {
                this.rightArm[i].rotateAngleZ = 0.0F;
                this.leftArm[i].rotateAngleZ = 0.0F;
            }
        }
    }
}