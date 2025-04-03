package fuzs.betteranimationscollection.client.model;

import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.EquineRenderState;
import net.minecraft.util.Mth;

public class FamiliarHorseModel extends HorseModel {
    private final ModelPart upperMouth;
    private final ModelPart lowerMouth;
    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftHindShin;
    private final ModelPart rightHindShin;
    private final ModelPart leftFrontShin;
    private final ModelPart rightFrontShin;
    private final ModelPart[] saddleParts;

    public FamiliarHorseModel(ModelPart modelPart) {
        super(modelPart);
        ModelPart headParts = modelPart.getChild("head_parts");
        this.upperMouth = headParts.getChild("upper_mouth");
        this.lowerMouth = headParts.getChild("lower_mouth");
        this.rightHindLeg = modelPart.getChild("right_hind_leg");
        this.leftHindLeg = modelPart.getChild("left_hind_leg");
        this.rightFrontLeg = modelPart.getChild("right_front_leg");
        this.leftFrontLeg = modelPart.getChild("left_front_leg");
        this.rightHindShin = modelPart.getChild("right_hind_shin");
        this.leftHindShin = modelPart.getChild("left_hind_shin");
        this.rightFrontShin = modelPart.getChild("right_front_shin");
        this.leftFrontShin = modelPart.getChild("left_front_shin");
        ModelPart leftSaddleMouth = this.upperMouth.getChild("left_saddle_mouth");
        ModelPart rightSaddleMouth = this.upperMouth.getChild("right_saddle_mouth");
        ModelPart mouthSaddleWrap = this.upperMouth.getChild("mouth_saddle_wrap");
        ModelPart lowerMouthSaddleWrap = this.lowerMouth.getChild("lower_mouth_saddle_wrap");
        this.saddleParts = new ModelPart[]{
                leftSaddleMouth, rightSaddleMouth, mouthSaddleWrap, lowerMouthSaddleWrap
        };
    }

