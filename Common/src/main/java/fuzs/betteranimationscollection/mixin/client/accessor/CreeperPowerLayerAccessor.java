package fuzs.betteranimationscollection.mixin.client.accessor;

import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreeperPowerLayer.class)
public interface CreeperPowerLayerAccessor {

    @Accessor
    @Mutable
    void setModel(CreeperModel<Creeper> model);
}
