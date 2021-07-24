package fuzs.betteranimationscollection.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.DrownedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.ZombieEntity;

public class DrownedKneesModel<T extends ZombieEntity> extends DrownedModel<T> {

    private final ModelRenderer rightLowerLeg;
    private final ModelRenderer leftLowerLeg;

    public DrownedKneesModel(float modelSizeIn, float yOffsetIn, int textureWidthIn, int textureHeightIn) {

        super(modelSizeIn, yOffsetIn, textureWidthIn, textureHeightIn);

        this.rightLeg = getRightDrownedLeg(this, modelSizeIn, 0.0F);
        this.leftLeg = ZombieKneesModel.getLeftLeg(this, modelSizeIn, 0.0F);
        this.rightLowerLeg = getLowerRightDrownedLeg(this, modelSizeIn, 0.0F);
        this.leftLowerLeg = ZombieKneesModel.getLowerLeftLeg(this, modelSizeIn, 0.0F);
        this.rightLeg.addChild(this.rightLowerLeg);
        this.leftLeg.addChild(this.leftLowerLeg);
    }

    public DrownedKneesModel(float modelSizeIn, boolean shortTexture) {

        super(modelSizeIn, shortTexture);

        this.rightLeg = ZombieKneesModel.getRightLeg(this, modelSizeIn, 0.0F);
        this.leftLeg = ZombieKneesModel.getLeftLeg(this, modelSizeIn, 0.0F);
        this.rightLowerLeg = ZombieKneesModel.getLowerRightLeg(this, modelSizeIn, 0.0F);
        this.leftLowerLeg = ZombieKneesModel.getLowerLeftLeg(this, modelSizeIn, 0.0F);
        this.rightLeg.addChild(this.rightLowerLeg);
        this.leftLeg.addChild(this.leftLowerLeg);
    }

    public static ModelRenderer getRightDrownedLeg(Model model, float modelSizeIn, float yOffsetIn) {

        final ModelRenderer rightLeg = new ModelRenderer(model, 16, 48);
        rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSizeIn);
        rightLeg.setPos(-1.9F, 12.0F + yOffsetIn, 0.0F);
        return rightLeg;
    }

    public static ModelRenderer getLowerRightDrownedLeg(Model model, float modelSizeIn, float yOffsetIn) {

        final ModelRenderer rightLowerLeg = new ModelRenderer(model, 16, 54);
        rightLowerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, modelSizeIn);
        rightLowerLeg.setPos(0.0F, 6.0F + yOffsetIn, 0.0F);
        return rightLowerLeg;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        ZombieKneesModel.setupKneeAnim(entityIn, limbSwing, limbSwingAmount, this, this.rightLowerLeg, this.leftLowerLeg);
        // should probably also do something about swimming animation as legs are used there, but it's broken for drowneds anyways
    }

}
