package fuzs.betteranimationscollection.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class BipedKneesModel<T extends LivingEntity> extends BipedModel<T> {

    private final ModelRenderer rightLowerLeg;
    private final ModelRenderer leftLowerLeg;

    public BipedKneesModel(float modelSizeIn) {

        super(modelSizeIn);

        this.rightLeg = makeHalfLeg(this, modelSizeIn, -1.9F, 0.0F, 0.0F, 0, 16, false);
        this.leftLeg = makeHalfLeg(this, modelSizeIn, 1.9F, 0.0F, 0.0F, 0, 16, true);
        this.rightLowerLeg = makeHalfLeg(this, modelSizeIn, 0.0F, -6.0F, -2.0F, 0, 22, false);
        this.leftLowerLeg = makeHalfLeg(this, modelSizeIn, 0.0F, -6.0F, -2.0F, 0, 22, true);
        this.rightLeg.addChild(this.rightLowerLeg);
        this.leftLeg.addChild(this.leftLowerLeg);
    }

    public static ModelRenderer makeHalfLeg(Model model, float modelSizeIn, float xOffsetIn, float yOffsetIn, float zOffsetIn, int textureX, int textureY, boolean mirror) {

        final ModelRenderer halfLeg = new ModelRenderer(model, textureX, textureY);
        halfLeg.addBox(-2.0F, 0.0F, -2.0F - zOffsetIn, 4, 6, 4, modelSizeIn);
        halfLeg.setPos(xOffsetIn, 12.0F + yOffsetIn, zOffsetIn);
        halfLeg.mirror = mirror;
        return halfLeg;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        setupKneeAnim(entityIn, limbSwing, limbSwingAmount, this, this.rightLowerLeg, this.leftLowerLeg);
    }

    public static <T extends LivingEntity> void setupKneeAnim(T entityIn, float limbSwing, float limbSwingAmount, BipedModel<T> model, ModelRenderer rightLowerLeg, ModelRenderer leftLowerLeg) {

        float flightAmplifier = 1.0F;
        if (entityIn.getFallFlyingTicks() > 4) {

            flightAmplifier = (float) entityIn.getDeltaMovement().lengthSqr();
            flightAmplifier = flightAmplifier / 0.2F;
            flightAmplifier = Math.max(1.0F, flightAmplifier * flightAmplifier * flightAmplifier);
        }

        if (model.riding) {

            rightLowerLeg.xRot = 0.6F;
            leftLowerLeg.xRot = 0.6F;
        } else {

            rightLowerLeg.xRot = Math.max(0.0F, MathHelper.sin(limbSwing * 0.6662F)) * 1.5F * limbSwingAmount / flightAmplifier;
            leftLowerLeg.xRot = Math.max(0.0F, MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI)) * 1.5F * limbSwingAmount / flightAmplifier;
        }
    }

}
