package fuzs.betteranimationscollection.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.util.math.MathHelper;

public class FamiliarHorseModel<T extends AbstractHorseEntity> extends HorseModel<T> {

    private final ModelRenderer neck;
    private final ModelRenderer upperMouth;
    private final ModelRenderer lowerMouth;
    private final ModelRenderer backRightLeg;
    private final ModelRenderer backLeftLeg;
    private final ModelRenderer frontRightLeg;
    private final ModelRenderer frontLeftLeg;
    private final ModelRenderer backRightShin;
    private final ModelRenderer backLeftShin;
    private final ModelRenderer frontRightShin;
    private final ModelRenderer frontLeftShin;

    private final ModelRenderer[] saddleParts;
    private final ModelRenderer[] ridingParts;

    public FamiliarHorseModel(float scaleIn) {

        super(scaleIn);

        this.neck = new ModelRenderer(this, 0, 35);
        this.neck.addBox(-2.05F, -6.0F, -2.0F, 4.0F, 12.0F, 7.0F);
        this.neck.xRot = ((float) Math.PI / 6F);
        ModelRenderer head = new ModelRenderer(this, 0, 13);
        head.addBox(-3.0F, -11.0F, -2.0F, 6.0F, 5.0F, 7.0F, scaleIn);
        this.neck.addChild(head);
        ModelRenderer mane = new ModelRenderer(this, 56, 36);
        mane.addBox(-1.0F, -11.0F, 5.01F, 2.0F, 16.0F, 2.0F, scaleIn);
        this.neck.addChild(mane);
        this.upperMouth = new ModelRenderer(this, 0, 25);
        this.upperMouth.addBox(-2.0F, -11.0F, -7.0F, 4.0F, 3.0F, 5.0F, scaleIn);
        this.neck.addChild(this.upperMouth);
        this.lowerMouth = new ModelRenderer(this, 0, 28);
        this.lowerMouth.addBox(-2.0F, -8.0F, -7.0F, 4.0F, 2.0F, 5.0F, scaleIn);
        this.neck.addChild(this.lowerMouth);
        this.addEarModels(this.neck);

        this.backLeftLeg = new ModelRenderer(this, 48, 21);
        this.backLeftLeg.addBox(-1.0F, -1.01F, -1.0F, 4.0F, 5.0F, 4.0F, scaleIn);
        this.backLeftLeg.setPos(-4.0F, 14.0F, 7.0F);
        this.backLeftShin = new ModelRenderer(this, 48, 26);
        this.backLeftShin.addBox(-1.0F, 0.99F, -1.0F, 4.0F, 6.0F, 4.0F, scaleIn);
        this.backLeftShin.setPos(-4.0F, 17.0F, 7.0F);
        this.backRightLeg = new ModelRenderer(this, 48, 21);
        this.backRightLeg.mirror = true;
        this.backRightLeg.addBox(-3.0F, -1.01F, -1.0F, 4.0F, 5.0F, 4.0F, scaleIn);
        this.backRightLeg.setPos(4.0F, 14.0F, 7.0F);
        this.backRightShin = new ModelRenderer(this, 48, 26);
        this.backRightShin.mirror = true;
        this.backRightShin.addBox(-3.0F, 0.99F, -1.0F, 4.0F, 6.0F, 4.0F, scaleIn);
        this.backRightShin.setPos(4.0F, 17.0F, 7.0F);
        this.frontLeftLeg = new ModelRenderer(this, 48, 21);
        this.frontLeftLeg.addBox(-1.0F, -1.01F, -1.9F, 4.0F, 5.0F, 4.0F, scaleIn);
        this.frontLeftLeg.setPos(-4.0F, 6.0F, -12.0F);
        this.frontLeftShin = new ModelRenderer(this, 48, 26);
        this.frontLeftShin.addBox(-1.0F, 0.99F, -1.9F, 4.0F, 6.0F, 4.0F, scaleIn);
        this.frontLeftShin.setPos(-4.0F, 9.0F, -12.0F);
        this.frontRightLeg = new ModelRenderer(this, 48, 21);
        this.frontRightLeg.mirror = true;
        this.frontRightLeg.addBox(-3.0F, -1.01F, -1.9F, 4.0F, 5.0F, 4.0F, scaleIn);
        this.frontRightLeg.setPos(4.0F, 6.0F, -12.0F);
        this.frontRightShin = new ModelRenderer(this, 48, 26);
        this.frontRightShin.mirror = true;
        this.frontRightShin.addBox(-3.0F, 0.99F, -1.9F, 4.0F, 6.0F, 4.0F, scaleIn);
        this.frontRightShin.setPos(4.0F, 9.0F, -12.0F);

        ModelRenderer leftFaceMetal = new ModelRenderer(this, 29, 5);
        leftFaceMetal.addBox(2.0F, -9.0F, -5.0F, 1.0F, 2.0F, 2.0F, scaleIn);
        this.neck.addChild(leftFaceMetal);
        ModelRenderer rightFaceMetal = new ModelRenderer(this, 29, 5);
        rightFaceMetal.addBox(-3.0F, -9.0F, -5.0F, 1.0F, 2.0F, 2.0F, scaleIn);
        this.neck.addChild(rightFaceMetal);
        ModelRenderer leftRein = new ModelRenderer(this, 32, 2);
        leftRein.addBox(3.1F, -6.0F, -8.0F, 0.0F, 3.0F, 16.0F, scaleIn);
        leftRein.xRot = (-(float) Math.PI / 6F);
        this.neck.addChild(leftRein);
        ModelRenderer rightRein = new ModelRenderer(this, 32, 2);
        rightRein.addBox(-3.1F, -6.0F, -8.0F, 0.0F, 3.0F, 16.0F, scaleIn);
        rightRein.xRot = (-(float) Math.PI / 6F);
        this.neck.addChild(rightRein);
        ModelRenderer headRopes = new ModelRenderer(this, 1, 1);
        headRopes.addBox(-3.0F, -11.0F, -1.9F, 6.0F, 5.0F, 6.0F, 0.2F);
        this.neck.addChild(headRopes);
        ModelRenderer mouthRopes = new ModelRenderer(this, 19, 0);
        mouthRopes.addBox(-2.0F, -11.0F, -4.0F, 4.0F, 5.0F, 2.0F, 0.2F);
        this.neck.addChild(mouthRopes);

        this.saddleParts = new ModelRenderer[]{leftFaceMetal, rightFaceMetal, headRopes, mouthRopes};
        this.ridingParts = new ModelRenderer[]{leftRein, rightRein};
    }

