package fuzs.betteranimationscollection.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.client.renderer.model.ModelPart;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.util.Mth;
import net.minecraft.util.math.Mth;
import net.minecraft.world.entity.animal.horse.AbstractHorse;

public class FamiliarHorseModel<T extends AbstractHorse> extends HorseModel<T> {
    private final ModelPart neck;
    private final ModelPart upperMouth;
    private final ModelPart lowerMouth;
    private final ModelPart upperMouthRopes;
    private final ModelPart lowerMouthRopes;
    private final ModelPart backRightLeg;
    private final ModelPart backLeftLeg;
    private final ModelPart frontRightLeg;
    private final ModelPart frontLeftLeg;
    private final ModelPart backRightShin;
    private final ModelPart backLeftShin;
    private final ModelPart frontRightShin;
    private final ModelPart frontLeftShin;
    private final ModelPart babyBackRightLeg;
    private final ModelPart babyBackLeftLeg;
    private final ModelPart babyFrontRightLeg;
    private final ModelPart babyFrontLeftLeg;
    private final ModelPart babyBackRightShin;
    private final ModelPart babyBackLeftShin;
    private final ModelPart babyFrontRightShin;
    private final ModelPart babyFrontLeftShin;
    private final ModelPart rightFaceMetal;
    private final ModelPart leftFaceMetal;

    private final ModelPart[] saddleParts;
    private final ModelPart[] ridingParts;

