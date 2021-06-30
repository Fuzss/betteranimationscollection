package fuzs.betteranimationscollection.client.renderer.entity.model;

import fuzs.betteranimationscollection.mixin.client.accessor.ISnowManModelAccessor;
import net.minecraft.client.renderer.entity.model.SnowManModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnimatedSnowManStickModel<T extends Entity> extends SnowManModel<T> {
    
    private final ModelRenderer arm1;
    private final ModelRenderer arm2;

    public AnimatedSnowManStickModel() {
        
        this.arm1 = ((ISnowManModelAccessor) this).getArm1();
        this.arm2 = ((ISnowManModelAccessor) this).getArm2();
    }
    
    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        if (entityIn instanceof MobEntity) {

            // this only works because MobEntity#livingSoundTime is manually being synced to the client in {@link fuzs.betteranimationscollection.client.element.SyncSoundElement}
            MobEntity entitylivingIn = (MobEntity) entityIn;
            int soundTime = entitylivingIn.ambientSoundTime + entitylivingIn.getAmbientSoundInterval();

            // makes 5 % of snowman render left handed, like for most mobs with arms in vanilla
            boolean leftHanded = Math.abs(entitylivingIn.getUUID().getLeastSignificantBits() % 20) == 0;
            if (0 < soundTime && soundTime < 8) {

                if (leftHanded) {

                    this.arm1.xRot = 1.5F - (float) soundTime * 1.5F / 8.0F;
                    this.arm1.yRot += (1.0F - (float) soundTime / 8.0F) * 2.0F;
                } else {

                    this.arm2.xRot = 1.5F - (float) soundTime * 1.5F / 8.0F;
                    this.arm2.yRot -= (1.0F - (float) soundTime / 8.0F) * 2.0F;
                }
            } else {

                this.arm1.xRot = 0.0F;
                this.arm2.xRot = 0.0F;
            }
        }
    }
    
}
