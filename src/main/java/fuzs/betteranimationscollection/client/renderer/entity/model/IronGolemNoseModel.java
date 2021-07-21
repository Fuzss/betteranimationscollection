package fuzs.betteranimationscollection.client.renderer.entity.model;

import fuzs.betteranimationscollection.mixin.client.accessor.IronGolemModelAccessor;
import net.minecraft.client.renderer.entity.model.IronGolemModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IronGolemNoseModel<T extends IronGolemEntity> extends IronGolemModel<T> {

    private final ModelRenderer nose;

    public IronGolemNoseModel() {

        super();

        ModelRenderer head = new ModelRenderer(this).setTexSize(128, 128);
        head.setPos(0.0F, -7.0F, -2.0F);
        head.texOffs(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8);

        // separate nose from head model
        this.nose = new ModelRenderer(this).setTexSize(128, 128);
        this.nose.setPos(0.0F, -4.0F, 0.0F);
        this.nose.texOffs(24, 0).addBox(-1.0F, -1.0F, -7.5F, 2, 4, 2);
        head.addChild(this.nose);

        ((IronGolemModelAccessor) this).setHead(head);
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        super.prepareMobModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);

        // this only works because MobEntity#ambientSoundTime is manually being synced to the client in {@link fuzs.betteranimationscollection.client.element.SyncSoundElement}
        int soundTime = entitylivingbaseIn.ambientSoundTime + entitylivingbaseIn.getAmbientSoundInterval();
        if (0 < soundTime && soundTime < 20) {

            float rotation = MathHelper.sin((float)soundTime * (float)((3.0F * Math.PI) / 20.0F));
            this.nose.zRot = rotation * 0.75F * ((float)(20 - soundTime) / 20.0F);
        } else {

            this.nose.zRot = 0.0F;
        }

    }
    
}
