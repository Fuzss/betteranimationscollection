package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.model.ModelZombieKnees;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZombieKnees extends RenderBiped<EntityZombie>
{
    private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");

    public RenderZombieKnees(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelZombieKnees(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombieKnees(0.5F, true);
                this.modelArmor = new ModelZombieKnees(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityZombie entity)
    {
        return ZOMBIE_TEXTURES;
    }
}