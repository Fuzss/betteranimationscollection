package fuzs.betteranimationscollection.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.SquidTentaclesElement;
import fuzs.betteranimationscollection.mixin.client.accessor.SquidModelAccessor;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SquidTentaclesModel<T extends Entity> extends SquidModel<T> {

    private final ModelRenderer[] tentacles = new ModelRenderer[8];
    private final ModelRenderer[][] tentacles2 = new ModelRenderer[8][];

    public SquidTentaclesModel() {

        ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
        SquidModelAccessor modelAccessor = (SquidModelAccessor) this;
        builder.add(modelAccessor.getBody());
        for (int j = 0; j < this.tentacles.length; ++j) {

            this.tentacles[j] = new ModelRenderer(this, 48, 0);
            this.tentacles[j].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
            double swingAmount = j * Math.PI * 2.0D / (double) this.tentacles.length;
            this.tentacles[j].x = (float) Math.cos(swingAmount) * 5.0F;
            this.tentacles[j].z = (float) Math.sin(swingAmount) * 5.0F;
            this.tentacles[j].y = 15.0F;
            this.tentacles[j].yRot = (float) (j * Math.PI * -2.0D / (double) this.tentacles.length + (Math.PI / 2D));
            builder.add(this.tentacles[j]);

            SquidTentaclesElement element = (SquidTentaclesElement) BetterAnimationsCollection.SQUID_TENTACLES;
            this.tentacles2[j] = new ModelRenderer[element.length];
            for (int k = 0; k < this.tentacles2[0].length; ++k) {

                this.tentacles2[j][k] = new ModelRenderer(this, 48, 2 + 2 * k);
                this.tentacles2[j][k].addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
                this.tentacles2[j][k].y = 2.0F;
                if (k == 0) {

                    this.tentacles[j].addChild(this.tentacles2[j][k]);
                } else {

                    this.tentacles2[j][k - 1].addChild(this.tentacles2[j][k]);
                }
            }
        }

        modelAccessor.setParts(builder.build());
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

            for (ModelRenderer modelrenderer : this.tentacles) {

                modelrenderer.xRot = ageInTicks * 2.0F;
            }

            for (int i = 0; i < this.tentacles.length; i++) {

                this.tentacles[i].xRot += (float) Math.sin(progress) * magnitude;
                for (int j = 0; j < this.tentacles2[0].length; j++) {

                    this.tentacles2[i][j].xRot = -ageInTicks * 0.375F + (float) Math.sin(progress + (float) (j + 1)) * magnitude;
                }
            }
        }
    }

}
