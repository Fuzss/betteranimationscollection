package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.OinkySaddleLayer;
import com.fuzs.betteranimationscollection2.renderer.model.OinkyPigModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class OinkyPigRenderer extends MobRenderer<PigEntity, OinkyPigModel<PigEntity>> {
    private static final ResourceLocation PIG_TEXTURES = new ResourceLocation("textures/entity/pig/pig.png");

    public OinkyPigRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new OinkyPigModel<>(), 0.7F);
        this.addLayer(new OinkySaddleLayer(this));
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(@Nonnull PigEntity entity) {
        return PIG_TEXTURES;
    }
}