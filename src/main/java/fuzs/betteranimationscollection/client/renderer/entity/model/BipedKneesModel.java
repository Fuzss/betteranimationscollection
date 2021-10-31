package fuzs.betteranimationscollection.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class BipedKneesModel<T extends LivingEntity> extends BipedModel<T> {

    private final ModelRenderer rightShin;
    private final ModelRenderer leftShin;

    public BipedKneesModel(float modelSizeIn) {

        super(modelSizeIn);

        this.rightLeg = makeShin(this, modelSizeIn, -1.9F, 0.0F, 0.0F, 0, 16, false);
        this.leftLeg = makeShin(this, modelSizeIn, 1.9F, 0.0F, 0.0F, 0, 16, true);
        this.rightShin = makeShin(this, modelSizeIn, 0.0F, -6.0F, -2.0F, 0, 22, false);
        this.leftShin = makeShin(this, modelSizeIn, 0.0F, -6.0F, -2.0F, 0, 22, true);
        this.rightLeg.addChild(this.rightShin);
        this.leftLeg.addChild(this.leftShin);
    }

    public static ModelRenderer makeShin(EntityModel<?> model, float modelSizeIn, float xOffsetIn, float yOffsetIn, float zOffsetIn, int textureX, int textureY, boolean mirror) {

        final ModelRenderer shin = new ModelRenderer(model, textureX, textureY);
        shin.addBox(-2.0F, 0.0F, -2.0F - zOffsetIn, 4, 6, 4, modelSizeIn);
        shin.setPos(xOffsetIn, 12.0F + yOffsetIn, zOffsetIn);
        shin.mirror = mirror;
        return shin;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        setupKneeAnim(entityIn, limbSwing, limbSwingAmount, this, this.rightShin, this.leftShin);
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
