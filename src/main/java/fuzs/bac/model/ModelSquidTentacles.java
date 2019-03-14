package fuzs.bac.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSquidTentacles extends ModelBase
{
    /** The squid's body */
    ModelRenderer squidBody;
    /** The squid's tentacles */
    ModelRenderer[] squidTentacles = new ModelRenderer[8];

    ModelRenderer[][] squidTentacles2 = new ModelRenderer[8][8];
    private float progress;
    private float magnitude;

    public ModelSquidTentacles()
    {
        int i = -16;
        this.squidBody = new ModelRenderer(this, 0, 0);
        this.squidBody.addBox(-6.0F, -8.0F, -6.0F, 12, 16, 12);
        this.squidBody.rotationPointY += 8.0F;

        for (int j = 0; j < this.squidTentacles.length; ++j)
        {
            this.squidTentacles[j] = new ModelRenderer(this, 48, 0);
            double d0 = (double)j * Math.PI * 2.0D / (double)this.squidTentacles.length;
            float f = (float)Math.cos(d0) * 5.0F;
            float f1 = (float)Math.sin(d0) * 5.0F;
            this.squidTentacles[j].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
            this.squidTentacles[j].rotationPointX = f;
            this.squidTentacles[j].rotationPointZ = f1;
            this.squidTentacles[j].rotationPointY = 15.0F;
            d0 = (double)j * Math.PI * -2.0D / (double)this.squidTentacles.length + (Math.PI / 2D);
            this.squidTentacles[j].rotateAngleY = (float)d0;

            for(int k = 0; k < 8; ++k) {
                this.squidTentacles2[j][k] = new ModelRenderer(this, 48, 2 + 2 * k);
                this.squidTentacles2[j][k].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
                this.squidTentacles2[j][k].rotationPointY = 2.0F;
                if (k == 0) {
                    this.squidTentacles[j].addChild(this.squidTentacles2[j][k]);
                } else {
                    this.squidTentacles2[j][k - 1].addChild(this.squidTentacles2[j][k]);
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
        for (ModelRenderer modelrenderer : this.squidTentacles)
        {
            modelrenderer.rotateAngleX = ageInTicks * 2.0F;
        }

        for(int i = 0; i < 8; ++i) {
            float wiggleOffset = this.progress;
            this.squidTentacles[i].rotateAngleX += (float)Math.sin((double)wiggleOffset) * this.magnitude;

            for(int j = 0; j < 8; ++j) {
                this.squidTentacles2[i][j].rotateAngleX = -ageInTicks * 0.375F;
                this.squidTentacles2[i][j].rotateAngleX += (float)Math.sin((double)(wiggleOffset + (float)(j + 1))) * this.magnitude;
            }
        }
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entityIn instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase)entityIn;
            this.progress = ageInTicks / 1.75F;
            this.magnitude = MathHelper.sqrt(MathHelper.abs((float)entity.motionX) + MathHelper.abs((float)entity.motionY) + MathHelper.abs((float)entity.motionZ)) - 0.075F;
            this.magnitude *= 0.375F;
            if (this.magnitude < 0.0F || !entity.isInWater()) {
                this.magnitude = 0.0F;
            }
        }

        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.squidBody.render(scale);

        for (ModelRenderer modelrenderer : this.squidTentacles)
        {
            modelrenderer.render(scale);
        }
    }
}