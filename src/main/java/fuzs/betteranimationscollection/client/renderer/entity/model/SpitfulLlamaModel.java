package fuzs.betteranimationscollection.client.renderer.entity.model;

import fuzs.betteranimationscollection.mixin.client.accessor.LlamaModelAccessor;
import net.minecraft.client.renderer.entity.model.LlamaModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity;
import net.minecraft.util.math.MathHelper;

public class SpitfulLlamaModel<T extends AbstractChestedHorseEntity> extends LlamaModel<T> {

    private final ModelRenderer mouth;
    
    public SpitfulLlamaModel(float scaleIn) {
        
        super(scaleIn);

        ModelRenderer head = new ModelRenderer(this, 0, 0);
        head.addBox(-2.0F, -14.0F, -10.0F, 4.0F, 2.0F, 9.0F, scaleIn);
        head.setPos(0.0F, 7.0F, -6.0F);
        this.mouth = new ModelRenderer(this, 0, 2);
        this.mouth.addBox(-2.0F, 0.0F, -4.0F, 4.0F, 2.0F, 9.0F, scaleIn);
        this.mouth.setPos(0.0F, -12.0F, -6.0F);
        head.addChild(this.mouth);
        head.texOffs(0, 14).addBox(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, scaleIn);
        head.texOffs(17, 0).addBox(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, scaleIn);
        head.texOffs(17, 0).addBox(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, scaleIn);

        ((LlamaModelAccessor) this).setHead(head);
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        // this only works because MobEntity#ambientSoundTime is manually being synced to the client in {@link fuzs.betteranimationscollection.client.element.SyncSoundElement}
        int soundTime = entitylivingbaseIn.ambientSoundTime + entitylivingbaseIn.getAmbientSoundInterval();
        if (0 < soundTime && soundTime < 5) {

            float rotation = Math.abs(MathHelper.sin((float) soundTime * (float) Math.PI / 5.0F));
            this.mouth.xRot = rotation * 0.75F;
        } else {

            this.mouth.xRot = 0.0F;
        }
    }
    
}
