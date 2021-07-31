package fuzs.betteranimationscollection.client.renderer.entity.model;

import com.google.common.collect.Iterables;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.PlayfulDoggyElement;
import fuzs.betteranimationscollection.mixin.client.accessor.WolfModelAccessor;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class PlayfulDoggyModel<T extends WolfEntity> extends WolfModel<T> {

    private final ModelRenderer head = Iterables.get(super.headParts(), 0);
    private final ModelRenderer realHead;
    private final ModelRenderer body = Iterables.get(super.bodyParts(), 0);
    private final ModelRenderer leg0 = Iterables.get(super.bodyParts(), 1);
    private final ModelRenderer leg1 = Iterables.get(super.bodyParts(), 2);
    private final ModelRenderer leg2 = Iterables.get(super.bodyParts(), 3);
    private final ModelRenderer leg3 = Iterables.get(super.bodyParts(), 4);
    private final ModelRenderer tail;
    private final ModelRenderer realTail;
    private final ModelRenderer[] realTailParts;
    private final ModelRenderer upperBody = Iterables.get(super.bodyParts(), 6);

    private boolean isInSittingPose;
    private float rollOverAmount;

    public PlayfulDoggyModel() {

        this.tail = new ModelRenderer(this, 9, 18);
        this.tail.setPos(-1.0F, 12.0F, 8.0F);
        this.realTail = new ModelRenderer(this, 9, 18);
        this.realTail.addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F);
        this.tail.addChild(this.realTail);

        PlayfulDoggyElement element = (PlayfulDoggyElement) BetterAnimationsCollection.PLAYFUL_DOGGY;
        this.realTailParts = new ModelRenderer[element.tailLength];
        for (int i = 0; i < this.realTailParts.length; i++) {

            this.realTailParts[i] = new ModelRenderer(this, 9, Math.min(19 + i, 25));
            float tailFluffiness = element.fluffyTail ? getTailFluffiness(i) : 0.0F;
            this.realTailParts[i].addBox(0.0F, 0.0F, -1.0F, 2, 1, 2, tailFluffiness);
            this.realTailParts[i].setPos(0.0F, 1.0F + tailFluffiness, 0.0F);
            if (i == 0) {
                
                this.realTail.addChild(this.realTailParts[i]);
            } else {
                
                this.realTailParts[i - 1].addChild(this.realTailParts[i]);
            }
        }

        WolfModelAccessor modelAccessor = (WolfModelAccessor) this;
        this.realHead = modelAccessor.getRealHead();
        modelAccessor.setTail(this.tail);
        modelAccessor.setRealTail(this.realTail);
    }

    private float getTailFluffiness(int index) {

        if (index < 5) {

            return  0.1F + 0.1F * index;
        } else if (index == 5) {

            return  0.4F;
        } else {

            return  0.15F;
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {

        PlayfulDoggyElement element = (PlayfulDoggyElement) BetterAnimationsCollection.PLAYFUL_DOGGY;
        if (!this.isInSittingPose || !element.sittingBehaviour.rollOver() || element.sittingBehaviour.begForMeat() && this.rollOverAmount < 1E-4) {

            super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            return;
        }

        // max amount from head and body roll angles
        float rollOverAmount = element.sittingBehaviour.begForMeat() ? this.rollOverAmount : 0.47123888F;
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.0F, 1.25F, 0.0F);
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180.0F * rollOverAmount));
        matrixStackIn.translate(0.0F, -1.25F, 0.0F);
        this.leg0.xRot += rollOverAmount * 1.5F;
        this.leg1.xRot += rollOverAmount * 1.5F;
        this.leg2.xRot += rollOverAmount * 1.5F;
        this.leg3.xRot += rollOverAmount * 1.5F;
        this.leg0.y -= rollOverAmount * 1.75F;
        this.leg1.y -= rollOverAmount * 1.75F;
        this.leg2.y -= rollOverAmount * 1.75F;
        this.leg3.y -= rollOverAmount * 1.75F;
        this.realHead.zRot = -rollOverAmount * 1.5F;
        super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        matrixStackIn.popPose();
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        
        super.prepareMobModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);

        PlayfulDoggyElement element = (PlayfulDoggyElement) BetterAnimationsCollection.PLAYFUL_DOGGY;
        if (entitylivingbaseIn.isInSittingPose()) {
            
            if (element.sittingBehaviour.lieDown()) {
                
                this.upperBody.setPos(-1.0F, 17.5F, -3.0F);
                this.upperBody.xRot = ((float) Math.PI / 2F);
                this.upperBody.yRot = 0.0F;
                this.body.setPos(0.0F, 17.5F, 0.0F);
                this.body.xRot = ((float) Math.PI / 7F * 3F);
                this.tail.setPos(-1.0F, 19.0F, 6.0F);
                this.leg0.setPos(-2.5F, 22.0F, 5.25F);
                this.leg0.xRot = ((float) Math.PI * 3F / 2F);
                this.leg0.yRot = 0.4F;
                this.leg1.setPos(0.5F, 22.0F, 5.25F);
                this.leg1.xRot = ((float) Math.PI * 3F / 2F);
                this.leg1.yRot = -0.4F;
                this.leg2.xRot = ((float) Math.PI * 3F / 2F);
                this.leg2.setPos(-2.49F, 21.5F, -2.0F);
                this.leg2.yRot = 0.15F;
                this.leg3.xRot = ((float) Math.PI * 3F / 2F);
                this.leg3.setPos(0.51F, 21.5F, -2.0F);
                this.leg3.yRot = -0.15F;
                this.head.y = this.young ? 15.5F : 17.0F;
            } else {
                
                this.upperBody.setPos(-1.0F, 16.0F, -3.0F);
                this.upperBody.xRot = ((float) Math.PI * 2F / 5F);
                this.upperBody.yRot = 0.0F;
                this.tail.setPos(-1.0F, 21.0F, 6.0F);
            }
        } else {
            
            this.upperBody.setPos(-1.0F, 14.0F, -3.0F);
            this.upperBody.xRot = this.body.xRot;
            this.tail.setPos(-1.0F, 12.0F, 8.0F);
            this.head.y = 13.5F;
            this.leg0.yRot = this.leg1.yRot = this.leg2.yRot = this.leg3.yRot = 0.0F;
        }

        this.upperBody.zRot = entitylivingbaseIn.getBodyRollAngle(partialTickTime, -0.08F);
        this.realTail.zRot = entitylivingbaseIn.getBodyRollAngle(partialTickTime, -0.2F);

        // what ageInTicks would normally be which we don't have here
        float progress = ((float) entitylivingbaseIn.tickCount + partialTickTime) / 3.978873F;
        float magnitude = (0.5F + Math.max(limbSwingAmount, entitylivingbaseIn.getHeadRollAngle(partialTickTime) * 1.5F)) * 0.25F;
        float amplitude = limbSwing * 0.6662F + progress * 0.6662F;
        float animationSpeed = element.animationSpeed;
        if (!entitylivingbaseIn.isTame()) {
            
            this.tail.yRot = 0.0F;
            this.tail.xRot += MathHelper.sin(amplitude) * magnitude;
            for (int i = 0; i < this.realTailParts.length; i++) {
                
                this.realTailParts[i].zRot = 0.0F;
                this.realTailParts[i].xRot = MathHelper.sin(amplitude - (float)(i + 1) * animationSpeed * 0.15F) * magnitude;
            }
        } else {
            
            this.tail.yRot = MathHelper.sin(amplitude) * magnitude;
            for (int i = 0; i < this.realTailParts.length; i++) {
                
                this.realTailParts[i].xRot = 0.0F;
                this.realTailParts[i].zRot = MathHelper.sin(amplitude - (float)(i + 1) * animationSpeed * 0.15F) * magnitude;
            }
        }
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.isInSittingPose = entityIn.isInSittingPose();
        this.rollOverAmount = entityIn.getHeadRollAngle(1.0F) + entityIn.getBodyRollAngle(1.0F, 0.0F);
    }

}
