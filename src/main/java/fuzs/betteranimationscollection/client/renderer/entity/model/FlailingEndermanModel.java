package fuzs.betteranimationscollection.client.renderer.entity.model;

import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.FlailingEndermanElement;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class FlailingEndermanModel<T extends LivingEntity> extends EndermanModel<T> {

    public static final int ARM_LENGTH = 12;

    private final ModelRenderer[] rightArmParts = new ModelRenderer[ARM_LENGTH];
    private final ModelRenderer[] leftArmParts = new ModelRenderer[ARM_LENGTH];
    
    public FlailingEndermanModel() {
        
        super(0.0F);
        
        this.rightArm = new ModelRenderer(this, 56, 0);
        this.rightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.rightArm.setPos(-3.0F, -13.0F, 0.0F);
        this.leftArm = new ModelRenderer(this, 56, 0);
        this.leftArm.mirror = true;
        this.leftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.leftArm.setPos(5.0F, -13.0F, 0.0F);

        for (int i = 0; i < this.rightArmParts.length; i++) {
            
            this.rightArmParts[i] = new ModelRenderer(this, 56, 2 + i * 2);
            this.rightArmParts[i].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
            this.leftArmParts[i] = new ModelRenderer(this, 56, 2 + i * 2);
            this.leftArmParts[i].mirror = true;
            this.leftArmParts[i].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
            if (i == 0) {
                
                this.rightArmParts[i].setPos(0.0F, 1.0F, 0.0F);
                this.leftArmParts[i].setPos(0.0F, 1.0F, 0.0F);
                this.rightArm.addChild(this.rightArmParts[i]);
                this.leftArm.addChild(this.leftArmParts[i]);
            } else {
                
                this.rightArmParts[i].setPos(0.0F, 2.0F, 0.0F);
                this.leftArmParts[i].setPos(0.0F, 2.0F, 0.0F);
                this.rightArmParts[i - 1].addChild(this.rightArmParts[i]);
                this.leftArmParts[i - 1].addChild(this.leftArmParts[i]);
            }
        }
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        FlailingEndermanElement element = (FlailingEndermanElement) BetterAnimationsCollection.ARM_FLAILING_ENDERMAN;
        final int armParts = ARM_LENGTH;
        if (this.creepy && (!this.carrying || element.whileCarrying)) {

            final float animationSpeed = element.animationSpeed * 0.025F;
            if (!this.carrying) {

                this.rightArm.zRot = 2.6F;
                this.leftArm.zRot = -2.6F;
                this.rightArm.xRot = 0.0F;
                this.leftArm.xRot = 0.0F;
                for (int i = 0; i < armParts; i++) {

                    float armPartZRot = MathHelper.sin(ageInTicks * animationSpeed * 7 + (float) i * 0.45F) * ((float) (i + 8) / 8.0F) * animationSpeed;
                    this.rightArmParts[i].zRot = armPartZRot;
                    this.leftArmParts[i].zRot = -armPartZRot;
                }
            } else {

                double x1 = 0.0;
                double x2 = 2.0;
                double xSum = 0.0;
                double totalAngle = 0.0;
                double totalX = 0.0;
                for (int i = 0; i < armParts; i++) {

                    int j = i > armParts / 2 ? armParts - i : i;
                    float armPartZRot = MathHelper.sin(ageInTicks * animationSpeed * 5 + (float) j * 0.45F) * ((float) (j + 8) / 8.0F) * animationSpeed;
                    armPartZRot = i != j ? -armPartZRot : armPartZRot;
                    this.rightArmParts[i].zRot = this.leftArmParts[i].zRot = armPartZRot;

                    totalAngle += armPartZRot;
                    totalX += Math.tan(totalAngle) * 2;

                    double prevX1 = x1;
                    double prevX2 = x2;
                    x1 = Math.cos(armPartZRot) * prevX1 - Math.sin(armPartZRot) * prevX2;
                    x2 = Math.sin(armPartZRot) * prevX1 + Math.cos(armPartZRot) * prevX2;
                    xSum += x1;
                }

                System.out.println(xSum + " == " + totalX);
            }
        } else {

            this.rightArm.zRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            for (int i = 0; i < armParts; i++) {

                this.rightArmParts[i].zRot = this.leftArmParts[i].zRot = 0.0F;
            }
        }
    }
    
}
