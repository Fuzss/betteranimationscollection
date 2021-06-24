package fuzs.betteranimationscollection.client.element;

import com.google.common.collect.Maps;
import fuzs.puzzleslib.element.AbstractElement;
import fuzs.puzzleslib.element.side.IClientElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

import java.util.Map;
import java.util.function.Supplier;

public abstract class ModelElement extends AbstractElement implements IClientElement {

    private final Minecraft mc = Minecraft.getInstance();

    private final Map<LivingRenderer<?, ?>, EntityModel<?>> rendererToOrigModel = Maps.newHashMap();

    protected abstract EntityModel<?> getEntityModel();

    @Override
    public void loadClient() {

        // find all renderers which normally use the super class of our model, so we can safely exchange them
        this.mc.getEntityRenderDispatcher().renderers.values().forEach(renderer -> {

            if (renderer instanceof LivingRenderer) {

                @SuppressWarnings("rawtypes")
                LivingRenderer livingRenderer = ((LivingRenderer<?, ? extends EntityModel<?>>) renderer);
                EntityModel<?> model = this.getEntityModel();
                if (livingRenderer.model.getClass().equals(model.getClass().getSuperclass())) {

                    this.rendererToOrigModel.put(livingRenderer, livingRenderer.getModel());
                    livingRenderer.model = model;
                }
            }
        });
    }

    @Override
    public void unloadClient() {

        this.dos();
    }

    private <T extends LivingEntity, S extends EntityModel<T>> void dos() {

        this.rendererToOrigModel.forEach((key, value) -> ((LivingRenderer<T, S>) key).model = (S) value);
    }

}
