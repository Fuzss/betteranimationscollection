package fuzs.betteranimationscollection.mixin.client.accessor;

import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WolfModel.class)
public interface WolfModelAccessor {

    @Accessor
    @Mutable
    ModelPart setTail(ModelPart tail);

    @Accessor
    @Mutable
    ModelPart setRealTail(ModelPart realTail);
}
