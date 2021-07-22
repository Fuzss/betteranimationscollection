package fuzs.betteranimationscollection.client.renderer.entity.model;

import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.JigglySlimeElement;
import fuzs.betteranimationscollection.client.util.ModelUtil;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.math.MathHelper;

public class JigglySlimeModel<T extends Entity> extends SlimeModel<T> {

    private final ModelRenderer cube;
    private final ModelRenderer eye0;
    private final ModelRenderer eye1;
    private final ModelRenderer mouth;

    private final boolean isSlimeGelLayer;
    
    public JigglySlimeModel(int slimeBodyTexOffY) {
        
        super(slimeBodyTexOffY);
        this.cube = ModelUtil.getAtIndex(super.parts().iterator(), 0);
        this.eye0 = ModelUtil.getAtIndex(super.parts().iterator(), 1);
        this.eye1 = ModelUtil.getAtIndex(super.parts().iterator(), 2);
        this.mouth = ModelUtil.getAtIndex(super.parts().iterator(), 3);
        this.isSlimeGelLayer = slimeBodyTexOffY == 0;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        if (entityIn instanceof SlimeEntity) {

            JigglySlimeElement element = (JigglySlimeElement) BetterAnimationsCollection.JIGGLY_LIQUIDY_SLIME;
            float progress = limbSwing + ageInTicks * element.squishiness / 15.0F;
            float squishAmount = ((SlimeEntity) entityIn).targetSquish;
            float magnitude = element.squishiness / 20.0F;
            if (squishAmount < 0.0F) {
                
                magnitude += -squishAmount * 0.5F;
            }

            if (this.isSlimeGelLayer) {

                this.cube.x = MathHelper.sin(progress * 0.3F) * magnitude * 0.5F;
                this.cube.y = MathHelper.sin(progress * 0.33F) * magnitude * 0.5F;
                this.cube.z = MathHelper.sin(progress * 0.375F) * magnitude * 0.25F;
            } else {

                this.eye0.x = MathHelper.sin(progress * 0.5F + 0.5F) * magnitude - 0.125F;
                this.eye0.y = MathHelper.sin(progress * 0.45F + 1.5F) * magnitude;
                this.eye0.z = MathHelper.sin(progress * 0.475F + 2.5F) * magnitude * 0.25F;
                this.eye1.x = MathHelper.sin(progress * 0.525F + 1.0F) * magnitude + 0.125F;
                this.eye1.y = MathHelper.sin(progress * 0.475F + 3.0F) * magnitude;
                this.eye1.z = MathHelper.sin(progress * 0.425F + 2.0F) * magnitude * 0.25F;
                this.mouth.x = MathHelper.sin(progress * 0.55F + 3.75F) * magnitude;
                this.mouth.y = MathHelper.sin(progress * 0.625F + 1.75F) * magnitude;
                this.mouth.z = MathHelper.sin(progress * 0.6F + 2.75F) * magnitude * 0.25F;
                this.cube.x = MathHelper.sin(progress * 0.4F) * magnitude;
                this.cube.y = MathHelper.sin(progress * 0.44F) * magnitude;
                this.cube.z = MathHelper.sin(progress * 0.475F) * magnitude * 0.5F;
            }
        }
    }
    
}
