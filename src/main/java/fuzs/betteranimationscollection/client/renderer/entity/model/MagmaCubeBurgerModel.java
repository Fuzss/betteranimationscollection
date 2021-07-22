package fuzs.betteranimationscollection.client.renderer.entity.model;

import fuzs.betteranimationscollection.client.util.ModelUtil;
import net.minecraft.client.renderer.entity.model.MagmaCubeModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.math.MathHelper;

public class MagmaCubeBurgerModel<T extends SlimeEntity> extends MagmaCubeModel<T> {

    private final ModelRenderer insideCube;
    private final ModelRenderer[] bodyCubes;
    
    public MagmaCubeBurgerModel() {

        this.insideCube = ModelUtil.getAtIndex(super.parts().iterator(), 0);
        this.bodyCubes = super.parts().stream().skip(1).toArray(ModelRenderer[]::new);
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

        float deathTime = Math.min(10.0F, entitylivingbaseIn.deathTime);
        this.insideCube.visible = deathTime <= 0.0F;
        float squishFactor = Math.max(0.0F, MathHelper.lerp(partialTickTime, entitylivingbaseIn.oSquish, entitylivingbaseIn.squish));
        for (int i = 0; i < this.bodyCubes.length; i++) {
            
            this.bodyCubes[i].y = (float) (-(4 - i)) * squishFactor * 1.7F;
            this.bodyCubes[i].y += (float) (10 - i) * deathTime * 0.325F;
            this.bodyCubes[i].x = deathTime * 2.0F - (deathTime <= 0.0F ? 0.0F : (float) (7 - i) * 0.675F);
            this.bodyCubes[i].zRot = deathTime * 0.195F;
            this.bodyCubes[i].yRot = (float) (5 * i % 7 - 3) * deathTime * 0.05F;
        }
    }
    
}
