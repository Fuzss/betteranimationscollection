package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureCow;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCowUdder extends ModelCow
{
    public ModelRenderer utter;
    public ModelRenderer[] nipples = new ModelRenderer[4];

    public ModelCowUdder()
    {
        super();

        this.body = new ModelRenderer(this, 18, 4);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);

        this.utter = new ModelRenderer(this, 52, 0);
        this.utter.addBox(-2.0F, -2.5F, -2.0F, 4, 6, 1);
        this.utter.setRotationPoint(0.0F, 4.5F, -6.0F);
        this.body.addChild(this.utter);

        for (int i = 0; i < this.nipples.length; i++) {
            this.nipples[i] = new ModelRenderer(this, 52, 0);
            this.utter.addChild(this.nipples[i]);
        }
        this.nipples[0].addBox(-2.0F, -1.5F, -3.0F, 1, 1, 1);
        this.nipples[1].addBox(1.0F, -1.5F, -3.0F, 1, 1, 1);
        this.nipples[2].addBox(1.0F, 1.5F, -3.0F, 1, 1, 1);
        this.nipples[3].addBox(-2.0F, 1.5F, -3.0F, 1, 1, 1);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        // prevent calves from having an utter
        this.utter.isHidden = this.isChild && !this.hasChildUdder();
        // hide nipples when disabled
        for (ModelRenderer renderer : this.nipples) {
            renderer.isHidden = !this.hasNipples();
        }
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        int i = this.getSwing();
        this.utter.rotateAngleX = MathHelper.sin(limbSwing * 0.5F) * limbSwingAmount * i * 0.05F;
        this.utter.rotateAngleY = MathHelper.cos(limbSwing) * limbSwingAmount * i * 0.125F;
    }

    protected boolean hasNipples() {
        return FeatureCow.nipples;
    }

    protected boolean hasChildUdder() {
        return FeatureCow.utterChild;
    }

    protected int getSwing() {
        return FeatureCow.swing;
    }

}