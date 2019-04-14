package fuzs.tbac2.fixes;

import com.google.common.base.Predicate;
import com.mojang.authlib.GameProfile;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemEnderEye;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.UUID;

public class ParticleCreateWither extends BlockSkull {

    private static final Predicate<BlockWorldState> IS_WITHER_SKELETON = new Predicate<BlockWorldState>()
    {
        public boolean apply(@Nullable BlockWorldState p_apply_1_)
        {
            return p_apply_1_.getBlockState() != null && p_apply_1_.getBlockState().getBlock() == Blocks.SKULL && p_apply_1_.getTileEntity() instanceof TileEntitySkull && ((TileEntitySkull)p_apply_1_.getTileEntity()).getSkullType() == 1;
        }
    };
    private BlockPattern witherPattern1;
    private BlockPattern witherPattern2;
    private BlockPattern witherPattern3;

    @SubscribeEvent
    public void showSpawnParticles(PlayerInteractEvent.RightClickBlock evt) {
        if (evt.getItemStack().getItem() instanceof ItemSkull) {
            EnumFacing facing = evt.getFace();
            World worldIn = evt.getWorld();
            BlockPos pos = evt.getPos();
            EntityPlayer player = evt.getEntityPlayer();
            EnumHand hand = evt.getHand();
            if (facing != EnumFacing.DOWN)
            {
                if (worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos))
                {
                    facing = EnumFacing.UP;
                    pos = pos.down();
                }
                IBlockState iblockstate = worldIn.getBlockState(pos);
                Block block = iblockstate.getBlock();
                boolean flag = block.isReplaceable(worldIn, pos);

                if (!flag)
                {
                    if (!worldIn.getBlockState(pos).getMaterial().isSolid() && !worldIn.isSideSolid(pos, facing, true))
                    {
                        return;
                    }

                    pos = pos.offset(facing);
                }

                ItemStack itemstack = player.getHeldItem(hand);

                if (player.canPlayerEdit(pos, facing, itemstack) && Blocks.SKULL.canPlaceBlockAt(worldIn, pos))
                {
                    if (itemstack.getMetadata() == 1 && pos.getY() >= 2 && worldIn.getDifficulty() != EnumDifficulty.PEACEFUL && !worldIn.isRemote)
                    {
                        BlockPattern blockpattern = this.getWitherPattern1();
                        BlockPattern.PatternHelper blockpattern$patternhelper = blockpattern.match(worldIn, pos);
                        if (blockpattern$patternhelper != null) {
                            this.showParticles(blockpattern, blockpattern$patternhelper, worldIn);
                        } else {
                            blockpattern = this.getWitherPattern2();
                            blockpattern$patternhelper = blockpattern.match(worldIn, pos);
                            if (blockpattern$patternhelper != null) {
                                this.showParticles(blockpattern, blockpattern$patternhelper, worldIn);
                            } else {
                                blockpattern = this.getWitherPattern3();
                                blockpattern$patternhelper = blockpattern.match(worldIn, pos);
                                if (blockpattern$patternhelper != null) {
                                    this.showParticles(blockpattern, blockpattern$patternhelper, worldIn);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void showParticles(BlockPattern blockpattern, BlockPattern.PatternHelper blockpattern$patternhelper, World worldIn) {
        for (int i1 = 0; i1 < blockpattern.getPalmLength(); ++i1)
        {
            for (int j1 = 0; j1 < blockpattern.getThumbLength(); ++j1)
            {
                BlockWorldState blockworldstate2 = blockpattern$patternhelper.translateOffset(i1, j1, 0);
                worldIn.playEvent(2001, blockworldstate2.getPos(), Block.getStateId(blockworldstate2.getBlockState()));
            }
        }
    }

    protected BlockPattern getWitherPattern1()
    {
        if (this.witherPattern1 == null)
        {
            this.witherPattern1 = FactoryBlockPattern.start().aisle("~^^", "###", "~#~").where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.SOUL_SAND))).where('^', IS_WITHER_SKELETON).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.witherPattern1;
    }

    protected BlockPattern getWitherPattern2()
    {
        if (this.witherPattern2 == null)
        {
            this.witherPattern2 = FactoryBlockPattern.start().aisle("^~^", "###", "~#~").where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.SOUL_SAND))).where('^', IS_WITHER_SKELETON).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.witherPattern2;
    }

    protected BlockPattern getWitherPattern3()
    {
        if (this.witherPattern3 == null)
        {
            this.witherPattern3 = FactoryBlockPattern.start().aisle("^^~", "###", "~#~").where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.SOUL_SAND))).where('^', IS_WITHER_SKELETON).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.witherPattern3;
    }
}
