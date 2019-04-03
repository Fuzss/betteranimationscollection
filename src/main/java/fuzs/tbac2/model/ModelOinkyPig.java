package fuzs.tbac2.model;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelOinkyPig extends ModelQuadruped
{
    private final ModelRenderer snout;

    private int time = 0;

    public ModelOinkyPig()
    {
        this(0.0F);
    }

    public ModelOinkyPig(float scale)
    {
        super(6, scale);
        this.snout = new ModelRenderer(this, 16, 16);
        this.snout.addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1, scale);
        this.childYOffset = 4.0F;
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.snout.render(scale);
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        if (ageInTicks % 100 == 0) {
            time = 8;
        }
        
        float rotate = MathHelper.sin((float)time * 0.3926991F);
        this.snout.rotationPointY = 3.0F - rotate;

        if (time > 0) {
            time -= 1;
        }
    }
}