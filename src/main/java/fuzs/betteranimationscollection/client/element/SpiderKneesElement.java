package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.SpiderKneesModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class SpiderKneesElement extends ModelElement {

    @Override
    public String[] getDescription() {

        return new String[]{"A truly stunning visual addition. Spiders now finally have the knees they've always dreamed of."};
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new SpiderKneesModel<>();
    }

}
