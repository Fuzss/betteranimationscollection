package fuzs.betteranimationscollection.mixin.client.accessor;

import net.minecraft.client.renderer.entity.model.SnowManModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SnowManModel.class)
public interface ISnowManModelAccessor {

    @Accessor
    ModelRenderer getArm1();

    @Accessor
    ModelRenderer getArm2();

}
