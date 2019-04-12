package fuzs.tbac2.fixes;

import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemEnderEye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ParticleBlockAction {

    @SubscribeEvent
    public void smokeOnFluidMixing(BlockEvent.FluidPlaceBlockEvent evt) {
        if(evt.getNewState().getBlock() != Blocks.FIRE && !evt.getWorld().isRemote) {
            BlockPos pos = evt.getPos();
            WorldServer world = (WorldServer) evt.getWorld();
            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.2D, (double)pos.getZ() + 0.5D, 8, 0.25D, 0D, 0.25D, 0D);
        }
    }

    @SubscribeEvent
    public void smokeOnEyeInsert(PlayerInteractEvent.RightClickBlock evt) {
        if (evt.getItemStack().getItem() instanceof ItemEnderEye && !evt.getWorld().isRemote) {
            BlockPos pos = evt.getPos();
            EnumFacing facing = evt.getFace();
            World worldIn = evt.getWorld();
            IBlockState iblockstate = worldIn.getBlockState(pos);
            ItemStack itemstack = evt.getItemStack();
            if (evt.getEntityPlayer().canPlayerEdit(pos.offset(facing), facing, itemstack) && iblockstate.getBlock() == Blocks.END_PORTAL_FRAME && !((Boolean)iblockstate.getValue(BlockEndPortalFrame.EYE)).booleanValue()) {
                ((WorldServer) worldIn).spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + 0.5D, pos.getY() + 0.9225D, pos.getZ() + 0.5D, 16, 0.225D, 0D, 0.225D, 0D);
            }
        }
    }

}
