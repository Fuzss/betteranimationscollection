package fuzs.betteranimationscollection.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.ZombieEntity;

public class ZombieKneesModel<T extends ZombieEntity> extends ZombieModel<T> {
    
    private final ModelRenderer rightLowerLeg;
    private final ModelRenderer leftLowerLeg;
    
    public ZombieKneesModel(float modelSizeIn, boolean shortTexture) {

        super(modelSizeIn, shortTexture);

        this.rightLeg = BipedKneesModel.getHalfLeg(this, modelSizeIn, -1.9F, 0.0F, 0, 16, false);
        this.leftLeg = BipedKneesModel.getHalfLeg(this, modelSizeIn, 1.9F, 0.0F, 0, 16, true);
        this.rightLowerLeg = BipedKneesModel.getHalfLeg(this, modelSizeIn, 0.0F, -6.0F, 0, 22, false);
        this.leftLowerLeg = BipedKneesModel.getHalfLeg(this, modelSizeIn, 0.0F, -6.0F, 0, 22, true);
        this.rightLeg.addChild(this.rightLowerLeg);
        this.leftLeg.addChild(this.leftLowerLeg);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        BipedKneesModel.setupKneeAnim(entityIn, limbSwing, limbSwingAmount, this, this.rightLowerLeg, this.leftLowerLeg);
    }
    
}
