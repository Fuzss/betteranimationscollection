package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.puzzleslib.element.AbstractElement;
import fuzs.puzzleslib.element.side.IClientElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

@SuppressWarnings("unchecked")
public abstract class RendererElement<T extends Entity> extends AbstractElement implements IClientElement {

    private final Minecraft mc = Minecraft.getInstance();

    private EntityRenderer<T> origEntityRenderer;

    protected abstract EntityType<T> getEntityType();

    protected abstract EntityRenderer<? super T> getEntityRenderer();

    @Override
    public void loadClient() {

        EntityType<T> entityType = this.getEntityType();
        if (this.origEntityRenderer == null) {

            this.origEntityRenderer = (EntityRenderer<T>) this.mc.getEntityRenderDispatcher().renderers.get(entityType);
        }

        this.mc.getEntityRenderDispatcher().register(entityType, this.getEntityRenderer());
        BetterAnimationsCollection.LOGGER.info("Replaced EntityRender for type {} in EntityRenderDispatcher", entityType.getRegistryName());
    }

    @Override
    public void unloadClient() {

        EntityType<T> entityType = this.getEntityType();
        this.mc.getEntityRenderDispatcher().register(entityType, this.origEntityRenderer);
        BetterAnimationsCollection.LOGGER.info("Restored EntityRender for type {} in EntityRenderDispatcher", entityType.getRegistryName());
    }

}
