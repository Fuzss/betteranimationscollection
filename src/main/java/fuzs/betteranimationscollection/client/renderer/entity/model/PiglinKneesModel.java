package fuzs.betteranimationscollection.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.PiglinModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class PiglinKneesModel<T extends MobEntity> extends PiglinModel<T> {

    private final ModelRenderer rightShin;
    private final ModelRenderer leftShin;
    private final ModelRenderer leftPants;
    private final ModelRenderer rightPants;
    private final ModelRenderer rightLowerPants;
    private final ModelRenderer leftLowerPants;

    public PiglinKneesModel() {
        
        super(0.0F, 64, 64);

        this.rightLeg = BipedKneesModel.makeShin(this, 0.0F, -1.9F, 0.0F, 0.0F, 0, 16, false);
        this.leftLeg = BipedKneesModel.makeShin(this, 0.0F, 1.9F, 0.0F, 0.0F, 16, 48, true);
        this.rightShin = BipedKneesModel.makeShin(this, 0.0F, 0.0F, -6.0F, -2.0F, 0, 22, false);
        this.leftShin = BipedKneesModel.makeShin(this, 0.0F, 0.0F, -6.0F, -2.0F, 16, 54, true);
        this.rightLeg.addChild(this.rightShin);
        this.leftLeg.addChild(this.leftShin);
        this.rightPants = BipedKneesModel.makeShin(this, 0.25F, -1.9F, 0.0F, 0.0F, 0, 32, false);
        this.leftPants = BipedKneesModel.makeShin(this, 0.25F, 1.9F, 0.0F, 0.0F, 0, 48, true);
        this.rightLowerPants = BipedKneesModel.makeShin(this, 0.25F, 0.0F, -6.0F, -2.0F, 0, 38, false);
        this.leftLowerPants = BipedKneesModel.makeShin(this, 0.25F, 0.0F, -6.0F, -2.0F, 0, 54, true);
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
        BipedKneesModel.setupKneeAnim(entityIn, limbSwing, limbSwingAmount, this, this.rightShin, this.leftShin);
        this.rightPants.copyFrom(this.rightLeg);
        this.leftPants.copyFrom(this.leftLeg);
        this.rightLowerPants.copyFrom(this.rightShin);
        this.leftLowerPants.copyFrom(this.leftShin);
    }

    @Override
    public void setAllVisible(boolean visible) {

        super.setAllVisible(visible);
        this.rightPants.visible = visible;
        this.leftPants.visible = visible;
    }

}
