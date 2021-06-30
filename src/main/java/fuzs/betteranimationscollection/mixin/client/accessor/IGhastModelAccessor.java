package fuzs.betteranimationscollection.mixin.client.accessor;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.GhastModel;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GhastModel.class)
public interface IGhastModelAccessor<T extends Entity> {

    @Accessor
    void setParts(ImmutableList<ModelRenderer> parts);

}
