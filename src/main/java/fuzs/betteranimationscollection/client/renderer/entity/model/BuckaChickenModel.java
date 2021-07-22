package fuzs.betteranimationscollection.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.BuckaChickenElement;
import fuzs.betteranimationscollection.client.util.ModelUtil;
import net.minecraft.client.renderer.entity.model.ChickenModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BuckaChickenModel<T extends Entity> extends ChickenModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer wing0;
    private final ModelRenderer wing1;
    private final ModelRenderer redThing;
    private final ModelRenderer billBottom;

    public BuckaChickenModel() {

        this.head = ModelUtil.getAtIndex(super.headParts().iterator(), 0);
        this.body = ModelUtil.getAtIndex(super.bodyParts().iterator(), 0);
        this.leg0 = ModelUtil.getAtIndex(super.bodyParts().iterator(), 1);
        this.leg1 = ModelUtil.getAtIndex(super.bodyParts().iterator(), 2);
        this.wing0 = ModelUtil.getAtIndex(super.bodyParts().iterator(), 3);
        this.wing1 = ModelUtil.getAtIndex(super.bodyParts().iterator(), 4);

        ModelRenderer billTop = new ModelRenderer(this, 14, 0);
        billTop.addBox(-2.0F, -4.0F, -4.0F, 4, 1, 2, 0.0F);
        this.head.addChild(billTop);
        this.billBottom = new ModelRenderer(this, 14, 1);
        this.billBottom.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 2, 0.0F);
        this.billBottom.setPos(0.0F, -3.0F, -2.0F);
        billTop.addChild(this.billBottom);
        this.redThing = new ModelRenderer(this, 14, 4);
        this.redThing.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.redThing.setPos(0.0F, 1.0F, 0.0F);
        this.billBottom.addChild(this.redThing);

        // fix rotation point to be at body and not in air
        this.wing0.setPos(3.0F, 13.0F, 0.0F);
        this.wing1.setPos(-3.0F, 13.0F, 0.0F);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {

        // other head parts are added as children to this one in constructor
        return ImmutableList.of(this.head);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        // copied from super class as we fully need to override the method
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.body.xRot = ((float) Math.PI / 2F);
        this.leg0.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        BuckaChickenElement element = (BuckaChickenElement) BetterAnimationsCollection.BUCKA_BUCKA_CHICKEN;
        // ageInTicks is flapSpeed here
        if (ageInTicks == 0 && element.moveWings) {
            
            float wingSwingAmount = limbSwingAmount * element.wingAnimationSpeed * 0.1F;
            float wingFlapRot = MathHelper.sin(limbSwing) * wingSwingAmount + wingSwingAmount;
            this.wing0.zRot = -wingFlapRot;
            this.wing1.zRot = wingFlapRot;
        } else {
            
            this.wing0.zRot = -ageInTicks;
            this.wing1.zRot = ageInTicks;
        }
        
        if (element.moveHead) {
            
            this.head.z = -4.0F + MathHelper.cos(limbSwing) * element.headAnimationSpeed * 0.5F * limbSwingAmount;
        }
        
        if (element.moveChin) {
            
            this.redThing.xRot = MathHelper.sin(limbSwing) * (float) element.chinAnimationSpeed * 0.1F * limbSwingAmount;
            this.redThing.xRot -= this.billBottom.xRot;
        }
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        if (entitylivingbaseIn instanceof MobEntity) {

            // this only works because MobEntity#ambientSoundTime is manually being synced to the client in {@link fuzs.betteranimationscollection.client.element.SyncSoundElement}
            MobEntity entitylivingIn = (MobEntity) entitylivingbaseIn;
            int soundTime = entitylivingIn.ambientSoundTime + entitylivingIn.getAmbientSoundInterval();
            if (0 < soundTime && soundTime < 8) {

                float rotation = Math.abs(MathHelper.sin((float) soundTime * (float) Math.PI / 5.0F));
                this.billBottom.xRot = rotation * 0.75F;
            } else {

                this.billBottom.xRot = 0.0F;
            }
        }
    }

}