    public FamiliarHorseModel(float scaleIn) {

        super(scaleIn);

        this.neck = new ModelPart(this, 0, 35);
        this.neck.addBox(-2.05F, -6.0F, -2.0F, 4.0F, 12.0F, 7.0F);
        this.neck.xRot = ((float) Math.PI / 6F);
        ModelPart head = new ModelPart(this, 0, 13);
        head.addBox(-3.0F, -11.0F, -2.0F, 6.0F, 5.0F, 7.0F, scaleIn);
        this.neck.addChild(head);
        ModelPart mane = new ModelPart(this, 56, 36);
        mane.addBox(-1.0F, -11.0F, 5.01F, 2.0F, 16.0F, 2.0F, scaleIn);
        this.neck.addChild(mane);
        this.upperMouth = new ModelPart(this, 0, 25);
        this.upperMouth.addBox(-2.0F, -11.0F, -7.0F, 4.0F, 3.0F, 5.0F, scaleIn);
        this.neck.addChild(this.upperMouth);
        this.lowerMouth = new ModelPart(this, 0, 28);
        this.lowerMouth.addBox(-2.0F, -8.0F, -7.0F, 4.0F, 2.0F, 5.0F, scaleIn);
        this.neck.addChild(this.lowerMouth);
        this.addEarModels(this.neck);

        this.backLeftLeg = new ModelPart(this, 48, 21);
        this.backLeftLeg.addBox(-1.0F, -1.01F, -1.0F, 4.0F, 5.0F, 4.0F, scaleIn);
        this.backLeftLeg.setPos(-4.0F, 14.0F, 7.0F);
        this.backLeftShin = new ModelPart(this, 48, 26);
        this.backLeftShin.addBox(-1.0F, 0.99F, -1.0F, 4.0F, 6.0F, 4.0F, scaleIn);
        this.backLeftShin.setPos(-4.0F, 17.0F, 7.0F);
        this.backRightLeg = new ModelPart(this, 48, 21);
        this.backRightLeg.mirror = true;
        this.backRightLeg.addBox(-3.0F, -1.01F, -1.0F, 4.0F, 5.0F, 4.0F, scaleIn);
        this.backRightLeg.setPos(4.0F, 14.0F, 7.0F);
        this.backRightShin = new ModelPart(this, 48, 26);
        this.backRightShin.mirror = true;
        this.backRightShin.addBox(-3.0F, 0.99F, -1.0F, 4.0F, 6.0F, 4.0F, scaleIn);
        this.backRightShin.setPos(4.0F, 17.0F, 7.0F);
        this.frontLeftLeg = new ModelPart(this, 48, 21);
        this.frontLeftLeg.addBox(-1.0F, -1.01F, -1.9F, 4.0F, 5.0F, 4.0F, scaleIn);
        this.frontLeftLeg.setPos(-4.0F, 6.0F, -12.0F);
        this.frontLeftShin = new ModelPart(this, 48, 26);
        this.frontLeftShin.addBox(-1.0F, 0.99F, -1.9F, 4.0F, 6.0F, 4.0F, scaleIn);
        this.frontLeftShin.setPos(-4.0F, 9.0F, -12.0F);
        this.frontRightLeg = new ModelPart(this, 48, 21);
        this.frontRightLeg.mirror = true;
        this.frontRightLeg.addBox(-3.0F, -1.01F, -1.9F, 4.0F, 5.0F, 4.0F, scaleIn);
        this.frontRightLeg.setPos(4.0F, 6.0F, -12.0F);
        this.frontRightShin = new ModelPart(this, 48, 26);
        this.frontRightShin.mirror = true;
        this.frontRightShin.addBox(-3.0F, 0.99F, -1.9F, 4.0F, 6.0F, 4.0F, scaleIn);
        this.frontRightShin.setPos(4.0F, 9.0F, -12.0F);

        this.babyBackLeftLeg = new ModelPart(this, 48, 21);
        this.babyBackLeftLeg.addBox(-1.0F, 0.01F, -1.0F, 4.0F, 5.0F, 4.0F, scaleIn, scaleIn + 1.0F, scaleIn);
        this.babyBackLeftLeg.setPos(-4.0F, 14.0F, 7.0F);
        this.babyBackLeftShin = new ModelPart(this, 48, 26);
        this.babyBackLeftShin.addBox(-1.0F, 4.01F, -1.0F, 4.0F, 6.0F, 4.0F, scaleIn, scaleIn + 1.0F, scaleIn);
        this.babyBackLeftShin.setPos(-4.0F, 17.0F, 7.0F);
        this.babyBackRightLeg = new ModelPart(this, 48, 21);
        this.babyBackRightLeg.mirror = true;
        this.babyBackRightLeg.addBox(-3.0F, 0.01F, -1.0F, 4.0F, 5.0F, 4.0F, scaleIn, scaleIn + 1.0F, scaleIn);
        this.babyBackRightLeg.setPos(4.0F, 14.0F, 7.0F);
        this.babyBackRightShin = new ModelPart(this, 48, 26);
        this.babyBackRightShin.mirror = true;
        this.babyBackRightShin.addBox(-3.0F, 4.01F, -1.0F, 4.0F, 6.0F, 4.0F, scaleIn, scaleIn + 1.0F, scaleIn);
        this.babyBackRightShin.setPos(4.0F, 17.0F, 7.0F);
        this.babyFrontLeftLeg = new ModelPart(this, 48, 21);
        this.babyFrontLeftLeg.addBox(-1.0F, 0.01F, -1.9F, 4.0F, 5.0F, 4.0F, scaleIn, scaleIn + 1.0F, scaleIn);
        this.babyFrontLeftLeg.setPos(-4.0F, 6.0F, -12.0F);
        this.babyFrontLeftShin = new ModelPart(this, 48, 26);
        this.babyFrontLeftShin.addBox(-1.0F, 4.01F, -1.9F, 4.0F, 6.0F, 4.0F, scaleIn, scaleIn + 1.0F, scaleIn);
        this.babyFrontLeftShin.setPos(-4.0F, 9.0F, -12.0F);
        this.babyFrontRightLeg = new ModelPart(this, 48, 21);
        this.babyFrontRightLeg.mirror = true;
        this.babyFrontRightLeg.addBox(-3.0F, 0.01F, -1.9F, 4.0F, 5.0F, 4.0F, scaleIn, scaleIn + 1.0F, scaleIn);
        this.babyFrontRightLeg.setPos(4.0F, 6.0F, -12.0F);
        this.babyFrontRightShin = new ModelPart(this, 48, 26);
        this.babyFrontRightShin.mirror = true;
        this.babyFrontRightShin.addBox(-3.0F, 4.01F, -1.9F, 4.0F, 6.0F, 4.0F, scaleIn, scaleIn + 1.0F, scaleIn);
        this.babyFrontRightShin.setPos(4.0F, 9.0F, -12.0F);

        this.leftFaceMetal = new ModelPart(this, 29, 5);
        this.leftFaceMetal.addBox(2.0F, -9.0F, -5.0F, 1.0F, 2.0F, 2.0F, scaleIn);
        this.neck.addChild(this.leftFaceMetal);
        this.rightFaceMetal = new ModelPart(this, 29, 5);
        this.rightFaceMetal.addBox(-3.0F, -9.0F, -5.0F, 1.0F, 2.0F, 2.0F, scaleIn);
        this.neck.addChild(this.rightFaceMetal);
        ModelPart leftRein = new ModelPart(this, 32, 2);
        leftRein.addBox(3.1F, -6.0F, -8.0F, 0.0F, 3.0F, 16.0F, scaleIn);
        leftRein.xRot = (-(float) Math.PI / 6F);
        this.neck.addChild(leftRein);
        ModelPart rightRein = new ModelPart(this, 32, 2);
        rightRein.addBox(-3.1F, -6.0F, -8.0F, 0.0F, 3.0F, 16.0F, scaleIn);
        rightRein.xRot = (-(float) Math.PI / 6F);
        this.neck.addChild(rightRein);
        ModelPart headRopes = new ModelPart(this, 1, 1);
        headRopes.addBox(-3.0F, -11.0F, -1.9F, 6.0F, 5.0F, 6.0F, 0.2F);
        this.neck.addChild(headRopes);
        this.upperMouthRopes = new ModelPart(this, 19, 0);
        this.upperMouthRopes.addBox(-2.0F, -11.0F, -4.0F, 4.0F, 3.0F, 2.0F, 0.2F);
        this.neck.addChild(this.upperMouthRopes);
        this.lowerMouthRopes = new ModelPart(this, 19, 0);
        this.lowerMouthRopes.addBox(-2.0F, -8.0F, -4.0F, 4.0F, 2.0F, 2.0F, 0.2F);
        this.neck.addChild(this.lowerMouthRopes);

        this.saddleParts = new ModelPart[]{this.leftFaceMetal, this.rightFaceMetal, headRopes, this.upperMouthRopes, this.lowerMouthRopes};
        this.ridingParts = new ModelPart[]{leftRein, rightRein};
    }

