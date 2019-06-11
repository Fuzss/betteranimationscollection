package com.fuzs.betteranimationscollection2.render;

import com.fuzs.betteranimationscollection2.layer.LayerAnimatedSnowmanHeadStick;
import com.fuzs.betteranimationscollection2.model.ModelAnimatedSnowManStick;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSnowmanHead;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAnimatedSnowManStick extends RenderLiving<EntitySnowman>
{
    private static final ResourceLocation SNOW_MAN_TEXTURES = new ResourceLocation("textures/entity/snowman.png");

    public RenderAnimatedSnowManStick(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelAnimatedSnowManStick(), 0.5F);
        this.addLayer(new LayerAnimatedSnowmanHeadStick(this));
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySnowman entity)
    {
        return SNOW_MAN_TEXTURES;
    }

    public ModelAnimatedSnowManStick getMainModel()
    {
        return (ModelAnimatedSnowManStick)super.getMainModel();
    }
}