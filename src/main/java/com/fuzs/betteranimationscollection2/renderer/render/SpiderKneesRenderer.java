package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.SpiderKneesEyesLayer;
import com.fuzs.betteranimationscollection2.renderer.model.SpiderKneesModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class SpiderKneesRenderer<T extends SpiderEntity> extends MobRenderer<T, SpiderKneesModel<T>>
{
    private static final ResourceLocation SPIDER_TEXTURES = new ResourceLocation("textures/entity/spider/spider.png");

    public SpiderKneesRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new SpiderKneesModel<>(), 1.0F);
        this.addLayer(new SpiderKneesEyesLayer<>(this));
    }

    protected float getDeathMaxRotation(T entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(@Nonnull T entity)
    {
        return SPIDER_TEXTURES;
    }
}