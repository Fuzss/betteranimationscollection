package fuzs.betteranimationscollection.mixin.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@SuppressWarnings("unused")
@Mixin(BipedModel.class)
public abstract class BipedModelMixin<T extends LivingEntity> extends AgeableModel<T> implements IHasArm, IHasHead {
    
    @Shadow
    public ModelRenderer rightLeg;
    @Shadow
    public ModelRenderer leftLeg;
    @Shadow
    public boolean crouching;
    
    @Unique
    public ModelRenderer rightLowerLeg;
    @Unique
    public ModelRenderer leftLowerLeg;

    @Inject(method = "<init>(Ljava/util/function/Function;FFII)V", at = @At("TAIL"))
    public void init(Function<ResourceLocation, RenderType> renderTypeIn, float modelSizeIn, float yOffsetIn, int textureWidthIn, int textureHeightIn, CallbackInfo callbackInfo) {

        this.rightLeg = getRightLeg(this, modelSizeIn, yOffsetIn);
        this.leftLeg = getRightLeg(this, modelSizeIn, yOffsetIn);
        this.rightLowerLeg = getRightLowerLeg(this, modelSizeIn, yOffsetIn);
        this.leftLowerLeg = getLeftLowerLeg(this, modelSizeIn, yOffsetIn);
        this.rightLeg.addChild(this.rightLowerLeg);
        this.leftLeg.addChild(this.leftLowerLeg);
    }

    private static ModelRenderer getRightLeg(Model model, float modelSizeIn, float yOffsetIn) {

        final ModelRenderer rightLeg = new ModelRenderer(model, 0, 16);
        rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSizeIn);
        rightLeg.setPos(-1.9F, 12.0F + yOffsetIn, 0.0F);
        return rightLeg;
    }

    private static ModelRenderer getLeftLeg(Model model, float modelSizeIn, float yOffsetIn) {

        final ModelRenderer leftLeg = new ModelRenderer(model, 0, 16);
        leftLeg.mirror = true;
        leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSizeIn);
        leftLeg.setPos(1.9F, 12.0F + yOffsetIn, 0.0F);
        return leftLeg;
    }

    private static ModelRenderer getRightLowerLeg(Model model, float modelSizeIn, float yOffsetIn) {

        final ModelRenderer rightLowerLeg = new ModelRenderer(model, 0, 22);
        rightLowerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSizeIn);
        rightLowerLeg.setPos(0.0F, 6.0F, 0.0F);
        return rightLowerLeg;
    }

    private static ModelRenderer getLeftLowerLeg(Model model, float modelSizeIn, float yOffsetIn) {

        final ModelRenderer leftLowerLeg = new ModelRenderer(model, 0, 22);
        leftLowerLeg.mirror = true;
        leftLowerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSizeIn);
        leftLowerLeg.setPos(0.0F, 6.0F, 0.0F);
        return leftLowerLeg;
    }

    @Inject(method = "setupAnim", at = @At("TAIL"))
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {

        setupKneeAnim(entityIn, limbSwing, limbSwingAmount, (BipedModel<? super T>) (Object) this, this.rightLowerLeg, this.leftLowerLeg);
    }

    private static <T extends LivingEntity> void setupKneeAnim(T entityIn, float limbSwing, float limbSwingAmount, BipedModel<T> model, ModelRenderer rightLowerLeg, ModelRenderer leftLowerLeg) {

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
