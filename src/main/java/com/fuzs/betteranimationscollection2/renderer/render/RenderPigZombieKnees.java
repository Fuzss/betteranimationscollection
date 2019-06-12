package com.fuzs.betteranimationscollection2.renderer.render;

import com.fuzs.betteranimationscollection2.renderer.model.ModelZombieKnees;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPigZombieKnees extends RenderBiped<EntityPigZombie>
{
    private static final ResourceLocation ZOMBIE_PIGMAN_TEXTURE = new ResourceLocation("textures/entity/zombie_pigman.png");

    public RenderPigZombieKnees(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelZombieKnees(), 0.5F);
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombieKnees(0.5F, true);
                this.modelArmor = new ModelZombieKnees(1.0F, true);
            }
        });
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityPigZombie entity)
    {
        return ZOMBIE_PIGMAN_TEXTURE;
    }
}