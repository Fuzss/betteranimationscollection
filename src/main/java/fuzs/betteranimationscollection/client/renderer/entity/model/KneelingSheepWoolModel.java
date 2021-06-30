package fuzs.betteranimationscollection.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.SheepWoolModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.SheepEntity;

public class KneelingSheepWoolModel<T extends SheepEntity> extends SheepWoolModel<T> {
    
    private float rotation;

    public KneelingSheepWoolModel() {
        
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-4.0F, -15.0F, 0.0F, 8, 16, 6, 1.75F);
        this.body.setPos(0.0F, 12.0F, 7.0F);
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        super.prepareMobModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        this.rotation = entitylivingbaseIn.getHeadEatPositionScale(partialTickTime);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.body.xRot += this.rotation * 0.4F;
        this.leg2.y = this.leg3.y = 12.0F + this.rotation * 4.0F;
        this.leg2.xRot -= this.rotation;
        this.leg3.xRot -= this.rotation;
    }
    
}