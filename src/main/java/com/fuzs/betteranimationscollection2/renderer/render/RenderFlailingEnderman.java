package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.LayerFlailingEndermanEyes;
import com.fuzs.betteranimationscollection2.renderer.layer.LayerFlailingHeldBlock;
import com.fuzs.betteranimationscollection2.renderer.model.ModelFlailingEnderman;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class RenderFlailingEnderman extends RenderLiving<EntityEnderman>
{
    private static final ResourceLocation ENDERMAN_TEXTURES = new ResourceLocation("textures/entity/enderman/enderman.png");
    private final Random rnd = new Random();

    public RenderFlailingEnderman(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelFlailingEnderman(0.0F), 0.5F);
        this.addLayer(new LayerFlailingEndermanEyes(this));
        this.addLayer(new LayerFlailingHeldBlock(this));
    }

    public ModelFlailingEnderman getMainModel()
    {
        return (ModelFlailingEnderman)super.getMainModel();
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityEnderman entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        IBlockState iblockstate = entity.getHeldBlockState();
        ModelFlailingEnderman modelenderman = this.getMainModel();
        modelenderman.isCarrying = iblockstate != null;
        modelenderman.isAttacking = entity.isScreaming();

        if (entity.isScreaming())
        {
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