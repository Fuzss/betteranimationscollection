package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.layer.JigglySlimeGelLayer;
import com.fuzs.betteranimationscollection2.renderer.model.JigglySlimeModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeGelLayer;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class JigglySlimeRenderer extends MobRenderer<SlimeEntity, JigglySlimeModel<SlimeEntity>> {
    private static final ResourceLocation SLIME_TEXTURES = new ResourceLocation("textures/entity/slime/slime.png");

    public JigglySlimeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new JigglySlimeModel<>(16), 0.25F);
        this.addLayer(new JigglySlimeGelLayer<>(this));
    }

    public void doRender(SlimeEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.shadowSize = 0.25F * (float)entity.getSlimeSize();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    protected void preRenderCallback(SlimeEntity entitylivingbaseIn, float partialTickTime) {
        float f = 0.999F;
        GlStateManager.scalef(0.999F, 0.999F, 0.999F);
        float f1 = (float)entitylivingbaseIn.getSlimeSize();
        float f2 = MathHelper.lerp(partialTickTime, entitylivingbaseIn.prevSquishFactor, entitylivingbaseIn.squishFactor) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        GlStateManager.scalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    protected ResourceLocation getEntityTexture(@Nonnull SlimeEntity entity) {
        return SLIME_TEXTURES;
    }
}