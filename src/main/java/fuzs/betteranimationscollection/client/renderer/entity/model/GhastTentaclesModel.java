package fuzs.betteranimationscollection.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.GhastTentaclesElement;
import fuzs.betteranimationscollection.client.util.ModelUtil;
import net.minecraft.client.renderer.entity.model.GhastModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class GhastTentaclesModel<T extends Entity> extends GhastModel<T> {
    
    private final ModelRenderer[] tentacles = new ModelRenderer[9];
    private final ModelRenderer[][] tentacles2 = new ModelRenderer[9][];
    private final ImmutableList<ModelRenderer> parts;

    public GhastTentaclesModel() {

        ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
        // get head from vanilla model
        builder.add(ModelUtil.getAtIndex(super.parts().iterator(), 0));

        Random random = new Random(1660L);
        for (int i = 0; i < this.tentacles.length; i++) {

            this.tentacles[i] = new ModelRenderer(this, 0, 0);
            this.tentacles[i].addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
            this.tentacles[i].x = (((float) (i % 3) - (float)(i / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
            this.tentacles[i].z = ((float) (i / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
            this.tentacles[i].y = 24.6F;
            builder.add(this.tentacles[i]);

            GhastTentaclesElement element = (GhastTentaclesElement) BetterAnimationsCollection.WIGGLY_GHAST_TENTACLES;
            int randomLength = random.nextInt(element.length / 2) + element.length / 2 + 1;
            this.tentacles2[i] = new ModelRenderer[randomLength - 1];

            for (int j = 0; j < this.tentacles2[i].length; j++) {

                this.tentacles2[i][j] = new ModelRenderer(this, 0, 1 + j);
                this.tentacles2[i][j].addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
                this.tentacles2[i][j].y = 1.0F;

                if (j == 0) {

                    this.tentacles[i].addChild(this.tentacles2[i][j]);
                } else {

                    this.tentacles2[i][j - 1].addChild(this.tentacles2[i][j]);
                }
            }
        }

        this.parts = builder.build();
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        GhastTentaclesElement element = (GhastTentaclesElement) BetterAnimationsCollection.WIGGLY_GHAST_TENTACLES;
        float speed = 1.0F / (float) element.speed;
        for (int i = 0; i < this.tentacles.length; i++) {
            
            this.tentacles[i].xRot = speed * MathHelper.sin(ageInTicks * speed + (float) i) + 0.4F;
            for (int j = 0; j < this.tentacles2[i].length; j++) {
                
                this.tentacles2[i][j].xRot = speed * MathHelper.sin(ageInTicks * speed + (float) i - (float) j / 2.0F);
            }
        }
    }

    @Override
    public Iterable<ModelRenderer> parts() {

        return this.parts;
    }

}