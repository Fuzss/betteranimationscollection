package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.LayerMooshroomUdderMushroom;
import com.fuzs.betteranimationscollection2.renderer.model.ModelMooshroomUdder;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMooshroomUdder extends RenderLiving<EntityMooshroom>
{
    private static final ResourceLocation MOOSHROOM_TEXTURES = new ResourceLocation("textures/entity/cow/mooshroom.png");

    public RenderMooshroomUdder(RenderManager p_i47200_1_)
    {
        super(p_i47200_1_, new ModelMooshroomUdder(), 0.7F);
        this.addLayer(new LayerMooshroomUdderMushroom(this));
    }

    public ModelMooshroomUdder getMainModel()
    {
        return (ModelMooshroomUdder) super.getMainModel();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityMooshroom entity)
    {
        return MOOSHROOM_TEXTURES;
    }
}