package fuzs.betteranimationscollection.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.OcelotTailElement;
import net.minecraft.client.renderer.entity.model.OcelotModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class OcelotTailModel<T extends Entity> extends OcelotModel<T> {

    private final ModelRenderer tail;
    private final ModelRenderer[] tailParts;

    public OcelotTailModel() {

        super(0.0F);

        this.tail = getTail(this, 0.0F);
        OcelotTailElement element = (OcelotTailElement) BetterAnimationsCollection.FLOWY_OCELOT_TAIL;
        this.tailParts = getTailParts(this, element.length, 0.0F);
        this.tail.addChild(this.tailParts[0]);
    }

    public static ModelRenderer getTail(Model model, float scale) {

        final ModelRenderer tail = new ModelRenderer(model, 0, 15);
        tail.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, scale);
        tail.xRot = 0.9F;
        tail.setPos(0.0F, 15.0F, 8.0F);

        return tail;
    }

    public static ModelRenderer[] getTailParts(Model model, int length, float scale) {

        final ModelRenderer[] tailParts = new ModelRenderer[length];
        for (int i = 0; i < tailParts.length; ++i) {

            if (i < tailParts.length / 2) {

                tailParts[i] = new ModelRenderer(model, 0, 16 + i);
                tailParts[i].addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, scale);
                tailParts[i].setPos(0.0F, 1.0F, 0.0F);
                if (i > 0) {

                    // part at index 0 needs to be added as child of tail part, this is done later
                    tailParts[i - 1].addChild(tailParts[i]);
                }
            } else {

                tailParts[i] = new ModelRenderer(model, 4, 8 + i);
                tailParts[i].addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, scale);
                tailParts[i].setPos(0.0F, 1.0F, 0.0F);
                tailParts[i - 1].addChild(tailParts[i]);
            }
        }

        return tailParts;
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

        OcelotTailElement element = (OcelotTailElement) BetterAnimationsCollection.FLOWY_OCELOT_TAIL;
        OcelotTailModel.setTailAnim(this.tail, this.tailParts, limbSwing, limbSwingAmount, ageInTicks, element.swing);

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    public static void setTailAnim(ModelRenderer tail, ModelRenderer[] tailParts, float limbSwing, float limbSwingAmount, float ageInTicks, int swing) {

        float magnitude = (0.5F + limbSwingAmount) * 0.125F;
        float amplitude = limbSwing * 0.6662F + (ageInTicks / 4.66F) * 0.6662F;
        tail.xRot += MathHelper.sin(amplitude) * magnitude;
        for (int i = 0; i < tailParts.length; i++) {

            tailParts[i].zRot = 0.0F;
            tailParts[i].xRot = 0.05F;
            tailParts[i].xRot += MathHelper.sin(amplitude - (float) (i + 1) * swing * 0.05F) * magnitude;
        }
    }

}
