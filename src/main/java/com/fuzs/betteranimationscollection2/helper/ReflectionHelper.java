package com.fuzs.betteranimationscollection2.helper;

import com.fuzs.betteranimationscollection2.BetterAnimationsCollection2;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ReflectionHelper {

    public static final String SNOWMANMODEL_RIGHTHAND = "field_78192_d";
    public static final String SNOWMANMODEL_LEFTHAND = "field_78193_e";

    private static final String LIVINGRENDERER_ENTITYMODEL = "field_77045_g";

    @SuppressWarnings("unchecked")
    public static <T extends EntityModel> RendererModel getModelPart(T instance, String field) {

        try {

            return ObfuscationReflectionHelper.getPrivateValue((Class<? super T>) instance.getClass().getSuperclass(), instance, field);

        } catch (Exception e) {

            BetterAnimationsCollection2.LOGGER.error("getModelPart() failed for " + instance.getClass().getSuperclass().getSimpleName(), e);

        }

        return null;

    }

    public static <T extends EntityModel> void setModel(LivingRenderer instance, T model) {

        try {

            ObfuscationReflectionHelper.setPrivateValue(getLivingRenderer(instance), instance, model, LIVINGRENDERER_ENTITYMODEL);

        } catch (Exception e) {

            BetterAnimationsCollection2.LOGGER.error("setModelPart() failed for " + instance.getClass().getSimpleName(), e);

        }

    }

    @SuppressWarnings("unchecked")
    private static Class<LivingRenderer> getLivingRenderer(LivingRenderer instance) {

        Class<?> clazz = instance.getClass();

        while (clazz != null && clazz != LivingRenderer.class) {

            clazz = clazz.getSuperclass();

        }

        return (Class<LivingRenderer>) clazz;

    }

}
