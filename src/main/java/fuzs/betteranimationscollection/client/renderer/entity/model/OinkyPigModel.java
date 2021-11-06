package fuzs.betteranimationscollection.client.renderer.entity.model;

import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.OinkyPigElement;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OinkyPigModel<T extends Entity> extends PigModel<T> {

    private final ModelRenderer snout;
    private final ModelRenderer earRight;
    private final ModelRenderer earLeft;

    public OinkyPigModel() {

        this(0.0F);
    }

    public OinkyPigModel(float scale) {

        super(scale);

        // overwrite normal head as it already includes the snout
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, scale);
        this.head.setPos(0.0F, 12.0F, -6.0F);

        // make snout a separate part
        this.snout = new ModelRenderer(this, 16, 16);
        this.snout.addBox(-2.0F, -3.0F, -1.0F, 4, 3, 1, scale);
        this.snout.setPos(0.0F, 3.0F, -8.0F);
        this.head.addChild(this.snout);

        this.earRight = new ModelRenderer(this, 16, 4);
        this.earRight.addBox(0.0F, 0.0F, -2.0F, 1.0F, 5.0F, 4.0F, scale);
        this.earRight.setPos(3.5F, -2.0F, -4.0F);
        this.head.addChild(this.earRight);
        this.earLeft = new ModelRenderer(this, 16, 4);
        this.earLeft.addBox(0.0F, 0.0F, -2.0F, 1.0F, 5.0F, 4.0F, scale);
        this.earLeft.setPos(-3.5F, -2.0F, -4.0F);
        this.earLeft.yRot = (float) Math.PI;
        this.head.addChild(this.earLeft);
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        if (entitylivingbaseIn instanceof MobEntity) {

            // this only works because MobEntity#ambientSoundTime is manually being synced to the client in {@link fuzs.betteranimationscollection.client.element.SyncSoundElement}
            MobEntity entitylivingIn = (MobEntity) entitylivingbaseIn;
            int soundTime = entitylivingIn.ambientSoundTime + entitylivingIn.getAmbientSoundInterval();
            if (0 < soundTime && soundTime < 8) {

                float rotation = MathHelper.sin((float) soundTime * (float) (Math.PI / 8.0F));
                this.snout.y = 3.0F - rotation;
            } else {

                this.snout.y = 3.0F;
            }
        }
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        OinkyPigElement element = (OinkyPigElement) BetterAnimationsCollection.OINKY_PIG;
        boolean floatyEars = element.floatyEars;
        this.earRight.visible = floatyEars;
        this.earLeft.visible = floatyEars;

        if (floatyEars) {

            float f1 = ageInTicks * 0.1F + limbSwing * 0.5F;
            float f2 = 0.08F + limbSwingAmount * element.earAnimationSpeed * 0.04F;
            this.earRight.zRot = (-(float) Math.PI / 6.0F) - MathHelper.cos(f1 * 1.2F) * f2;
            this.earLeft.zRot = ((float) Math.PI / 6.0F) + MathHelper.cos(f1) * f2;
        }
    }

}