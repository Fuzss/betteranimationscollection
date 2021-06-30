package fuzs.betteranimationscollection.mixin.client.accessor;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SquidModel.class)
public interface ISquidModelAccessor<T extends Entity> {

    @Accessor
    ModelRenderer getBody();

    @Accessor
    void setParts(ImmutableList<ModelRenderer> parts);

}
