package fuzs.betteranimationscollection.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.math.MathHelper;

public class ZombieKneesModel<T extends ZombieEntity> extends ZombieModel<T> {
    
    private final ModelRenderer rightLowerLeg;
    private final ModelRenderer leftLowerLeg;
    
    public ZombieKneesModel(float modelSizeIn, boolean shortTexture) {

        super(modelSizeIn, shortTexture);

        this.rightLeg = getRightLeg(this, modelSizeIn, 0.0F);
        this.leftLeg = getLeftLeg(this, modelSizeIn, 0.0F);
        this.rightLowerLeg = getLowerRightLeg(this, modelSizeIn, 0.0F);
        this.leftLowerLeg = getLowerLeftLeg(this, modelSizeIn, 0.0F);
        this.rightLeg.addChild(this.rightLowerLeg);
        this.leftLeg.addChild(this.leftLowerLeg);
    }

    public static ModelRenderer getRightLeg(Model model, float modelSizeIn, float yOffsetIn) {

        final ModelRenderer rightLeg = new ModelRenderer(model, 0, 16);
        rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSizeIn);
        rightLeg.setPos(-1.9F, 12.0F + yOffsetIn, 0.0F);
        return rightLeg;
    }

    public static ModelRenderer getLeftLeg(Model model, float modelSizeIn, float yOffsetIn) {

        final ModelRenderer leftLeg = new ModelRenderer(model, 0, 16);
        leftLeg.mirror = true;
        leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSizeIn);
        leftLeg.setPos(1.9F, 12.0F + yOffsetIn, 0.0F);
        return leftLeg;
    }

    public static ModelRenderer getLowerRightLeg(Model model, float modelSizeIn, float yOffsetIn) {

        final ModelRenderer rightLowerLeg = new ModelRenderer(model, 0, 22);
        rightLowerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSizeIn);
        rightLowerLeg.setPos(0.0F, 6.0F + yOffsetIn, 0.0F);
        return rightLowerLeg;
    }

    public static ModelRenderer getLowerLeftLeg(Model model, float modelSizeIn, float yOffsetIn) {

        final ModelRenderer leftLowerLeg = new ModelRenderer(model, 0, 22);
        leftLowerLeg.mirror = true;
        leftLowerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSizeIn);
        leftLowerLeg.setPos(0.0F, 6.0F + yOffsetIn, 0.0F);
        return leftLowerLeg;
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
            flightAmplifier = Math.min(1.0F, flightAmplifier * flightAmplifier * flightAmplifier);
        }

        model.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * 0.75F * limbSwingAmount / flightAmplifier;
        model.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * 0.75F * limbSwingAmount / flightAmplifier;
        rightLowerLeg.xRot = 0.0F;
        leftLowerLeg.xRot = 0.0F;
        if (model.riding) {

            model.rightLeg.xRot -= 0.6F;
            model.leftLeg.xRot -= 0.6F;
            rightLowerLeg.xRot = 0.9F;
            leftLowerLeg.xRot = 0.9F;
        }

        if (model.crouching) {

            model.rightLeg.xRot -= 0.275F;
            model.leftLeg.xRot -= 0.275F;
            rightLowerLeg.xRot = 0.275F;
            leftLowerLeg.xRot = 0.275F;
        }

        rightLowerLeg.xRot += Math.max(0.0F, MathHelper.sin(limbSwing * 0.6662F)) * 1.5F * limbSwingAmount / flightAmplifier;
        leftLowerLeg.xRot += Math.max(0.0F, MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI)) * 1.5F * limbSwingAmount / flightAmplifier;
    }
    
}
