package fuzs.betteranimationscollection.mixin.client.accessor;

import net.minecraft.client.renderer.entity.layers.SheepWoolLayer;
import net.minecraft.client.renderer.entity.model.SheepWoolModel;
import net.minecraft.entity.passive.SheepEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SheepWoolLayer.class)
public interface ISheepWoolLayerAccessor {

    @Accessor
    void setModel(SheepWoolModel<SheepEntity> model);

}
