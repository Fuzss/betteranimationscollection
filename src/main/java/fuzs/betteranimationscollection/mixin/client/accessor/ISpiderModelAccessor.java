package fuzs.betteranimationscollection.mixin.client.accessor;

import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SpiderModel.class)
public interface ISpiderModelAccessor {

    @Accessor
    void setLeg0(ModelRenderer leg0);

    @Accessor
    void setLeg1(ModelRenderer leg1);

    @Accessor
    void setLeg2(ModelRenderer leg2);

    @Accessor
    void setLeg3(ModelRenderer leg3);

    @Accessor
    void setLeg4(ModelRenderer leg4);

    @Accessor
    void setLeg5(ModelRenderer leg5);

    @Accessor
    void setLeg6(ModelRenderer leg6);

    @Accessor
    void setLeg7(ModelRenderer leg7);

}
