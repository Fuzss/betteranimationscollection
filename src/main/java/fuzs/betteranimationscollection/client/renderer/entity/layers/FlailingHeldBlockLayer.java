package fuzs.betteranimationscollection.client.renderer.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.FlailingEndermanElement;
import fuzs.betteranimationscollection.client.renderer.entity.model.FlailingEndermanModel;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.HeldBlockLayer;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.model.data.EmptyModelData;

public class FlailingHeldBlockLayer extends HeldBlockLayer {

    public FlailingHeldBlockLayer(IEntityRenderer<EndermanEntity, EndermanModel<EndermanEntity>> p_i50949_1_) {

        super(p_i50949_1_);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EndermanEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        BlockState blockstate = entitylivingbaseIn.getCarriedBlock();
        if (blockstate != null) {

            matrixStackIn.pushPose();
            double flailAmount = 0.0;
            FlailingEndermanElement element = (FlailingEndermanElement) BetterAnimationsCollection.ARM_FLAILING_ENDERMAN;
            if (element.whileCarrying && entitylivingbaseIn.isCreepy()) {

                final int armParts = FlailingEndermanModel.ARM_LENGTH;
                final float animationSpeed = element.animationSpeed * 0.025F;
                final double armPartLength = 2.0;
                double totalAngle = 0.0;
                for (int i = 0; i < armParts; i++) {

                    int j = i > armParts / 2 ? armParts - i : i;
                    float armPartZRot = MathHelper.sin(ageInTicks * animationSpeed * 5 + (float) j * 0.45F) * ((float) (j + 8) / 8.0F) * animationSpeed;
                    totalAngle += i != j ? -armPartZRot : armPartZRot;
                    flailAmount += Math.tan(totalAngle) * armPartLength;
                }
            }

            // below just copied from super
            matrixStackIn.translate(-flailAmount * 0.0575, 0.6875D, -0.75D);
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(20.0F));
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(45.0F));
            matrixStackIn.translate(0.25D, 0.1875D, 0.25D);
            matrixStackIn.scale(-0.5F, -0.5F, 0.5F);
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));
            Minecraft.getInstance().getBlockRenderer().renderBlock(blockstate, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
            matrixStackIn.popPose();
        }
    }

}