    @Override
    public Iterable<ModelRenderer> headParts() {

        return ImmutableList.of(this.neck);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {

        return ImmutableList.of(this.body, this.backRightLeg, this.backLeftLeg, this.frontRightLeg, this.frontLeftLeg, this.backRightShin, this.backLeftShin, this.frontRightShin, this.frontLeftShin);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        boolean saddled = entityIn.isSaddled();
        boolean vehicle = entityIn.isVehicle();

        for (ModelRenderer modelrenderer : this.saddleParts) {

            modelrenderer.visible = saddled;
        }

        for (ModelRenderer modelrenderer1 : this.ridingParts) {

            modelrenderer1.visible = vehicle && saddled;
        }
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        super.prepareMobModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);

//        this.neck.y = this.headParts.y;
//        this.neck.z = this.headParts.z;
//        this.neck.xRot = this.headParts.xRot;
//        this.neck.yRot = this.headParts.yRot;
        
        


        float f = MathHelper.rotlerp(entitylivingbaseIn.yBodyRotO, entitylivingbaseIn.yBodyRot, partialTickTime);
        float f1 = MathHelper.rotlerp(entitylivingbaseIn.yHeadRotO, entitylivingbaseIn.yHeadRot, partialTickTime);
        float f2 = MathHelper.lerp(partialTickTime, entitylivingbaseIn.xRotO, entitylivingbaseIn.xRot);
        float f3 = f1 - f;
        float f4 = f2 * ((float) Math.PI / 180F);
        if (f3 > 20.0F) {
            
            f3 = 20.0F;
        }

        if (f3 < -20.0F) {
            
            f3 = -20.0F;
        }

        if (limbSwingAmount > 0.2F) {
            
            f4 += MathHelper.cos(limbSwing * 0.4F) * 0.15F * limbSwingAmount;
        }

        float f5 = entitylivingbaseIn.getEatAnim(partialTickTime);
        float f6 = entitylivingbaseIn.getStandAnim(partialTickTime);
        float f7 = 1.0F - f6;
        float f8 = entitylivingbaseIn.getMouthAnim(partialTickTime);
        float f9 = (float) entitylivingbaseIn.tickCount + partialTickTime;
        this.neck.y = 4.0F;
        this.neck.z = -12.0F;
        this.neck.xRot = ((float) Math.PI / 6F) + f4;
        this.neck.yRot = f3 * ((float) Math.PI / 180F);
        float f10 = entitylivingbaseIn.isInWater() ? 0.2F : 1.0F;
        float f11 = MathHelper.cos(f10 * limbSwing * 0.6662F + (float) Math.PI);
        float f12 = f11 * 0.8F * limbSwingAmount;
//        float f13 = (1.0F - Math.max(f6, f5)) * (((float) Math.PI / 6F) + f4 + f8 * MathHelper.sin(f9) * 0.05F);
//        this.neck.xRot = f6 * (0.2617994F + f4) + f5 * (2.1816616F + MathHelper.sin(f9) * 0.05F) + f13;
//        this.neck.yRot = f6 * f3 * ((float) Math.PI / 180F) + (1.0F - Math.max(f6, f5)) * this.neck.yRot;
        this.neck.xRot = f6 * (0.2617994F + f4) + f5 * 2.1816616F + (1.0F - Math.max(f6, f5)) * this.neck.xRot;
        this.neck.yRot = f6 * f3 * 0.017453292F + (1.0F - Math.max(f6, f5)) * this.neck.yRot;
        this.neck.y = f6 * -4.0F + f5 * 11.0F + (1.0F - Math.max(f6, f5)) * this.neck.y;
        this.neck.z = f6 * -4.0F + f5 * -12.0F + (1.0F - Math.max(f6, f5)) * this.neck.z;




        this.upperMouth.y = 0.02F;
        this.lowerMouth.y = 0.0F;
        this.upperMouth.z = 0.02F - f8;
        this.lowerMouth.z = f8;
        this.upperMouth.xRot = -0.09424778F * f8;
        this.lowerMouth.xRot = 0.15707964F * f8;
        this.upperMouth.yRot = 0.0F;
        this.lowerMouth.yRot = 0.0F;

        
        float f14 = 0.2617994F * f6;
        float f15 = MathHelper.cos(f9 * 0.6F + (float) Math.PI);
        this.frontLeftLeg.y = 2.0F * f6 + 14.0F * f7;
        this.frontLeftLeg.z = -6.0F * f6 - 10.0F * f7;
        this.frontRightLeg.y = this.frontLeftLeg.y;
        this.frontRightLeg.z = this.frontLeftLeg.z;
        this.backLeftShin.y = this.backLeftLeg.y + MathHelper.sin(((float) Math.PI / 2F) + f14 + f7 * - f11 * 0.5F * limbSwingAmount) * 3.0F;
        this.backLeftShin.z = this.backLeftLeg.z + MathHelper.cos(-((float) Math.PI / 2F) + f14 + f7 * - f11 * 0.5F * limbSwingAmount) * 3.0F;
        this.backRightShin.y = this.backRightLeg.y + MathHelper.sin(((float) Math.PI / 2F) + f14 + f7 * f11 * 0.5F * limbSwingAmount) * 3.0F;
        this.backRightShin.z = this.backRightLeg.z + MathHelper.cos(-((float) Math.PI / 2F) + f14 + f7 * f11 * 0.5F * limbSwingAmount) * 3.0F;
        float f16 = ((-(float) Math.PI / 3F) + f15) * f6 + f12 * f7;
        float f17 = ((-(float) Math.PI / 3F) - f15) * f6 - f12 * f7;
        this.frontLeftShin.y = this.frontLeftLeg.y + MathHelper.sin(((float) Math.PI / 2F) + f16) * 3.0F;
        this.frontLeftShin.z = this.frontLeftLeg.z + MathHelper.cos(-((float) Math.PI / 2F) + f16) * 3.0F;
        this.frontRightShin.y = this.frontRightLeg.y + MathHelper.sin(((float) Math.PI / 2F) + f17) * 3.0F;
        this.frontRightShin.z = this.frontRightLeg.z + MathHelper.cos(-((float) Math.PI / 2F) + f17) * 3.0F;
        this.backLeftLeg.xRot = f14 + - f11 * 0.5F * limbSwingAmount * f7;
        this.backLeftShin.xRot = -0.08726646F * f6 + (-f11 * 0.5F * limbSwingAmount - Math.max(0.0F, f11 * 0.5F * limbSwingAmount)) * f7;
        this.backRightLeg.xRot = f14 + f11 * 0.5F * limbSwingAmount * f7;
        this.backRightShin.xRot = -0.08726646F * f6 + (f11 * 0.5F * limbSwingAmount - Math.max(0.0F, -f11 * 0.5F * limbSwingAmount)) * f7;
        this.frontLeftLeg.xRot = f16;
        this.frontLeftShin.xRot = (this.frontLeftLeg.xRot + (float) Math.PI * Math.max(0.0F, 0.2F + f15 * 0.2F)) * f6 + (f12 + Math.max(0.0F, f11 * 0.5F * limbSwingAmount)) * f7;
        this.frontRightLeg.xRot = f17;
        this.frontRightShin.xRot = (this.frontRightLeg.xRot + (float) Math.PI * Math.max(0.0F, 0.2F - f15 * 0.2F)) * f6 + (-f12 + Math.max(0.0F, -f11 * 0.5F * limbSwingAmount)) * f7;


//        this.babyLeg1.y = this.backLeftLeg.y;
//        this.babyLeg1.z = this.backLeftLeg.z;
//        this.babyLeg1.xRot = this.backLeftLeg.xRot;
//        this.babyLeg2.y = this.backRightLeg.y;
//        this.babyLeg2.z = this.backRightLeg.z;
//        this.babyLeg2.xRot = this.backRightLeg.xRot;
//        this.babyLeg3.y = this.frontLeftLeg.y;
//        this.babyLeg3.z = this.frontLeftLeg.z;
//        this.babyLeg3.xRot = this.frontLeftLeg.xRot;
//        this.babyLeg4.y = this.frontRightLeg.y;
//        this.babyLeg4.z = this.frontRightLeg.z;
//        this.babyLeg4.xRot = this.frontRightLeg.xRot;
//        boolean flag1 = entitylivingbaseIn.isBaby();
//        this.backLeftLeg.visible = !flag1;
//        this.backRightLeg.visible = !flag1;
//        this.frontLeftLeg.visible = !flag1;
//        this.frontRightLeg.visible = !flag1;
//        this.babyLeg1.visible = flag1;
//        this.babyLeg2.visible = flag1;
//        this.babyLeg3.visible = flag1;
//        this.babyLeg4.visible = flag1;
//        this.body.y = flag1 ? 10.8F : 0.0F;
    }

}
