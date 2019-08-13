package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.model.GhastTentaclesModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class GhastTentaclesRenderer extends MobRenderer<GhastEntity, GhastTentaclesModel<GhastEntity>> {
    private static final ResourceLocation GHAST_TEXTURES = new ResourceLocation("textures/entity/ghast/ghast.png");
    private static final ResourceLocation GHAST_SHOOTING_TEXTURES = new ResourceLocation("textures/entity/ghast/ghast_shooting.png");

    public GhastTentaclesRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GhastTentaclesModel<>(), 1.5F);
    }

    protected ResourceLocation getEntityTexture(@Nonnull GhastEntity entity) {
        return entity.isAttacking() ? GHAST_SHOOTING_TEXTURES : GHAST_TEXTURES;
    }

    protected void preRenderCallback(GhastEntity entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scalef(4.5F, 4.5F, 4.5F);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}