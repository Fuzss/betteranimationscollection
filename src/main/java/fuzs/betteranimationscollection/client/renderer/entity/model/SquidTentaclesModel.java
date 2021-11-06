package fuzs.betteranimationscollection.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.SquidTentaclesElement;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SquidTentaclesModel<T extends Entity> extends SquidModel<T> {

    private final ModelRenderer[] tentaclesStart = new ModelRenderer[8];
    private final ModelRenderer[][] tentaclesExtension = new ModelRenderer[8][];
    private final ImmutableList<ModelRenderer> parts;

    public SquidTentaclesModel() {

        ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
        // get head from vanilla model
        builder.add(Iterables.get(super.parts(), 0));
        for (int j = 0; j < this.tentaclesStart.length; j++) {

            this.tentaclesStart[j] = new ModelRenderer(this, 48, 0);
            this.tentaclesStart[j].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
            double swingAmount = j * Math.PI * 2.0D / (double) this.tentaclesStart.length;
            this.tentaclesStart[j].x = (float) Math.cos(swingAmount) * 5.0F;
            this.tentaclesStart[j].z = (float) Math.sin(swingAmount) * 5.0F;
            this.tentaclesStart[j].y = 15.0F;
            this.tentaclesStart[j].yRot = (float) (j * Math.PI * -2.0D / (double) this.tentaclesStart.length + (Math.PI / 2D));
            builder.add(this.tentaclesStart[j]);

            SquidTentaclesElement element = (SquidTentaclesElement) BetterAnimationsCollection.SQUIGGLY_SQUID_TENTACLES;
            this.tentaclesExtension[j] = new ModelRenderer[element.tentaclesLength];
            for (int k = 0; k < this.tentaclesExtension[0].length; ++k) {

                this.tentaclesExtension[j][k] = new ModelRenderer(this, 48, 2 + 2 * k);
                this.tentaclesExtension[j][k].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
                this.tentaclesExtension[j][k].y = 2.0F;
                if (k == 0) {

                    this.tentaclesStart[j].addChild(this.tentaclesExtension[j][k]);
                } else {

                    this.tentaclesExtension[j][k - 1].addChild(this.tentaclesExtension[j][k]);
                }
            }
        }

        this.parts = builder.build();
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        if (entityIn instanceof MobEntity) {

            MobEntity entity = (MobEntity) entityIn;
            float progress = ageInTicks / 1.75F;
            float magnitude = MathHelper.sqrt(Math.abs(entity.getDeltaMovement().x) + Math.abs(entity.getDeltaMovement().y) + Math.abs(entity.getDeltaMovement().z)) - 0.075F;
            magnitude *= 0.375F;
            if (magnitude < 0.0F || !entity.isInWater()) {

                magnitude = 0.0F;
            }

            for (ModelRenderer modelrenderer : this.tentaclesStart) {

                modelrenderer.xRot = ageInTicks * 2.0F;
            }

            for (int i = 0; i < this.tentaclesStart.length; i++) {

                this.tentaclesStart[i].xRot += (float) Math.sin(progress) * magnitude;
                for (int j = 0; j < this.tentaclesExtension[0].length; j++) {

                    this.tentaclesExtension[i][j].xRot = -ageInTicks * 0.375F + (float) Math.sin(progress + (float) (j + 1)) * magnitude;
                }
            }
        }
    }

    @Override
    public Iterable<ModelRenderer> parts() {

        return this.parts;
    }

}
