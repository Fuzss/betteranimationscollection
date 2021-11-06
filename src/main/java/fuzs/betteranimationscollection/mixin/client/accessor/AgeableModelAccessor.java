package fuzs.betteranimationscollection.mixin.client.accessor;

import net.minecraft.client.renderer.entity.model.AgeableModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AgeableModel.class)
public interface AgeableModelAccessor {

    @Accessor
    void setYHeadOffset(float yHeadOffset);

    @Accessor
    void setZHeadOffset(float zHeadOffset);

}
