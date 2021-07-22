package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.MagmaCubeBurgerModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class MagmaCubeBurgerElement extends ModelElement {

    @Override
    public String[] getDescription() {

        return new String[]{"Adds a custom death animation to magma cubes, which causes their bodies to form into a pile of steamy, delicious hamburger patties when they die.",
                "Unfortunately, you can't eat them because they're way too hot."};
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new MagmaCubeBurgerModel<>();
    }

}
