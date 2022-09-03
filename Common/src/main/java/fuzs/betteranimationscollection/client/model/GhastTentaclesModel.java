package fuzs.betteranimationscollection.client.model;

import fuzs.betteranimationscollection.client.element.GhastTentaclesElement;
import fuzs.betteranimationscollection.mixin.client.accessor.LayerDefinitionAccessor;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;

public class GhastTentaclesModel<T extends Entity> extends GhastModel<T> {
    private final ModelPart[] tentacles = new ModelPart[9];
    private final ModelPart[][] tentacleParts = new ModelPart[9][];

    public GhastTentaclesModel(ModelPart modelPart) {
        super(modelPart);
        RandomSource randomSource = RandomSource.create(1660L);
        for (int i = 0; i < this.tentacles.length; i++) {
            ModelPart tentacle = this.tentacles[i] = modelPart.getChild("tentacle" + i);
            int randomLength = randomSource.nextInt(GhastTentaclesElement.maxTentaclesLength / 2) + GhastTentaclesElement.maxTentaclesLength / 2 + 1;
            this.tentacleParts[i] = new ModelPart[14];
            for (int j = 0; j < this.tentacleParts[i].length; j++) {
                tentacle = this.tentacleParts[i][j] = tentacle.getChild("tentacle_part" + j);
                // we always create all parts, the config only sets unwanted ones invisible
                if (j >= randomLength) tentacle.visible = false;
            }
        }
    }

    public static LayerDefinition createAnimatedBodyLayer() {
        LayerDefinition layerDefinition = GhastModel.createBodyLayer();
        MeshDefinition meshDefinition = ((LayerDefinitionAccessor) layerDefinition).getMesh();
        PartDefinition partDefinition = meshDefinition.getRoot();
        for (int i = 0; i < 9; ++i) {
            float offsetX = (((float) (i % 3) - (float)(i / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
            float offsetY = 24.6F;
            float offsetZ = ((float) (i / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
            PartDefinition partDefinition1 = partDefinition.addOrReplaceChild("tentacle" + i, CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F), PartPose.offset(offsetX, offsetY, offsetZ));
            for (int j = 0; j < 14; j++) {
                partDefinition1 = partDefinition1.addOrReplaceChild("tentacle_part" + i, CubeListBuilder.create().texOffs(0, 1 + j).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F), PartPose.offset(0.0F, 1.0F, 0.0F));
            }
        }
        return layerDefinition;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0F / (float) GhastTentaclesElement.animationSpeed;
        for (int i = 0; i < this.tentacles.length; i++) {
            this.tentacles[i].xRot = speed * Mth.sin(ageInTicks * speed + (float) i) + 0.4F;
            for (int j = 0; j < this.tentacleParts[i].length; j++) {
                this.tentacleParts[i][j].xRot = speed * Mth.sin(ageInTicks * speed + (float) i - (float) j / 2.0F);
            }
        }
    }
}