package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.model.MagmaCubeBurgerModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class MagmaCubeBurgerRenderer extends MobRenderer<MagmaCubeEntity, MagmaCubeBurgerModel<MagmaCubeEntity>> {
    private static final ResourceLocation MAGMA_CUBE_TEXTURES = new ResourceLocation("textures/entity/slime/magmacube.png");

    public MagmaCubeBurgerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MagmaCubeBurgerModel<>(), 0.25F);
    }

    protected ResourceLocation getEntityTexture(@Nonnull MagmaCubeEntity entity) {
        return MAGMA_CUBE_TEXTURES;
    }

    protected void preRenderCallback(MagmaCubeEntity entitylivingbaseIn, float partialTickTime) {
        int i = entitylivingbaseIn.getSlimeSize();
        float f = MathHelper.lerp(partialTickTime, entitylivingbaseIn.prevSquishFactor, entitylivingbaseIn.squishFactor) / ((float)i * 0.5F + 1.0F);
        float f1 = 1.0F / (f + 1.0F);
        GlStateManager.scalef(f1 * (float)i, 1.0F / f1 * (float)i, f1 * (float)i);
    }
}