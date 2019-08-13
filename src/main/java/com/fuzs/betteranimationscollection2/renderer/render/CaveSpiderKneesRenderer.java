package com.fuzs.betteranimationscollection2.renderer.render;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class CaveSpiderKneesRenderer extends SpiderKneesRenderer<CaveSpiderEntity>
{
    private static final ResourceLocation CAVE_SPIDER_TEXTURES = new ResourceLocation("textures/entity/spider/cave_spider.png");

    public CaveSpiderKneesRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
        this.shadowSize *= 0.7F;
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(CaveSpiderEntity entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scalef(0.7F, 0.7F, 0.7F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(@Nonnull CaveSpiderEntity entity) {
        return CAVE_SPIDER_TEXTURES;
    }
}