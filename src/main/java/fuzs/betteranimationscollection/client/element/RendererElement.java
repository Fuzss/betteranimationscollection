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

        EntityRenderer<? super T> entityRenderer = this.getEntityRenderer();
        this.mc.getEntityRenderDispatcher().register(entityType, entityRenderer);
        BetterAnimationsCollection.LOGGER.info("Replaced {} with {} for type {} in EntityRenderDispatcher", this.origEntityRenderer.getClass().getSimpleName(), entityRenderer.getClass().getSimpleName(), entityType.getRegistryName());
    }

    @Override
    public void unloadClient() {

        EntityType<T> entityType = this.getEntityType();
        this.mc.getEntityRenderDispatcher().register(entityType, this.origEntityRenderer);
        BetterAnimationsCollection.LOGGER.info("Restored {} for type {} in EntityRenderDispatcher", this.origEntityRenderer.getClass().getSimpleName(), entityType.getRegistryName());
    }

}
