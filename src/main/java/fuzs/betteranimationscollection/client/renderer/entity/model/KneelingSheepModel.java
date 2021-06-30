package fuzs.betteranimationscollection.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KneelingSheepModel<T extends SheepEntity> extends SheepModel<T> {
    
    private final ModelRenderer leg4;
    private final ModelRenderer leg5;

    private float rotation;

    public KneelingSheepModel() {
        
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-4.0F, -15.0F, 0.0F, 8, 16, 6, 0.0F);
        this.body.setPos(0.0F, 12.0F, 7.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.leg2.setPos(-3.0F, 12.0F, -5.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.leg3.setPos(3.0F, 12.0F, -5.0F);
        this.leg4 = new ModelRenderer(this, 0, 22);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.leg4.texOffs(0, 16);
        this.leg4.addBox(-2.0F, 5.02F, -2.0F, 4, 1, 4, -0.01F);
        this.leg4.setPos(0.0F, 6.0F, 0.0F);
        this.leg2.addChild(this.leg4);
        this.leg5 = new ModelRenderer(this, 0, 22);
        this.leg5.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.leg5.texOffs(0, 16);
        this.leg5.addBox(-2.0F, 5.02F, -2.0F, 4, 1, 4, -0.01F);
        this.leg5.setPos(0.0F, 6.0F, 0.0F);
        this.leg3.addChild(this.leg5);
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
        this.leg4.xRot = this.leg5.xRot = this.rotation * 2.0F;
    }
    
}