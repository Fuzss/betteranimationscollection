package fuzs.betteranimationscollection.client.model;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Mob;

public class PiglinKneesModel<T extends Mob> extends PiglinModel<T> implements KneesModel {
    private final ModelPart rightShin;
    private final ModelPart leftShin;
    private final ModelPart rightPants;
    private final ModelPart leftPants;
    private final ModelPart rightLowerPants;
    private final ModelPart leftLowerPants;

    public PiglinKneesModel(ModelPart modelPart) {
        super(modelPart);
        ModelPart rightLeg = modelPart.getChild("right_leg");
        this.rightShin = rightLeg.getChild("right_shin");
        ModelPart leftLeg = modelPart.getChild("left_leg");
        this.leftShin = leftLeg.getChild("left_shin");
        this.rightPants = modelPart.getChild("right_pants");
        this.rightLowerPants = this.rightPants.getChild("right_lower_pants");
        this.leftPants = modelPart.getChild("left_pants");
        this.leftLowerPants = this.leftPants.getChild("left_lower_pants");
    }

    public static LayerDefinition createAnimatedBodyLayer() {
        MeshDefinition meshDefinition = PiglinModel.createMesh(CubeDeformation.NONE);
        PartDefinition partDefinition = meshDefinition.getRoot();
        Pair<CubeListBuilder, PartPose> rightLeg = HumanoidKneesModel.createShin(0, 16, -1.9F, 0.0F, 0.0F, false, CubeDeformation.NONE);
        Pair<CubeListBuilder, PartPose> leftLeg = HumanoidKneesModel.createShin(16, 48, 1.9F, 0.0F, 0.0F, true, CubeDeformation.NONE);
        Pair<CubeListBuilder, PartPose> rightShin = HumanoidKneesModel.createShin(0, 22, 0.0F, -6.0F, -2.0F, false, CubeDeformation.NONE);
        Pair<CubeListBuilder, PartPose> leftShin = HumanoidKneesModel.createShin(16, 54, 0.0F, -6.0F, -2.0F, true, CubeDeformation.NONE);
        PartDefinition partDefinition1 = partDefinition.addOrReplaceChild("right_leg", rightLeg.left(), rightLeg.right());
        PartDefinition partDefinition2 = partDefinition.addOrReplaceChild("left_leg", leftLeg.left(), leftLeg.right());
        partDefinition1.addOrReplaceChild("right_shin", rightShin.left(), rightShin.right());
        partDefinition2.addOrReplaceChild("left_shin", leftShin.left(), leftShin.right());
        CubeDeformation cubeDeformation = new CubeDeformation(0.25F);
        Pair<CubeListBuilder, PartPose> rightPants = HumanoidKneesModel.createShin( 0, 32, -1.9F, 0.0F, 0.0F, false, cubeDeformation);
        Pair<CubeListBuilder, PartPose> leftPants = HumanoidKneesModel.createShin(0, 48, 1.9F, 0.0F, 0.0F, true, cubeDeformation);
        Pair<CubeListBuilder, PartPose> rightLowerPants = HumanoidKneesModel.createShin(0, 38, 0.0F, -6.0F, -2.0F, false, cubeDeformation);
        Pair<CubeListBuilder, PartPose> leftLowerPants = HumanoidKneesModel.createShin(0, 54, 0.0F, -6.0F, -2.0F, true, cubeDeformation);
        PartDefinition partDefinition11 = partDefinition.addOrReplaceChild("right_pants", rightPants.left(), rightPants.right());
        PartDefinition partDefinition22 = partDefinition.addOrReplaceChild("left_pants", leftPants.left(), leftPants.right());
        partDefinition11.addOrReplaceChild("right_lower_pants", rightLowerPants.left(), rightLowerPants.right());
        partDefinition22.addOrReplaceChild("left_lower_pants", leftLowerPants.left(), leftLowerPants.right());
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg, this.hat, this.leftPants, this.rightPants, this.leftSleeve, this.rightSleeve, this.jacket);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        KneesModel.setupAnim(this, entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.rightPants.copyFrom(this.rightLeg);
        this.leftPants.copyFrom(this.leftLeg);
        this.rightLowerPants.copyFrom(this.rightShin);
        this.leftLowerPants.copyFrom(this.leftShin);
    }

    @Override
    public void copyPropertiesTo(HumanoidModel<T> model) {
        super.copyPropertiesTo(model);
        KneesModel.copyPropertiesTo(this, model);
    }

    @Override
    public ModelPart rightShin() {
        return this.rightShin;
    }

    @Override
    public ModelPart leftShin() {
        return this.leftShin;
    }
}
