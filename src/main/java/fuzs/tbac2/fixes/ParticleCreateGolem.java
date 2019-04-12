package fuzs.tbac2.fixes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ParticleCreateGolem extends BlockPumpkin {

    @SubscribeEvent
    public void showSpawnParticles(BlockEvent.PlaceEvent evt) {
        if (evt.getPlacedBlock().getBlock() == Blocks.PUMPKIN || evt.getPlacedBlock().getBlock() == Blocks.LIT_PUMPKIN) {
            World worldIn = evt.getWorld();
            BlockPos pos = evt.getPos();
            BlockPattern blockpattern = this.getSnowmanPattern();
            BlockPattern.PatternHelper blockpattern$patternhelper = blockpattern.match(worldIn, pos);
            if (blockpattern$patternhelper != null) {

                for (int i1 = 0; i1 < blockpattern.getThumbLength(); ++i1)
                {
                    BlockWorldState blockworldstate2 = blockpattern$patternhelper.translateOffset(0, i1, 0);
                    worldIn.playEvent(2001, blockworldstate2.getPos(), Block.getStateId(blockworldstate2.getBlockState()));
                }

            } else {
                blockpattern = this.getGolemPattern();
                blockpattern$patternhelper = blockpattern.match(worldIn, pos);
                if (blockpattern$patternhelper != null) {

                    for (int k1 = 0; k1 < blockpattern.getPalmLength(); ++k1) {
                        for (int l1 = 0; l1 < blockpattern.getThumbLength(); ++l1) {
                            BlockWorldState blockworldstate1 = blockpattern$patternhelper.translateOffset(k1, l1, 0);
                            worldIn.playEvent(2001, blockworldstate1.getPos(), Block.getStateId(blockworldstate1.getBlockState()));
                        }
                    }
                }
            }
        }
    }
}
