package com.fuzs.betteranimationscollection2.helper;

import com.fuzs.betteranimationscollection2.BetterAnimationsCollection2;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ReflectionHelper {

    // snowman
    public static final String SNOWMANMODEL_RIGHTHAND = "field_78192_d";
    public static final String SNOWMANMODEL_LEFTHAND = "field_78193_e";
    // spider
    public static final String SPIDERMODEL_SPIDERLEG1 = "field_78205_d";
    public static final String SPIDERMODEL_SPIDERLEG2 = "field_78206_e";
    public static final String SPIDERMODEL_SPIDERLEG3 = "field_78203_f";
    public static final String SPIDERMODEL_SPIDERLEG4 = "field_78204_g";
    public static final String SPIDERMODEL_SPIDERLEG5 = "field_78212_h";
    public static final String SPIDERMODEL_SPIDERLEG6 = "field_78213_i";
    public static final String SPIDERMODEL_SPIDERLEG7 = "field_78210_j";
    public static final String SPIDERMODEL_SPIDERLEG8 = "field_78211_k";
    // ghast
    public static final String GHASTMODEL_TENTACLES = "field_78127_b";
    // chicken
    public static final String CHICKENMODEL_HEAD = "field_78142_a";
    public static final String CHICKENMODEL_BODY = "field_78140_b";
    public static final String CHICKENMODEL_RIGHTLEG = "field_78141_c";
    public static final String CHICKENMODEL_LEFTLEG = "field_78138_d";
    // magmacube
    public static final String MAGMACUBEMODEL_SEGMENTS = "field_78109_a";
    public static final String MAGMACUBEMODEL_CORE = "field_78108_b";
    // slime
    public static final String SLIMEMODEL_BODY = "field_78200_a";
    public static final String SLIMEMODEL_RIGHTEYE = "field_78198_b";
    public static final String SLIMEMODEL_LEFTEYE = "field_78199_c";
    public static final String SLIMEMODEL_MOUTH = "field_78197_d";
    // iron golem
    public static final String IRONGOLEMMODEL_HEAD = "field_78178_a";
    // squid
    public static final String SQUIDMODEL_TENTACLES = "field_78201_b";

    private static final String LIVINGRENDERER_ENTITYMODEL = "field_77045_g";

    @SuppressWarnings("unchecked")
    public static <T extends EntityModel> Object getModelPart(T instance, String field) {

        try {

            return ObfuscationReflectionHelper.getPrivateValue((Class<? super T>) instance.getClass().getSuperclass(), instance, field);

        } catch (Exception e) {

            BetterAnimationsCollection2.LOGGER.error("getModelPart() failed for " + instance.getClass().getSuperclass().getSimpleName(), e);

        }

        return null;

    }

    @SuppressWarnings("unchecked")
    public static <T extends EntityModel> void setModelPart(T instance, Object rendererModel, String field) {

        try {

            ObfuscationReflectionHelper.setPrivateValue((Class<? super T>) instance.getClass().getSuperclass(), instance, rendererModel, field);

        } catch (Exception e) {

            BetterAnimationsCollection2.LOGGER.error("setModelPart() failed for " + instance.getClass().getSuperclass().getSimpleName(), e);

        }

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
