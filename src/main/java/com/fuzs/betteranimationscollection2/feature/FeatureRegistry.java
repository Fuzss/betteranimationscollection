package com.fuzs.betteranimationscollection2.feature;

import java.util.ArrayList;
import java.util.List;

public class FeatureRegistry {

    public static final List<Feature> REGISTRY = new ArrayList<>();

    public static void populate() {

        registerFeature(FeatureSlime.class);
        registerFeature(FeatureOcelot.class);
        registerFeature(FeatureCow.class);
        registerFeature(FeatureMooshroom.class);
        registerFeature(FeatureCreeper.class);
        registerFeature(FeatureEnderman.class);
        registerFeature(FeatureSheep.class);
        registerFeature(FeatureMagmaCube.class);
        registerFeature(FeatureGhast.class);
        registerFeature(FeaturePig.class);
        //registerFeature(FeatureWolf.class);
        registerFeature(FeatureVillager.class);
        registerFeature(FeatureIronGolem.class);
        registerFeature(FeatureSnowman.class);
        registerFeature(FeatureSpider.class);
        registerFeature(FeatureCaveSpider.class);
        registerFeature(FeatureSquid.class);

    }

    private static void registerFeature(Class<? extends Feature> feature) {

        if (feature != null) {

            try {
                REGISTRY.add(feature.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

}
