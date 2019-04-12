package fuzs.tbac2.fixes;

import fuzs.tbac2.util.PrivateAccessor;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class ParticleAnimalBreed implements PrivateAccessor {

    @SubscribeEvent
    public void loveMode(LivingEvent.LivingUpdateEvent evt) {
        if (evt.getEntity() instanceof EntityAnimal && !evt.getEntity().world.isRemote) {
            EntityAnimal entityanimal = (EntityAnimal) evt.getEntity();
            WorldServer world = (WorldServer) entityanimal.world;
            int inLove = getInLove(entityanimal);
            if (inLove % 10 == 0 && inLove > 0) {
                Random random = entityanimal.getRNG();
                double d0 = random.nextGaussian() * 0.02D;
                double d1 = random.nextGaussian() * 0.02D;
                double d2 = random.nextGaussian() * 0.02D;
                world.spawnParticle(EnumParticleTypes.HEART, entityanimal.posX + (double) (random.nextFloat() * entityanimal.width * 2.0F) - (double) entityanimal.width, entityanimal.posY + 0.5D + (double) (random.nextFloat() * entityanimal.height), entityanimal.posZ + (double) (random.nextFloat() * entityanimal.width * 2.0F) - (double) entityanimal.width, 1, d0, d1, d2, 0D);
            }
        }
    }

    @SubscribeEvent
    public void spawnBaby(BabyEntitySpawnEvent evt) {
        if (evt.getParentA() != null) {
            EntityLiving entityanimal = evt.getParentA();
            WorldServer world = (WorldServer) entityanimal.world;
            Random random = entityanimal.getRNG();
            EntityAgeable child = evt.getChild();
            System.out.println("Child x coord is: " + child.posX);
            System.out.println("Child y coord is: " + child.posY);
            System.out.println("Child z coord is: " + child.posZ);

            for (int i = 0; i < 7; ++i)
            {
                double d0 = random.nextGaussian() * 0.02D;
                double d1 = random.nextGaussian() * 0.02D;
                double d2 = random.nextGaussian() * 0.02D;
                double d3 = random.nextDouble() * (double)entityanimal.width * 2.0D - (double)entityanimal.width;
                double d4 = 0.5D + random.nextDouble() * (double)entityanimal.height;
                double d5 = random.nextDouble() * (double)entityanimal.width * 2.0D - (double)entityanimal.width;
                world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, entityanimal.posX + d3, entityanimal.posY + d4, entityanimal.posZ + d5, 1, d0, d1, d2, 0D);
            }
        }
    }
}
