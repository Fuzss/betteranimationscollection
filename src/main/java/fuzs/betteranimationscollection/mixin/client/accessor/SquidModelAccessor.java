package fuzs.betteranimationscollection.mixin.client.accessor;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SquidModel.class)
public interface SquidModelAccessor {

    @Accessor
    ModelRenderer getBody();

    @Accessor
    void setParts(ImmutableList<ModelRenderer> parts);

}
