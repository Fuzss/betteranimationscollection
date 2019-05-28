package com.fuzs.betteranimationscollection2.layer;

import com.fuzs.betteranimationscollection2.model.ModelOinkyPig;
import com.fuzs.betteranimationscollection2.render.RenderOinkyPig;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerOinkySaddle implements LayerRenderer<EntityPig>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");
    private final RenderOinkyPig pigRenderer;
    private final ModelOinkyPig pigModel = new ModelOinkyPig(0.5F);

    public LayerOinkySaddle(RenderOinkyPig pigRendererIn)
    {
        this.pigRenderer = pigRendererIn;
    }

    public void doRenderLayer(EntityPig entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entitylivingbaseIn.getSaddled())
        {
            this.pigRenderer.bindTexture(TEXTURE);
            this.pigModel.setModelAttributes(this.pigRenderer.getMainModel());
            this.pigModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}