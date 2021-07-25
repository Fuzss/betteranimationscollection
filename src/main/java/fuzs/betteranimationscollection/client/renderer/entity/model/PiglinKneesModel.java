package fuzs.betteranimationscollection.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.PiglinModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class PiglinKneesModel<T extends MobEntity> extends PiglinModel<T> {

    private final ModelRenderer rightLowerLeg;
    private final ModelRenderer leftLowerLeg;
    private final ModelRenderer leftPants;
    private final ModelRenderer rightPants;
    private final ModelRenderer rightLowerPants;
    private final ModelRenderer leftLowerPants;

    public PiglinKneesModel() {
        
        super(0.0F, 64, 64);

        this.rightLeg = BipedKneesModel.makeHalfLeg(this, 0.0F, -1.9F, 0.0F, 0.0F, 0, 16, false);
        this.leftLeg = BipedKneesModel.makeHalfLeg(this, 0.0F, 1.9F, 0.0F, 0.0F, 16, 48, true);
        this.rightLowerLeg = BipedKneesModel.makeHalfLeg(this, 0.0F, 0.0F, -6.0F, -2.0F, 0, 22, false);
        this.leftLowerLeg = BipedKneesModel.makeHalfLeg(this, 0.0F, 0.0F, -6.0F, -2.0F, 16, 54, true);
        this.rightLeg.addChild(this.rightLowerLeg);
        this.leftLeg.addChild(this.leftLowerLeg);
        this.rightPants = BipedKneesModel.makeHalfLeg(this, 0.25F, -1.9F, 0.0F, 0.0F, 0, 32, false);
        this.leftPants = BipedKneesModel.makeHalfLeg(this, 0.25F, 1.9F, 0.0F, 0.0F, 0, 48, true);
        this.rightLowerPants = BipedKneesModel.makeHalfLeg(this, 0.25F, 0.0F, -6.0F, -2.0F, 0, 38, false);
        this.leftLowerPants = BipedKneesModel.makeHalfLeg(this, 0.25F, 0.0F, -6.0F, -2.0F, 0, 54, true);
        this.rightPants.addChild(this.rightLowerPants);
        this.leftPants.addChild(this.leftLowerPants);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {

        return ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg, this.hat, this.leftPants, this.rightPants, this.leftSleeve, this.rightSleeve, this.jacket);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        BipedKneesModel.setupKneeAnim(entityIn, limbSwing, limbSwingAmount, this, this.rightLowerLeg, this.leftLowerLeg);
        this.rightPants.copyFrom(this.rightLeg);
        this.leftPants.copyFrom(this.leftLeg);
        this.rightLowerPants.copyFrom(this.rightLowerLeg);
        this.leftLowerPants.copyFrom(this.leftLowerLeg);
    }

    @Override
    public void setAllVisible(boolean visible) {

        super.setAllVisible(visible);
        this.rightPants.visible = visible;
        this.leftPants.visible = visible;
    }

}
