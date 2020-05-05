package com.fuzs.betteranimationscollection2.feature.core;

import com.fuzs.betteranimationscollection2.feature.ChickenFeature;
import com.fuzs.betteranimationscollection2.feature.PigFeature;
import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;

import java.util.List;
import java.util.function.Supplier;

public class Features {

    public static final List<Feature<? extends Entity>> REGISTRY = Lists.newLinkedList();

    private static void populate() {

//        registerFeature(FeatureSlime.class);
//        registerFeature(FeatureOcelot.class);
//        registerFeature(FeatureCow.class);
//        registerFeature(FeatureMooshroom.class);
//        registerFeature(FeatureCreeper.class);
//        registerFeature(FeatureEnderman.class);
//        registerFeature(FeatureSheep.class);
//        registerFeature(FeatureMagmaCube.class);
//        registerFeature(FeatureGhast.class);
        register(PigFeature::new);
//        registerFeature(FeatureWolf.class);
//        registerFeature(FeatureVillager.class);
//        registerFeature(FeatureIronGolem.class);
//        registerFeature(FeatureSnowman.class);
//        registerFeature(FeatureSpider.class);
//        registerFeature(FeatureCaveSpider.class);
//        registerFeature(FeatureSquid.class);
        register(ChickenFeature::new);
//        registerFeature(FeatureZombie.class);
//        registerFeature(FeaturePigZombie.class);
//        registerFeature(FeatureHusk.class);

    }

    @SuppressWarnings("SameParameterValue")
    private static void register(Supplier<? extends Feature<? extends Entity>> feature) {

        Features.REGISTRY.add(feature.get());
    }

    public static void create() {

        populate();

        // sort registry alphabetically
        Features.REGISTRY.sort((it, ti) -> it.getName().compareToIgnoreCase(ti.getName()));

    }

}