    public static MeshDefinition createBodyMesh(CubeDeformation cubeDeformation) {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition partDefinition2 = partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-5.0F, -8.0F, -17.0F, 10.0F, 10.0F, 22.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 11.0F, 5.0F));
        PartDefinition partDefinition3 = partDefinition.addOrReplaceChild("head_parts", CubeListBuilder.create().texOffs(0, 35).addBox(-2.05F, -6.0F, -2.0F, 4.0F, 12.0F, 7.0F), PartPose.offsetAndRotation(0.0F, 4.0F, -12.0F, 0.5235988F, 0.0F, 0.0F));
        PartDefinition partDefinition4 = partDefinition3.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -11.0F, -2.0F, 6.0F, 5.0F, 7.0F, cubeDeformation), PartPose.ZERO);
        partDefinition3.addOrReplaceChild("mane", CubeListBuilder.create().texOffs(56, 36).addBox(-1.0F, -11.0F, 5.01F, 2.0F, 16.0F, 2.0F, cubeDeformation), PartPose.ZERO);
        partDefinition3.addOrReplaceChild("upper_mouth", CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, -11.0F, -7.0F, 4.0F, 5.0F, 5.0F, cubeDeformation), PartPose.ZERO);
        partDefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-3.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, cubeDeformation), PartPose.offset(4.0F, 14.0F, 7.0F));
        partDefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(48, 21).addBox(-1.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, cubeDeformation), PartPose.offset(-4.0F, 14.0F, 7.0F));
        partDefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-3.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, cubeDeformation), PartPose.offset(4.0F, 14.0F, -12.0F));
        partDefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(48, 21).addBox(-1.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, cubeDeformation), PartPose.offset(-4.0F, 14.0F, -12.0F));
        CubeDeformation cubeDeformation2 = cubeDeformation.extend(0.0F, 5.5F, 0.0F);
        partDefinition.addOrReplaceChild("left_hind_baby_leg", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-3.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, cubeDeformation2), PartPose.offset(4.0F, 14.0F, 7.0F));
        partDefinition.addOrReplaceChild("right_hind_baby_leg", CubeListBuilder.create().texOffs(48, 21).addBox(-1.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, cubeDeformation2), PartPose.offset(-4.0F, 14.0F, 7.0F));
        partDefinition.addOrReplaceChild("left_front_baby_leg", CubeListBuilder.create().texOffs(48, 21).mirror().addBox(-3.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, cubeDeformation2), PartPose.offset(4.0F, 14.0F, -12.0F));
        partDefinition.addOrReplaceChild("right_front_baby_leg", CubeListBuilder.create().texOffs(48, 21).addBox(-1.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, cubeDeformation2), PartPose.offset(-4.0F, 14.0F, -12.0F));
        partDefinition2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(42, 36).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 14.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -5.0F, 2.0F, 0.5235988F, 0.0F, 0.0F));
        partDefinition2.addOrReplaceChild("saddle", CubeListBuilder.create().texOffs(26, 0).addBox(-5.0F, -8.0F, -9.0F, 10.0F, 9.0F, 9.0F, new CubeDeformation(0.5F)), PartPose.ZERO);
        partDefinition3.addOrReplaceChild("left_saddle_mouth", CubeListBuilder.create().texOffs(29, 5).addBox(2.0F, -9.0F, -6.0F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.ZERO);
        partDefinition3.addOrReplaceChild("right_saddle_mouth", CubeListBuilder.create().texOffs(29, 5).addBox(-3.0F, -9.0F, -6.0F, 1.0F, 2.0F, 2.0F, cubeDeformation), PartPose.ZERO);
        partDefinition3.addOrReplaceChild("left_saddle_line", CubeListBuilder.create().texOffs(32, 2).addBox(3.1F, -6.0F, -8.0F, 0.0F, 3.0F, 16.0F, cubeDeformation), PartPose.rotation(-0.5235988F, 0.0F, 0.0F));
        partDefinition3.addOrReplaceChild("right_saddle_line", CubeListBuilder.create().texOffs(32, 2).addBox(-3.1F, -6.0F, -8.0F, 0.0F, 3.0F, 16.0F, cubeDeformation), PartPose.rotation(-0.5235988F, 0.0F, 0.0F));
        partDefinition3.addOrReplaceChild("head_saddle", CubeListBuilder.create().texOffs(1, 1).addBox(-3.0F, -11.0F, -1.9F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.2F)), PartPose.ZERO);
        partDefinition3.addOrReplaceChild("mouth_saddle_wrap", CubeListBuilder.create().texOffs(19, 0).addBox(-2.0F, -11.0F, -4.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.ZERO);
        partDefinition4.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(19, 16).addBox(0.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.ZERO);
        partDefinition4.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(19, 16).addBox(-2.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.ZERO);
        return meshDefinition;
    }

    @Override
    public Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.neck);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.backRightLeg, this.backLeftLeg, this.frontRightLeg, this.frontLeftLeg, this.backRightShin, this.backLeftShin, this.frontRightShin, this.frontLeftShin, this.babyBackRightLeg, this.babyBackLeftLeg, this.babyFrontRightLeg, this.babyFrontLeftLeg, this.babyBackRightShin, this.babyBackLeftShin, this.babyFrontRightShin, this.babyFrontLeftShin);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        boolean saddled = entityIn.isSaddled();
        boolean vehicle = entityIn.isVehicle();

        for (ModelPart modelrenderer : this.saddleParts) {
            modelrenderer.visible = saddled;
        }

        for (ModelPart modelrenderer : this.ridingParts) {
            modelrenderer.visible = vehicle && saddled;
        }
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        super.prepareMobModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);

        float f = Mth.rotLerp(entitylivingbaseIn.yBodyRotO, entitylivingbaseIn.yBodyRot, partialTickTime);
        float f1 = Mth.rotLerp(entitylivingbaseIn.yHeadRotO, entitylivingbaseIn.yHeadRot, partialTickTime);
        float f2 = Mth.lerp(partialTickTime, entitylivingbaseIn.xRotO, entitylivingbaseIn.getXRot());
        float f3 = f1 - f;
        float f4 = f2 * ((float) Math.PI / 180F);
        f3 = Mth.clamp(f3, -20.0F, 20.0F);
        if (limbSwingAmount > 0.2F) {

            f4 += Mth.cos(limbSwing * 0.4F) * 0.15F * limbSwingAmount;
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
        float f11 = Mth.cos(f10 * limbSwing * 0.6662F + (float) Math.PI);
        float f12 = f11 * 1.8F * limbSwingAmount;
        float f13 = f11 * 1.4F * limbSwingAmount;
        this.neck.xRot = f6 * (0.2617994F + f4) + f5 * 2.1816616F + (1.0F - Math.max(f6, f5)) * this.neck.xRot;
        this.neck.yRot = f6 * f3 * 0.017453292F + (1.0F - Math.max(f6, f5)) * this.neck.yRot;
        this.neck.y = f6 * -4.0F + f5 * 11.0F + (1.0F - Math.max(f6, f5)) * this.neck.y;
        this.neck.z = f6 * -4.0F + f5 * -12.0F + (1.0F - Math.max(f6, f5)) * this.neck.z;

        this.upperMouth.y = this.upperMouthRopes.y = this.leftFaceMetal.y = this.rightFaceMetal.y = 0.02F;
        this.lowerMouth.y = this.lowerMouthRopes.y = 0.0F;
        this.upperMouth.z = this.upperMouthRopes.z = this.leftFaceMetal.z = this.rightFaceMetal.z = 0.02F - f8;
        this.lowerMouth.z = this.lowerMouthRopes.z = f8;
        this.upperMouth.xRot = this.upperMouthRopes.xRot = this.leftFaceMetal.xRot = this.rightFaceMetal.xRot = -0.09424778F * f8;
        this.lowerMouth.xRot = this.lowerMouthRopes.xRot = 0.15707964F * f8;
        this.upperMouth.yRot = this.upperMouthRopes.yRot = this.leftFaceMetal.yRot = this.rightFaceMetal.yRot = 0.0F;
        this.lowerMouth.yRot = this.lowerMouthRopes.yRot = 0.0F;

        float f14 = 0.2617994F * f6;
        float f15 = Mth.cos(f9 * 0.6F + (float) Math.PI);
        this.frontLeftLeg.y = 2.0F * f6 + 14.0F * f7;
        this.frontLeftLeg.z = -6.0F * f6 - 10.0F * f7;
        this.frontRightLeg.y = this.frontLeftLeg.y;
        this.frontRightLeg.z = this.frontLeftLeg.z;
        this.backLeftShin.y = this.backLeftLeg.y + Mth.sin(((float) Math.PI / 2F) + f14 + f7 * - f12) * 3.0F;
        this.backLeftShin.z = this.backLeftLeg.z + Mth.cos(-((float) Math.PI / 2F) + f14 + f7 * - f12) * 3.0F;
        this.backRightShin.y = this.backRightLeg.y + Mth.sin(((float) Math.PI / 2F) + f14 + f7 * f12) * 3.0F;
        this.backRightShin.z = this.backRightLeg.z + Mth.cos(-((float) Math.PI / 2F) + f14 + f7 * f12) * 3.0F;
        float f16 = ((-(float) Math.PI / 3F) + f15) * f6 + f12 * f7;
        float f17 = ((-(float) Math.PI / 3F) - f15) * f6 - f12 * f7;
        this.frontLeftShin.y = this.frontLeftLeg.y + Mth.sin(((float) Math.PI / 2F) + f16) * 3.0F;
        this.frontLeftShin.z = this.frontLeftLeg.z + Mth.cos(-((float) Math.PI / 2F) + f16) * 3.0F;
        this.frontRightShin.y = this.frontRightLeg.y + Mth.sin(((float) Math.PI / 2F) + f17) * 3.0F;
        this.frontRightShin.z = this.frontRightLeg.z + Mth.cos(-((float) Math.PI / 2F) + f17) * 3.0F;
        this.backLeftLeg.xRot = f14 - f12 * f7;
        this.backLeftShin.xRot = -0.08726646F * f6 + (-f13 - Math.max(0.0F, f13)) * f7;
        this.backRightLeg.xRot = f14 + f12 * f7;
        this.backRightShin.xRot = -0.08726646F * f6 + (f13 - Math.max(0.0F, -f13)) * f7;
        this.frontLeftLeg.xRot = f16;
        this.frontLeftShin.xRot = (this.frontLeftLeg.xRot + (float) Math.PI * Math.max(0.0F, 0.2F + f15 * 0.2F)) * f6 + (f13 + Math.max(0.0F, f13)) * f7;
        this.frontRightLeg.xRot = f17;
        this.frontRightShin.xRot = (this.frontRightLeg.xRot + (float) Math.PI * Math.max(0.0F, 0.2F - f15 * 0.2F)) * f6 + (-f13 + Math.max(0.0F, -f13)) * f7;
        this.copyBabyModelAngles();
        this.setBabyModelVisibility();
    }

    private void copyBabyModelAngles() {
        this.babyBackLeftLeg.y = this.backLeftLeg.y;
        this.babyBackLeftLeg.z = this.backLeftLeg.z;
        this.babyBackLeftLeg.xRot = this.backLeftLeg.xRot;
        this.babyBackRightLeg.y = this.backRightLeg.y;
        this.babyBackRightLeg.z = this.backRightLeg.z;
        this.babyBackRightLeg.xRot = this.backRightLeg.xRot;
        this.babyFrontLeftLeg.y = this.frontLeftLeg.y;
        this.babyFrontLeftLeg.z = this.frontLeftLeg.z;
        this.babyFrontLeftLeg.xRot = this.frontLeftLeg.xRot;
        this.babyFrontRightLeg.y = this.frontRightLeg.y;
        this.babyFrontRightLeg.z = this.frontRightLeg.z;
        this.babyFrontRightLeg.xRot = this.frontRightLeg.xRot;
        this.babyBackLeftShin.y = this.backLeftShin.y;
        this.babyBackLeftShin.z = this.backLeftShin.z;
        this.babyBackLeftShin.xRot = this.backLeftShin.xRot;
        this.babyBackRightShin.y = this.backRightShin.y;
        this.babyBackRightShin.z = this.backRightShin.z;
        this.babyBackRightShin.xRot = this.backRightShin.xRot;
        this.babyFrontLeftShin.y = this.frontLeftShin.y;
        this.babyFrontLeftShin.z = this.frontLeftShin.z;
        this.babyFrontLeftShin.xRot = this.frontLeftShin.xRot;
        this.babyFrontRightShin.y = this.frontRightShin.y;
        this.babyFrontRightShin.z = this.frontRightShin.z;
        this.babyFrontRightShin.xRot = this.frontRightShin.xRot;
    }

    private void setBabyModelVisibility() {
        this.backLeftLeg.visible = !this.young;
        this.backRightLeg.visible = !this.young;
        this.frontLeftLeg.visible = !this.young;
        this.frontRightLeg.visible = !this.young;
        this.backLeftShin.visible = !this.young;
        this.backRightShin.visible = !this.young;
        this.frontLeftShin.visible = !this.young;
        this.frontRightShin.visible = !this.young;
        this.babyBackLeftLeg.visible = this.young;
        this.babyBackRightLeg.visible = this.young;
        this.babyFrontLeftLeg.visible = this.young;
        this.babyFrontRightLeg.visible = this.young;
        this.babyBackLeftShin.visible = this.young;
        this.babyBackRightShin.visible = this.young;
        this.babyFrontLeftShin.visible = this.young;
        this.babyFrontRightShin.visible = this.young;
    }
}
