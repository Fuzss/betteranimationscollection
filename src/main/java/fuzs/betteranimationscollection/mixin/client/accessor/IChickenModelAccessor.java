package fuzs.betteranimationscollection.mixin.client.accessor;

import net.minecraft.client.renderer.entity.model.ChickenModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChickenModel.class)
public interface IChickenModelAccessor<T extends Entity> {

    @Accessor
    ModelRenderer getHead();

    @Accessor
    ModelRenderer getBody();

    @Accessor
    ModelRenderer getLeg0();

    @Accessor
    ModelRenderer getLeg1();

    @Accessor
    void setWing0(ModelRenderer wing0);

    @Accessor
    void setWing1(ModelRenderer wing1);

    @Accessor
    void setRedThing(ModelRenderer redThing);

}
