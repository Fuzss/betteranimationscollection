package fuzs.betteranimationscollection.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import fuzs.betteranimationscollection.client.util.ModelUtil;
import net.minecraft.client.renderer.entity.model.IronGolemModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IronGolemNoseModel<T extends IronGolemEntity> extends IronGolemModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer nose;
    private final ModelRenderer body = ModelUtil.getAtIndex(super.parts().iterator(), 1);
    private final ModelRenderer arm0 = ModelUtil.getAtIndex(super.parts().iterator(), 4);
    private final ModelRenderer arm1 = ModelUtil.getAtIndex(super.parts().iterator(), 5);
    private final ModelRenderer leg0 = ModelUtil.getAtIndex(super.parts().iterator(), 2);
    private final ModelRenderer leg1 = ModelUtil.getAtIndex(super.parts().iterator(), 3);

    public IronGolemNoseModel() {

        this.head = new ModelRenderer(this).setTexSize(128, 128);
        this.head.setPos(0.0F, -7.0F, -2.0F);
        this.head.texOffs(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8);

        // separate nose from head model
        this.nose = new ModelRenderer(this).setTexSize(128, 128);
        this.nose.setPos(0.0F, -4.0F, 0.0F);
        this.nose.texOffs(24, 0).addBox(-1.0F, -1.0F, -7.5F, 2, 4, 2);
        this.head.addChild(this.nose);
    }

    @Override
    public Iterable<ModelRenderer> parts() {

        // we need to replace the head, so we also need access to everything else
        return ImmutableList.of(this.head, this.body, this.leg0, this.leg1, this.arm0, this.arm1);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        super.prepareMobModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);

        // this only works because MobEntity#ambientSoundTime is manually being synced to the client in {@link fuzs.betteranimationscollection.client.element.SyncSoundElement}
        int soundTime = entitylivingbaseIn.ambientSoundTime + entitylivingbaseIn.getAmbientSoundInterval();
        final int maxSoundTime = 20;
        if (0 < soundTime && soundTime < maxSoundTime) {

            float rotation = MathHelper.sin((float) soundTime * (float) ((3.0F * Math.PI) / 20.0F));
            this.nose.zRot = rotation * 0.75F * ((float) (maxSoundTime - soundTime) / 20.0F);
        } else {

            this.nose.zRot = 0.0F;
        }
    }
    
}
