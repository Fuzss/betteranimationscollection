package fuzs.betteranimationscollection.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.CatTailElement;
import net.minecraft.client.renderer.entity.model.CatModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.CatEntity;

public class CatTailModel<T extends CatEntity> extends CatModel<T> {

    private final ModelRenderer tail;
    private final ModelRenderer[] tailParts;

    public CatTailModel(float scale) {

        super(scale);

        this.tail = OcelotTailModel.getTail(this, scale);
        CatTailElement element = (CatTailElement) BetterAnimationsCollection.CURLY_CAT_TAIL;
        this.tailParts = OcelotTailModel.getTailParts(this, element.tailLength, scale);
        this.tail.addChild(this.tailParts[0]);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {

        return ImmutableList.of(this.body, this.backLegL, this.backLegR, this.frontLegL, this.frontLegR, this.tail);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        // tail1 is still being updated by super class although unused, so we just copy the angles
        this.tail.y = this.tail1.y;
        this.tail.z = this.tail1.z;
        this.tail.xRot = this.tail1.xRot;

        this.tail.yRot = 0.0F;
        if (entityIn.isInSittingPose()) {

            this.tail.yRot = -1.0F;
            for (int i = 0; i < this.tailParts.length; i++) {

                this.tailParts[i].xRot = 0.0F;
                this.tailParts[i].zRot = (15.0F - (float) i) / 50.0F;
            }
        } else {

            CatTailElement element = (CatTailElement) BetterAnimationsCollection.CURLY_CAT_TAIL;
            OcelotTailModel.setTailAnim(this.tail, this.tailParts, limbSwing, limbSwingAmount, ageInTicks, element.animationSpeed);
        }

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

}
