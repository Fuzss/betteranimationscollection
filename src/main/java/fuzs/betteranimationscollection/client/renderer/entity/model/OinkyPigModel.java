package fuzs.betteranimationscollection.client.renderer.entity.model;

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

    public OinkyPigModel() {

        this(0.0F);
    }

    public OinkyPigModel(float scale) {

        super(scale);

        // overwrite normal head as it already includes the snout
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, scale);
        this.head.setPos(0.0F, (float) (18 - 6), -6.0F);

        // make snout a separate part
        this.snout = new ModelRenderer(this, 16, 16);
        this.snout.addBox(-2.0F, -3.0F, -1.0F, 4, 3, 1, scale);
        this.snout.setPos(0.0F, 3.0F, -8.0F);
        this.head.addChild(this.snout);
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        if (entitylivingbaseIn instanceof MobEntity) {

            // this only works because MobEntity#livingSoundTime is manually being synced to the client in {@link fuzs.betteranimationscollection.client.element.SyncSoundElement}
            MobEntity entitylivingIn = (MobEntity) entitylivingbaseIn;
            int soundTime = entitylivingIn.ambientSoundTime + entitylivingIn.getAmbientSoundInterval();
            if (0 < soundTime && soundTime < 8) {

                float rotate = MathHelper.sin((float) soundTime * (float) (Math.PI / 8.0F));
                this.snout.y = 3.0F - rotate;
            } else {

                this.snout.y = 3.0F;
            }
        }
    }

}