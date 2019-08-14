package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureMooshroom;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MooshroomUdderModel<T extends Entity> extends CowUdderModel<T> {

    @Override
    protected boolean hasNipples() {
        return FeatureMooshroom.nipples.get();
    }

    @Override
    protected boolean hasChildUdder() {
        return FeatureMooshroom.utterChild.get();
    }

    @Override
    protected int getSwing() {
        return FeatureMooshroom.swing.get();
    }

}