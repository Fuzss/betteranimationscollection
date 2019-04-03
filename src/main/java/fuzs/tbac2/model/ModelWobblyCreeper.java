package fuzs.tbac2.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWobblyCreeper extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer creeperArmor;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;

    public ModelRenderer[] bodyparts;

    public ModelWobblyCreeper()
    {
        this(0.0F);
    }

    public ModelWobblyCreeper(float p_i46366_1_)
    {
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i46366_1_);
        this.head.setRotationPoint(0.0F, 1.0F, 0.0F);

        this.creeperArmor = new ModelRenderer(this, 32, 0);
        this.creeperArmor.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i46366_1_ + 0.5F);
        this.creeperArmor.setRotationPoint(0.0F, 6.0F, 0.0F);

        this.body = new ModelRenderer(this, 16, 27);
        this.body.addBox(-4.0F, 11.0F, -2.0F, 8, 1, 4, p_i46366_1_);
        this.body.setRotationPoint(0.0F, 6.0F, 0.0F);

        this.bodyparts = new ModelRenderer[11];
        for(int i = 0; i < 11; ++i) {
            this.bodyparts[i] = new ModelRenderer(this, 16, 26 - i);
            this.bodyparts[i].addBox(-4.0F, 1.0F, -2.0F, 8, 1, 4, p_i46366_1_);
            if (i == 0) {
                this.bodyparts[i].setRotationPoint(0.0F, 9.0F, 0.0F);
                this.body.addChild(this.bodyparts[i]);
            } else {
                this.bodyparts[i].setRotationPoint(0.0F, -1.0F, 0.0F);
                this.bodyparts[i - 1].addChild(this.bodyparts[i]);
            }
        }
        this.bodyparts[10].addChild(this.head);

        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
        this.leg1.setRotationPoint(-2.0F, 18.0F, 4.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
        this.leg2.setRotationPoint(2.0F, 18.0F, 4.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
        this.leg3.setRotationPoint(-2.0F, 18.0F, -4.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
        this.leg4.setRotationPoint(2.0F, 18.0F, -4.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.body.render(scale);
        this.leg1.render(scale);
        this.leg2.render(scale);
        this.leg3.render(scale);
        this.leg4.render(scale);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        float magnitude = 0.0F;
        limbSwingAmount *= 3.5F;
        if (limbSwingAmount < 0.0F) {
            magnitude = 0.0F;
        } else if (limbSwingAmount < 0.6F) {
            magnitude = 0.125F * limbSwingAmount;
        } else {
            magnitude = 0.075F;
        }

        for(int i = 0; i < 11; ++i) {
            this.bodyparts[i].rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * magnitude;
        }
    }
}