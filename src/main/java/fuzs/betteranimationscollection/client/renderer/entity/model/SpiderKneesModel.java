package fuzs.betteranimationscollection.client.renderer.entity.model;

import fuzs.betteranimationscollection.mixin.client.accessor.SpiderModelAccessor;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpiderKneesModel<T extends Entity> extends SpiderModel<T> {

    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer leg4;
    private final ModelRenderer leg5;
    private final ModelRenderer leg6;
    private final ModelRenderer leg7;
    
    public SpiderKneesModel() {
        
        this.leg0 = new ModelRenderer(this, 18, 0);
        this.leg0.addBox(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.leg0.setPos(-4.0F, 15.0F, 2.0F);
        this.leg1 = new ModelRenderer(this, 18, 0);
        this.leg1.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.leg1.setPos(4.0F, 15.0F, 2.0F);
        this.leg2 = new ModelRenderer(this, 18, 0);
        this.leg2.addBox(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.leg2.setPos(-4.0F, 15.0F, 1.0F);
        this.leg3 = new ModelRenderer(this, 18, 0);
        this.leg3.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.leg3.setPos(4.0F, 15.0F, 1.0F);
        this.leg4 = new ModelRenderer(this, 18, 0);
        this.leg4.addBox(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.leg4.setPos(-4.0F, 15.0F, 0.0F);
        this.leg5 = new ModelRenderer(this, 18, 0);
        this.leg5.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.leg5.setPos(4.0F, 15.0F, 0.0F);
        this.leg6 = new ModelRenderer(this, 18, 0);
        this.leg6.addBox(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.leg6.setPos(-4.0F, 15.0F, -1.0F);
        this.leg7 = new ModelRenderer(this, 18, 0);
        this.leg7.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.leg7.setPos(4.0F, 15.0F, -1.0F);
        
        ModelRenderer legExtension0 = new ModelRenderer(this, 24, 0);
        legExtension0.addBox(-9.0F, -1.0F, -1.0F, 10, 2, 2, -0.01F);
        legExtension0.setPos(-7.0F, 0.5F, 0.0F);
        legExtension0.zRot = -1.05F;
        ModelRenderer legExtension1 = new ModelRenderer(this, 24, 0);
        legExtension1.addBox(-1.0F, -1.0F, -1.0F, 10, 2, 2, -0.01F);
        legExtension1.setPos(7.0F, 0.5F, 0.0F);
        legExtension1.zRot = 1.05F;
        
        this.leg0.addChild(legExtension0);
        this.leg1.addChild(legExtension1);
        this.leg2.addChild(legExtension0);
        this.leg3.addChild(legExtension1);
        this.leg4.addChild(legExtension0);
        this.leg5.addChild(legExtension1);
        this.leg6.addChild(legExtension0);
        this.leg7.addChild(legExtension1);

        SpiderModelAccessor modelAccessor = (SpiderModelAccessor) this;
        modelAccessor.setLeg0(this.leg0);
        modelAccessor.setLeg1(this.leg1);
        modelAccessor.setLeg2(this.leg2);
        modelAccessor.setLeg3(this.leg3);
        modelAccessor.setLeg4(this.leg4);
        modelAccessor.setLeg5(this.leg5);
        modelAccessor.setLeg6(this.leg6);
        modelAccessor.setLeg7(this.leg7);
    }
    
    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.leg0.zRot += 0.675F;
        this.leg1.zRot -= 0.675F;
        this.leg2.zRot += 0.675F;
        this.leg3.zRot -= 0.675F;
        this.leg4.zRot += 0.675F;
        this.leg5.zRot -= 0.675F;
        this.leg6.zRot += 0.675F;
        this.leg7.zRot -= 0.675F;
    }
    
}
