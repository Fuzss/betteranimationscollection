package fuzs.betteranimationscollection.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.ZombieEntity;

public class ZombieKneesModel<T extends ZombieEntity> extends ZombieModel<T> {
    
    private final ModelRenderer rightShin;
    private final ModelRenderer leftShin;
    
    public ZombieKneesModel(float modelSizeIn, boolean shortTexture) {

        super(modelSizeIn, shortTexture);

        this.rightLeg = BipedKneesModel.makeShin(this, modelSizeIn, -1.9F, 0.0F, 0.0F, 0, 16, false);
        this.leftLeg = BipedKneesModel.makeShin(this, modelSizeIn, 1.9F, 0.0F, 0.0F, 0, 16, true);
        this.rightShin = BipedKneesModel.makeShin(this, modelSizeIn, 0.0F, -6.0F, -2.0F, 0, 22, false);
        this.leftShin = BipedKneesModel.makeShin(this, modelSizeIn, 0.0F, -6.0F, -2.0F, 0, 22, true);
        this.rightLeg.addChild(this.rightShin);
        this.leftLeg.addChild(this.leftShin);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        BipedKneesModel.setupKneeAnim(entityIn, limbSwing, limbSwingAmount, this, this.rightShin, this.leftShin);
    }
    
}
