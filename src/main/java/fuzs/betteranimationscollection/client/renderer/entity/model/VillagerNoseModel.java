package fuzs.betteranimationscollection.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class VillagerNoseModel<T extends Entity> extends VillagerModel<T> {

    public VillagerNoseModel() {

        super(0.0F);
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        if (entitylivingbaseIn instanceof MobEntity) {

            MobEntity entitylivingIn = (MobEntity) entitylivingbaseIn;
            // this only works because MobEntity#ambientSoundTime is manually being synced to the client in {@link fuzs.betteranimationscollection.client.element.SyncSoundElement}
            int soundTime = entitylivingIn.ambientSoundTime + entitylivingIn.getAmbientSoundInterval();
            final int maxSoundTime = 20;
            if (0 < soundTime && soundTime < maxSoundTime) {

                float rotation = MathHelper.sin((float) soundTime * (float) ((3.0F * Math.PI) / 20.0F));
                this.nose.zRot = rotation * 0.75F * ((float) (maxSoundTime - soundTime) / 20.0F);
            } else {

                this.nose.zRot = 0.0F;
            }
        }
    }

}
