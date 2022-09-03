package fuzs.betteranimationscollection.client.model;

import fuzs.betteranimationscollection.client.element.BuckaChickenElement;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

public class BuckaChickenModel<T extends Entity> extends ChickenModel<T> {
    private final ModelPart head;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart redThing;
    private final ModelPart billBottom;

    public BuckaChickenModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
        this.rightWing = modelPart.getChild("right_wing");
        this.leftWing = modelPart.getChild("left_wing");
        ModelPart billTop = this.head.getChild(BuckaChickenElement.slimBill ? "slim_bill_top" : "bill_top");
        this.billBottom = billTop.getChild(BuckaChickenElement.slimBill ? "slim_bill_bottom" : "bill_bottom");
        this.redThing = this.billBottom.getChild("red_thing");
    }

    public static LayerDefinition createAnimatedBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition partDefinition1 = partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
        partDefinition.addOrReplaceChild("beak", CubeListBuilder.create(), PartPose.ZERO);
        partDefinition.addOrReplaceChild("red_thing", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition partDefinition2 = partDefinition1.addOrReplaceChild("bill_top", CubeListBuilder.create().texOffs(15, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F), PartPose.ZERO);
        PartDefinition partDefinition3 = partDefinition2.addOrReplaceChild("bill_bottom", CubeListBuilder.create().texOffs(15, 1).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F), PartPose.offset(0.0F, -3.0F, -2.0F));
        PartDefinition partDefinition22 = partDefinition1.addOrReplaceChild("slim_bill_top", CubeListBuilder.create().texOffs(14, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 2.0F), PartPose.ZERO);
        PartDefinition partDefinition33 = partDefinition22.addOrReplaceChild("slim_bill_bottom", CubeListBuilder.create().texOffs(14, 1).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 2.0F), PartPose.offset(0.0F, -3.0F, -2.0F));
        CubeListBuilder cubeListBuilder = CubeListBuilder.create().texOffs(14, 4).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F);
        PartPose partPose = PartPose.offset(0.0F, 1.0F, 0.0F);
        partDefinition3.addOrReplaceChild("red_thing", cubeListBuilder, partPose);
        partDefinition33.addOrReplaceChild("red_thing", cubeListBuilder, partPose);
        // fix rotation point to be at body and not in air
        partDefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(24, 13).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), PartPose.offset(3.0F, 13.0F, 0.0F));
        partDefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(24, 13).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), PartPose.offset(-3.0F, 13.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        // ageInTicks is flapSpeed here
        if (ageInTicks == 0 && BuckaChickenElement.moveWings) {
            float wingSwingAmount = limbSwingAmount * BuckaChickenElement.wingAnimationSpeed * 0.1F;
            float wingFlapRot = Mth.sin(limbSwing) * wingSwingAmount + wingSwingAmount;
            this.rightWing.zRot = -wingFlapRot;
            this.leftWing.zRot = wingFlapRot;
        } else {
            this.rightWing.zRot = -ageInTicks;
            this.leftWing.zRot = ageInTicks;
        }
        if (BuckaChickenElement.moveHead) {
            this.head.z = -4.0F + Mth.cos(limbSwing) * BuckaChickenElement.headAnimationSpeed * 0.5F * limbSwingAmount;
        }
        if (BuckaChickenElement.moveWattles) {
            this.redThing.xRot = Mth.sin(limbSwing) * (float) BuckaChickenElement.wattlesAnimationSpeed * 0.1F * limbSwingAmount;
        }
    }

    @Override
    public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        if (entitylivingbaseIn instanceof Mob mob) {
            // this only works because MobEntity#ambientSoundTime is manually being synced to the client in {@link fuzs.betteranimationscollection.client.element.SyncSoundElement}
            int soundTime = mob.ambientSoundTime + mob.getAmbientSoundInterval();
            if (0 < soundTime && soundTime < 8) {
                float rotation = Math.abs(Mth.sin((float) soundTime * (float) Math.PI / 5.0F));
                this.billBottom.xRot = rotation * 0.75F;
            } else {
                this.billBottom.xRot = 0.0F;
            }
        }
    }
}