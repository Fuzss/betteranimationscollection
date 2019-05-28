package com.fuzs.betteranimationscollection2.render;

import com.fuzs.betteranimationscollection2.layer.LayerOinkySaddle;
import com.fuzs.betteranimationscollection2.model.ModelOinkyPig;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderOinkyPig extends RenderLiving<EntityPig>
{
    private static final ResourceLocation PIG_TEXTURES = new ResourceLocation("textures/entity/pig/pig.png");

    public RenderOinkyPig(RenderManager p_i47198_1_)
    {
        super(p_i47198_1_, new ModelOinkyPig(), 0.7F);
        this.addLayer(new LayerOinkySaddle(this));
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityPig entity)
    {
        return PIG_TEXTURES;
    }
}