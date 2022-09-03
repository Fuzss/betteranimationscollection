package fuzs.betteranimationscollection.client.model;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.Zombie;

public class ZombieKneesModel<T extends Zombie> extends ZombieModel<T> {
    private final ModelPart rightShin;
    private final ModelPart leftShin;
    
    public ZombieKneesModel(ModelPart modelPart) {
        super(modelPart);
        ModelPart rightLeg = modelPart.getChild("right_leg");
        this.rightShin = rightLeg.getChild("right_shin");
        ModelPart leftLeg = modelPart.getChild("left_leg");
        this.leftShin = leftLeg.getChild("left_shin");
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        HumanoidKneesModel.setupKneeAnim(entityIn, limbSwing, limbSwingAmount, this, this.rightShin, this.leftShin);
    }
}
