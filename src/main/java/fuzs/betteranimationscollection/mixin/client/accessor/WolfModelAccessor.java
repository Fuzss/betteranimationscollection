package fuzs.betteranimationscollection.mixin.client.accessor;

import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WolfModel.class)
public interface WolfModelAccessor {

    @Accessor
    ModelRenderer getRealHead();

    @Accessor
    void setTail(ModelRenderer tail);

    @Accessor
    void setRealTail(ModelRenderer realTail);

}
