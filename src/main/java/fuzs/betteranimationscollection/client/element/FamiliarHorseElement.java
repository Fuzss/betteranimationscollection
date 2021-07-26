package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.FamiliarHorseModel;
import net.minecraft.client.renderer.entity.layers.LeatherHorseArmorLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class FamiliarHorseElement extends ModelElement {

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
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new FamiliarHorseModel<>(0.0F);
    }

}
