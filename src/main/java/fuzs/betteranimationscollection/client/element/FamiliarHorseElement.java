package fuzs.betteranimationscollection.client.element;

import com.google.common.collect.ImmutableList;
import fuzs.betteranimationscollection.client.renderer.entity.model.*;
import net.minecraft.client.renderer.entity.layers.LeatherHorseArmorLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

import java.util.List;
import java.util.function.Supplier;

public class FamiliarHorseElement extends MultiModelElement {

    @Override
    public String[] getDescription() {

        return new String[]{"Makes horses more lively again, just like they used to be in older versions.",
                "Does this by adding back their mouth and knees while staying true to the vanilla model style."};
    }

    @Override
    public void constructClient() {

        this.addLayerTransformer(FamiliarHorseModel.class, layerRenderer -> layerRenderer instanceof LeatherHorseArmorLayer, () -> new FamiliarHorseModel<>(0.1F));
    }

    @Override
    protected List<Supplier<EntityModel<? extends LivingEntity>>> getEntityModels() {

        return ImmutableList.of(() -> new FamiliarHorseModel<>(0.0F), () -> new FamiliarHorseArmorChestsModel<>(0.0F));
    }

}
