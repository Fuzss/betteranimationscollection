package fuzs.bac.model;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCowUdders extends ModelQuadruped
{
    private final ModelRenderer cowNipples;
    private final ModelRenderer cowNipples2;

    public ModelCowUdders()
    {
        super(12, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
        this.head.setRotationPoint(0.0F, 4.0F, -8.0F);
        this.head.setTextureOffset(22, 0).addBox(-5.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.head.setTextureOffset(22, 0).addBox(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.body = new ModelRenderer(this, 18, 4);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);

        this.cowNipples = new ModelRenderer(this, 52, 0);
        this.cowNipples.addBox(-2.0F, -3.0F, -2.0F, 4, 6, 1);
        this.cowNipples.addBox(-1.5F, -2.5F, -2.75F, 1, 1, 1, -0.125F);
        this.cowNipples.addBox(0.5F, -2.5F, -2.75F, 1, 1, 1, -0.125F);
        this.cowNipples.addBox(0.5F, 1.5F, -2.75F, 1, 1, 1, -0.125F);
        this.cowNipples.addBox(-1.5F, 1.5F, -2.75F, 1, 1, 1, -0.125F);
        this.cowNipples.setRotationPoint(0.0F, 4.5F, -6.0F);
        this.cowNipples2 = new ModelRenderer(this, 52, 0);
        this.cowNipples2.mirror = true;
        this.cowNipples2.addBox(-2.0F, -3.0F, -0.0F, 4, 6, 1);
        this.cowNipples.rotateAngleY = 3.141593F;
        this.cowNipples.addChild(this.cowNipples2);
        this.body.addChild(this.cowNipples);

        --this.leg1.rotationPointX;
        ++this.leg2.rotationPointX;
        this.leg1.rotationPointZ += 0.0F;
        this.leg2.rotationPointZ += 0.0F;
        --this.leg3.rotationPointX;
        ++this.leg4.rotationPointX;
        --this.leg3.rotationPointZ;
        --this.leg4.rotationPointZ;
        this.childZOffset += 2.0F;
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (this.isChild)
        {
            this.cowNipples.isHidden = true;
            this.cowNipples2.isHidden = true;
        } else {
            this.cowNipples.isHidden = false;
            this.cowNipples2.isHidden = false;
        }
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        this.cowNipples.rotateAngleX = MathHelper.sin(limbSwing * 0.5F) * limbSwingAmount * 0.25F;
        this.cowNipples.rotateAngleY = MathHelper.cos(limbSwing) * limbSwingAmount * 0.625F;
    }
}