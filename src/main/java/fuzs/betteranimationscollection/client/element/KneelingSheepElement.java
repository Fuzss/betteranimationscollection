package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.KneelingSheepModel;
import fuzs.betteranimationscollection.client.renderer.entity.model.KneelingSheepWoolModel;
import net.minecraft.client.renderer.entity.layers.SheepWoolLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class KneelingSheepElement extends ModelElement {

    @Override
    public String[] getDescription() {

        return new String[]{"This one is pretty kneat. It makes sheep actually bend down to eat grass.", "It's no longer just their head lowering, their whole body lowers down to get a sweet sample of that succulent cellulose.", "Did you notice their KNEES bend too when they kneel?"};
    }

    @Override
    public void constructClient() {

        this.addLayerTransformer(layerRenderer -> layerRenderer instanceof SheepWoolLayer, KneelingSheepWoolModel::new);
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new KneelingSheepModel<>();
    }

}
