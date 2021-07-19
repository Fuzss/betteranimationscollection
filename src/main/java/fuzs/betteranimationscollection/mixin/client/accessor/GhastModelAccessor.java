package fuzs.betteranimationscollection.mixin.client.accessor;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.GhastModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GhastModel.class)
public interface GhastModelAccessor {

    @Accessor
    void setParts(ImmutableList<ModelRenderer> parts);

}
