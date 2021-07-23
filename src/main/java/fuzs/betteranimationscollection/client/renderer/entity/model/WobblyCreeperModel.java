package fuzs.betteranimationscollection.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.WobblyCreeperElement;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * we don't use anything from super, but we still extend it in case mods assume this model is instance of {@link net.minecraft.client.renderer.entity.model.CreeperModel}
 * @param <T> entity class
 */
public class WobblyCreeperModel<T extends Entity> extends CreeperModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer[] bodyParts;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;

    private final int partHeight;

    public WobblyCreeperModel(float scale) {

        // less parts for charge layer as it looks silly with too much overlap inside the model
        this.partHeight = scale != 0.0F ? 3 : 1;

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale);
        this.head.setPos(0.0F, 1.0F, 0.0F);
        this.body = new ModelRenderer(this, 16, 27);
        this.body.addBox(-4.0F, 12.0F - this.partHeight, -2.0F, 8, this.partHeight, 4, scale);
        this.body.setPos(0.0F, 6.0F, 0.0F);
        this.bodyParts = new ModelRenderer[10 / this.partHeight + 1];
        for (int i = 0; i < this.bodyParts.length; i++) {
            
            this.bodyParts[i] = new ModelRenderer(this, 16, 27 - this.partHeight - i * this.partHeight);
            this.bodyParts[i].addBox(-4.0F, this.partHeight, -2.0F, 8, this.partHeight, 4, scale);
            if (i == 0) {
                
                this.bodyParts[i].setPos(0.0F, 9.0F, 0.0F);
                this.body.addChild(this.bodyParts[i]);
            } else {
                
                this.bodyParts[i].setPos(0.0F, -this.partHeight, 0.0F);
                this.bodyParts[i - 1].addChild(this.bodyParts[i]);
            }
        }
        
        this.bodyParts[this.bodyParts.length - 1].addChild(this.head);
        
        // basically sets rotation points to be at body
        this.leg0 = new ModelRenderer(this, 0, 16);
        this.leg0.addBox(-2.0F, 0.0F, 0.0F, 4, 6, 4, scale);
        this.leg0.setPos(-2.0F, 18.0F, 2.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, 0.0F, 4, 6, 4, scale);
        this.leg1.setPos(2.0F, 18.0F, 2.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -4.0F, 4, 6, 4, scale);
        this.leg2.setPos(-2.0F, 18.0F, -2.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -4.0F, 4, 6, 4, scale);
        this.leg3.setPos(2.0F, 18.0F, -2.0F);
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        
        return ImmutableList.of(this.body, this.leg0, this.leg1, this.leg2, this.leg3);
    }
    
    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.leg0.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        WobblyCreeperElement element = (WobblyCreeperElement) BetterAnimationsCollection.WOBBLY_CREEPER;
        WobblyCreeperElement.WobbleDirection wobbleDirection = element.wobbleDirection;
        if (wobbleDirection == WobblyCreeperElement.WobbleDirection.RANDOM) {

            wobbleDirection = WobblyCreeperElement.WobbleDirection.values()[(int) Math.abs(entityIn.getUUID().getLeastSignificantBits() % 3)];
        }

        final float magnitude = this.resolveMagnitude(limbSwingAmount * 3.5F);
        float cosSwing = magnitude * MathHelper.cos(limbSwing * 0.6662F) * this.partHeight;
        float sinSwing = magnitude * MathHelper.sin(limbSwing * 0.6662F) * this.partHeight;
        for (ModelRenderer bodyPart : this.bodyParts) {

            switch (wobbleDirection) {

                case SIDE:

                    bodyPart.xRot = 0.0F;
                    bodyPart.zRot = cosSwing;
                    break;
                case FRONT:

                    bodyPart.xRot = cosSwing;
                    bodyPart.zRot = 0.0F;
                    break;
                case CIRCLE:

                    bodyPart.xRot = sinSwing;
                    bodyPart.zRot = cosSwing;
                    break;
            }
        }
    }

    private float resolveMagnitude(float limbSwingAmount) {

        if (limbSwingAmount < 0.0F) {

            return  0.0F;
        } else if (limbSwingAmount < 0.6F) {

            return  0.125F * limbSwingAmount;
        } else {

            return  0.075F;
        }
    }

}
