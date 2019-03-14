package fuzs.bac.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelJigglySlime extends ModelBase
{
    /** The slime's bodies, both the inside box and the outside box */
    ModelRenderer slimeBodies;
    /** The slime's right eye */
    ModelRenderer slimeRightEye;
    /** The slime's left eye */
    ModelRenderer slimeLeftEye;
    /** The slime's mouth */
    ModelRenderer slimeMouth;

    private float progress;
    private float slimy;

    public ModelJigglySlime(int p_i1157_1_)
    {
        if (p_i1157_1_ > 0)
        {
            this.slimeBodies = new ModelRenderer(this, 0, p_i1157_1_);
            this.slimeBodies.addBox(-3.0F, 17.0F, -3.0F, 6, 6, 6);
            this.slimeRightEye = new ModelRenderer(this, 32, 0);
            this.slimeRightEye.addBox(-3.25F, 18.0F, -3.5F, 2, 2, 2);
            this.slimeLeftEye = new ModelRenderer(this, 32, 4);
            this.slimeLeftEye.addBox(1.25F, 18.0F, -3.5F, 2, 2, 2);
            this.slimeMouth = new ModelRenderer(this, 32, 8);
            this.slimeMouth.addBox(0.0F, 21.0F, -3.5F, 1, 1, 1);
            this.slimeBodies.addChild(this.slimeRightEye);
            this.slimeBodies.addChild(this.slimeLeftEye);
            this.slimeBodies.addChild(this.slimeMouth);
        }
        else
        {
            this.slimeBodies = new ModelRenderer(this, 0, p_i1157_1_);
            this.slimeBodies.addBox(-4.0F, 16.0F, -4.0F, 8, 8, 8);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (this.slimeRightEye != null) {
            float magnitude = 0.25F;
            if (this.slimy < 0.0F) {
                magnitude += -this.slimy * 0.5F;
            }

            this.slimeRightEye.rotationPointX = MathHelper.sin(this.progress * 0.5F + 0.5F) * magnitude - 0.125F;
            this.slimeRightEye.rotationPointY = MathHelper.sin(this.progress * 0.45F + 1.5F) * magnitude;
            this.slimeRightEye.rotationPointZ = MathHelper.sin(this.progress * 0.475F + 2.5F) * magnitude * 0.25F;
            this.slimeLeftEye.rotationPointX = MathHelper.sin(this.progress * 0.525F + 1.0F) * magnitude + 0.125F;
            this.slimeLeftEye.rotationPointY = MathHelper.sin(this.progress * 0.475F + 3.0F) * magnitude;
            this.slimeLeftEye.rotationPointZ = MathHelper.sin(this.progress * 0.425F + 2.0F) * magnitude * 0.25F;
            this.slimeMouth.rotationPointX = MathHelper.sin(this.progress * 0.55F + 3.75F) * magnitude;
            this.slimeMouth.rotationPointY = MathHelper.sin(this.progress * 0.625F + 1.75F) * magnitude;
            this.slimeMouth.rotationPointZ = MathHelper.sin(this.progress * 0.6F + 2.75F) * magnitude * 0.25F;
            this.slimeBodies.rotationPointX = MathHelper.sin(this.progress * 0.3F) * magnitude * 0.5F;
            this.slimeBodies.rotationPointY = MathHelper.sin(this.progress * 0.33F) * magnitude * 0.5F;
            this.slimeBodies.rotationPointZ = MathHelper.sin(this.progress * 0.375F) * magnitude * 0.25F;
        }
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entityIn instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase)entityIn;
            this.progress = limbSwing + ageInTicks * 0.33F;
            if (entity instanceof EntitySlime) {
                this.slimy = ((EntitySlime)entity).squishAmount;
            }
        }
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.translate(0.0F, 0.001F, 0.0F);
        this.slimeBodies.render(scale);

        if (this.slimeRightEye != null)
        {
            this.slimeRightEye.render(scale);
            this.slimeLeftEye.render(scale);
            this.slimeMouth.render(scale);
        }
    }
}