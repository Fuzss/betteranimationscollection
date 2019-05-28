package com.fuzs.betteranimationscollection2.render;

import com.fuzs.betteranimationscollection2.layer.LayerWavingEndermanEyes;
import com.fuzs.betteranimationscollection2.layer.LayerWavingHeldBlock;
import com.fuzs.betteranimationscollection2.model.ModelWavingEnderman;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class RenderWavingEnderman extends RenderLiving<EntityEnderman>
{
    private static final ResourceLocation ENDERMAN_TEXTURES = new ResourceLocation("textures/entity/enderman/enderman.png");
    private final Random rnd = new Random();

    public RenderWavingEnderman(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelWavingEnderman(0.0F), 0.5F);
        this.addLayer(new LayerWavingEndermanEyes(this));
        this.addLayer(new LayerWavingHeldBlock(this));
    }

    public ModelWavingEnderman getMainModel()
    {
        return (ModelWavingEnderman)super.getMainModel();
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityEnderman entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        IBlockState iblockstate = entity.getHeldBlockState();
        ModelWavingEnderman modelenderman = this.getMainModel();
        modelenderman.isCarrying = iblockstate != null;
        modelenderman.isAttacking = entity.isScreaming();

        if (entity.isScreaming())
        {
            double d0 = 0.02D;
            x += this.rnd.nextGaussian() * 0.02D;
            z += this.rnd.nextGaussian() * 0.02D;
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityEnderman entity)
    {
        return ENDERMAN_TEXTURES;
    }
}