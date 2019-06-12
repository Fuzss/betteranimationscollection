package com.fuzs.betteranimationscollection2.renderer.model;

import com.fuzs.betteranimationscollection2.feature.FeatureMooshroom;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMooshroomUdder extends ModelCowUdder
{
    @Override
    protected boolean hasNipples() {
        return FeatureMooshroom.nipples;
    }

    @Override
    protected boolean hasChildUdder() {
        return FeatureMooshroom.utterChild;
    }

    @Override
    protected int getSwing() {
        return FeatureMooshroom.swing;
    }
}