    public static MeshDefinition createAnimatedBodyMesh(CubeDeformation cubeDeformation, boolean isBaby) {
        MeshDefinition meshDefinition = HorseModel.createBodyMesh(cubeDeformation);
        PartDefinition partDefinition = meshDefinition.getRoot();

        // head parts
        PartDefinition partDefinition3 = partDefinition.getChild("head_parts");
        PartDefinition upperMouth = partDefinition3.addOrReplaceChild("upper_mouth",
                CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, -3.0F, -5.0F, 4.0F, 3.0F, 5.0F, cubeDeformation),
                PartPose.offset(0.0F, -8.0F, -2.0F));
        PartDefinition lowerMouth = partDefinition3.addOrReplaceChild("lower_mouth",
                CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, 0.0F, -5.0F, 4.0F, 2.0F, 5.0F, cubeDeformation),
                PartPose.offset(0.0F, -8.0F, -2.0F));
        partDefinition3.clearChild("left_saddle_mouth");
        partDefinition3.clearChild("right_saddle_mouth");
        upperMouth.addOrReplaceChild("left_saddle_mouth",
                CubeListBuilder.create().texOffs(29, 5).addBox(2.0F, -1.0F, -4.0F, 1.0F, 2.0F, 2.0F, cubeDeformation),
                PartPose.ZERO);
        upperMouth.addOrReplaceChild("right_saddle_mouth",
                CubeListBuilder.create().texOffs(29, 5).addBox(-3.0F, -1.0F, -4.0F, 1.0F, 2.0F, 2.0F, cubeDeformation),
                PartPose.ZERO);
        partDefinition3.clearChild("mouth_saddle_wrap");
        upperMouth.addOrReplaceChild("mouth_saddle_wrap",
                CubeListBuilder.create()
                        .texOffs(19, 0)
                        .addBox(-2.0F, -3.125F, -2.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.2F)),
                PartPose.ZERO);
        lowerMouth.addOrReplaceChild("lower_mouth_saddle_wrap",
                CubeListBuilder.create()
                        .texOffs(19, 0)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.18F)),
                PartPose.ZERO);

        // legs parts
        if (isBaby) cubeDeformation = cubeDeformation.extend(0.0F, 1.0F, 0.0F);
        partDefinition.addOrReplaceChild("left_hind_leg",
                CubeListBuilder.create()
                        .texOffs(48, 21)
                        .mirror()
                        .addBox(-3.0F, -1.01F, -1.0F, 4.0F, 5.0F, 4.0F, cubeDeformation),
                PartPose.offset(4.0F, 14.0F, 7.0F));
        partDefinition.addOrReplaceChild("right_hind_leg",
                CubeListBuilder.create()
                        .texOffs(48, 21)
                        .addBox(-1.0F, -1.01F, -1.0F, 4.0F, 5.0F, 4.0F, cubeDeformation),
                PartPose.offset(-4.0F, 14.0F, 7.0F));
        partDefinition.addOrReplaceChild("left_front_leg",
                CubeListBuilder.create()
                        .texOffs(48, 21)
                        .mirror()
                        .addBox(-3.0F, -1.01F, -1.9F, 4.0F, 5.0F, 4.0F, cubeDeformation),
                PartPose.offset(4.0F, 14.0F, -10.0F));
        partDefinition.addOrReplaceChild("right_front_leg",
                CubeListBuilder.create()
                        .texOffs(48, 21)
                        .addBox(-1.0F, -1.01F, -1.9F, 4.0F, 5.0F, 4.0F, cubeDeformation),
                PartPose.offset(-4.0F, 14.0F, -10.0F));
        partDefinition.addOrReplaceChild("left_hind_shin",
                CubeListBuilder.create()
                        .texOffs(48, 26)
                        .mirror()
                        .addBox(-3.0F, 0.99F, -1.0F, 4.0F, 6.0F, 4.0F, cubeDeformation),
                PartPose.offset(4.0F, 17.0F, 7.0F));
        partDefinition.addOrReplaceChild("right_hind_shin",
                CubeListBuilder.create().texOffs(48, 26).addBox(-1.0F, 0.99F, -1.0F, 4.0F, 6.0F, 4.0F, cubeDeformation),
                PartPose.offset(-4.0F, 17.0F, 7.0F));
        partDefinition.addOrReplaceChild("left_front_shin",
                CubeListBuilder.create()
                        .texOffs(48, 26)
                        .mirror()
                        .addBox(-3.0F, 0.99F, -1.9F, 4.0F, 6.0F, 4.0F, cubeDeformation),
                PartPose.offset(4.0F, 17.0F, -10.0F));
        partDefinition.addOrReplaceChild("right_front_shin",
                CubeListBuilder.create().texOffs(48, 26).addBox(-1.0F, 0.99F, -1.9F, 4.0F, 6.0F, 4.0F, cubeDeformation),
                PartPose.offset(-4.0F, 17.0F, -10.0F));

        return meshDefinition;
    }

    public static MeshTransformer getBabyTransformer() {
        return BABY_TRANSFORMER;
    }

    @Override
    public void setupAnim(EquineRenderState renderState) {
        super.setupAnim(renderState);
        setupAnim(renderState,
                this.upperMouth,
                this.lowerMouth,
                this.rightHindShin,
                this.rightHindLeg,
                this.leftHindShin,
                this.leftHindLeg,
                this.rightFrontShin,
                this.rightFrontLeg,
                this.leftFrontShin,
                this.leftFrontLeg,
                this.saddleParts);
    }

    static void setupAnim(EquineRenderState renderState, ModelPart upperMouth, ModelPart lowerMouth, ModelPart rightHindShin, ModelPart rightHindLeg, ModelPart leftHindShin, ModelPart leftHindLeg, ModelPart rightFrontShin, ModelPart rightFrontLeg, ModelPart leftFrontShin, ModelPart leftFrontLeg, ModelPart[] saddleParts) {
        setupMouthAnim(renderState, upperMouth, lowerMouth);
        setupLegsAnim(renderState,
                rightHindShin,
                rightHindLeg,
                leftHindShin,
                leftHindLeg,
                rightFrontShin,
                rightFrontLeg,
                leftFrontShin,
                leftFrontLeg);
        setupSaddleAnim(renderState, saddleParts);
    }

    private static void setupMouthAnim(EquineRenderState renderState, ModelPart upperMouth, ModelPart lowerMouth) {
        float feedingAnimation = renderState.feedingAnimation;
        upperMouth.xRot += -0.09424778F * feedingAnimation;
        lowerMouth.xRot += 0.15707964F * feedingAnimation;
    }

    private static void setupLegsAnim(EquineRenderState renderState, ModelPart rightHindShin, ModelPart rightHindLeg, ModelPart leftHindShin, ModelPart leftHindLeg, ModelPart rightFrontShin, ModelPart rightFrontLeg, ModelPart leftFrontShin, ModelPart leftFrontLeg) {
        float standAnimation = renderState.standAnimation;
        float standAnimationInv = 1.0F - standAnimation;
        float ageInTicks = renderState.ageInTicks;
        float isInWater = renderState.isInWater ? 0.2F : 1.0F;
        float walkAnimationPos = Mth.cos(isInWater * renderState.walkAnimationPos * 0.6662F + Mth.PI);
        float walkAnimationSpeedLeg = walkAnimationPos * 1.8F * renderState.walkAnimationSpeed;
        float walkAnimationSpeedShin = walkAnimationPos * 1.4F * renderState.walkAnimationSpeed;
        float standAnimationAngle = 0.2617994F * standAnimation;
        float animationProgress = Mth.cos(ageInTicks * 0.6F + Mth.PI);
        float ageScale = renderState.ageScale;

        rightHindShin.y = rightHindLeg.y +
                Mth.sin(Mth.HALF_PI + standAnimationAngle + standAnimationInv * -walkAnimationSpeedLeg) * 3.0F *
                        ageScale;
        rightHindShin.z = rightHindLeg.z +
                Mth.cos(-Mth.HALF_PI + standAnimationAngle + standAnimationInv * -walkAnimationSpeedLeg) * 3.0F *
                        ageScale;
        leftHindShin.y = leftHindLeg.y +
                Mth.sin(Mth.HALF_PI + standAnimationAngle + standAnimationInv * walkAnimationSpeedLeg) * 3.0F *
                        ageScale;
        leftHindShin.z = leftHindLeg.z +
                Mth.cos(-Mth.HALF_PI + standAnimationAngle + standAnimationInv * walkAnimationSpeedLeg) * 3.0F *
                        ageScale;

        // similar to vanilla super, but walkAnimationSpeed variable is different
        float rightFrontLegAnimation =
                ((-Mth.PI / 3.0F) + animationProgress) * standAnimation + walkAnimationSpeedLeg * standAnimationInv;
        float leftFrontLegAnimation =
                ((-Mth.PI / 3.0F) - animationProgress) * standAnimation - walkAnimationSpeedLeg * standAnimationInv;
        rightFrontShin.y = rightFrontLeg.y + Mth.sin(Mth.HALF_PI + rightFrontLegAnimation) * 3.0F * ageScale;
        rightFrontShin.z = rightFrontLeg.z + Mth.cos(-Mth.HALF_PI + rightFrontLegAnimation) * 3.0F * ageScale;
        leftFrontShin.y = leftFrontLeg.y + Mth.sin(Mth.HALF_PI + leftFrontLegAnimation) * 3.0F * ageScale;
        leftFrontShin.z = leftFrontLeg.z + Mth.cos(-Mth.HALF_PI + leftFrontLegAnimation) * 3.0F * ageScale;

        rightHindLeg.xRot = standAnimationAngle - walkAnimationSpeedLeg * standAnimationInv;
        rightHindShin.xRot = -0.08726646F * standAnimation +
                (-walkAnimationSpeedShin - Math.max(0.0F, walkAnimationSpeedShin)) * standAnimationInv;
        leftHindLeg.xRot = standAnimationAngle + walkAnimationSpeedLeg * standAnimationInv;
        leftHindShin.xRot = -0.08726646F * standAnimation +
                (walkAnimationSpeedShin - Math.max(0.0F, -walkAnimationSpeedShin)) * standAnimationInv;

        // x-rotations for leg parts are similar to vanilla
        rightFrontLeg.xRot = rightFrontLegAnimation;
        rightFrontShin.xRot =
                (rightFrontLeg.xRot + Mth.PI * Math.max(0.0F, 0.2F + animationProgress * 0.2F)) * standAnimation +
                        (walkAnimationSpeedShin + Math.max(0.0F, walkAnimationSpeedShin)) * standAnimationInv;
        leftFrontLeg.xRot = leftFrontLegAnimation;
        leftFrontShin.xRot =
                (leftFrontLeg.xRot + Mth.PI * Math.max(0.0F, 0.2F - animationProgress * 0.2F)) * standAnimation +
                        (-walkAnimationSpeedShin + Math.max(0.0F, -walkAnimationSpeedShin)) * standAnimationInv;
    }

    private static void setupSaddleAnim(EquineRenderState renderState, ModelPart[] saddleParts) {
        for (ModelPart modelPart : saddleParts) {
            modelPart.visible = renderState.isSaddled;
        }
    }
}
