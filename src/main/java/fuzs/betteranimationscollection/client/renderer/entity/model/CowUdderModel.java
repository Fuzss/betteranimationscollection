package fuzs.betteranimationscollection.client.renderer.entity.model;

import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.CowUdderElement;
import fuzs.betteranimationscollection.mixin.client.accessor.AgeableModelAccessor;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CowUdderModel<T extends Entity> extends CowModel<T> {

    private final ModelRenderer utter;
    private final ModelRenderer[] nipples = new ModelRenderer[4];

    public CowUdderModel() {
        
        // body has to be replaced as it already includes the utter normally
        this.body = new ModelRenderer(this, 18, 4);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
        this.body.setPos(0.0F, 5.0F, 2.0F);

        this.utter = new ModelRenderer(this, 52, 0);
        this.utter.addBox(-2.0F, -2.5F, -2.0F, 4, 6, 1);
        this.utter.setPos(0.0F, 4.5F, -6.0F);
        this.body.addChild(this.utter);

        for (int i = 0; i < this.nipples.length; i++) {
            
            this.nipples[i] = new ModelRenderer(this, 52, 0);
            this.utter.addChild(this.nipples[i]);
        }
        
        this.nipples[0].addBox(-2.0F, -1.5F, -3.0F, 1, 1, 1);
        this.nipples[1].addBox(1.0F, -1.5F, -3.0F, 1, 1, 1);
        this.nipples[2].addBox(1.0F, 1.5F, -3.0F, 1, 1, 1);
        this.nipples[3].addBox(-2.0F, 1.5F, -3.0F, 1, 1, 1);

        // fix calf head being positioned wrongly since vanilla 1.15, don't need a config option for that
        ((AgeableModelAccessor) this).setYHeadOffset(8.0F);
        ((AgeableModelAccessor) this).setZHeadOffset(6.0F);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        CowUdderElement element = (CowUdderElement) BetterAnimationsCollection.WOBBLY_COW_UDDER;
        this.utter.visible = !this.young || element.calfUtter;
        for (ModelRenderer renderer : this.nipples) {

            renderer.visible = element.showNipples;
        }
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        CowUdderElement element = (CowUdderElement) BetterAnimationsCollection.WOBBLY_COW_UDDER;
        this.utter.xRot = MathHelper.sin(limbSwing * 0.5F) * limbSwingAmount * element.animationSpeed * 0.05F;
        this.utter.yRot = MathHelper.cos(limbSwing) * limbSwingAmount * element.animationSpeed * 0.125F;
    }
    
}
