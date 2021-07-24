package fuzs.betteranimationscollection.client.element;

import com.google.common.collect.ImmutableList;
import fuzs.betteranimationscollection.client.renderer.entity.model.DrownedKneesModel;
import fuzs.betteranimationscollection.client.renderer.entity.model.PiglinKneesModel;
import fuzs.betteranimationscollection.client.renderer.entity.model.ZombieKneesModel;
import net.minecraft.client.renderer.entity.layers.DrownedOuterLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

import java.util.List;
import java.util.function.Supplier;

public class HumanoidKneesElement extends MultiModelElement {

    @Override
    public String[] getDescription() {

        return new String[]{"This one makes the knees of zombie-like and piglin-like mobs bend when they walk around.",
                "Looks pretty fluid and nice. You'll like it, trust me."};
    }

    @Override
    public void constructClient() {

        // TODO armor layer for all
        this.addLayerTransformer(DrownedKneesModel.class, layerRenderer -> layerRenderer instanceof DrownedOuterLayer, () -> new DrownedKneesModel<>(0.25F));
    }

    @Override
    protected List<Supplier<EntityModel<? extends LivingEntity>>> getEntityModels() {

        return ImmutableList.of(() -> new ZombieKneesModel<>(0.0F, false), () -> new DrownedKneesModel<>(0.0F), PiglinKneesModel::new);
    }

}
