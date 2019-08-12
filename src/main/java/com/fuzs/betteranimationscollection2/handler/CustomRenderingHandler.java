package com.fuzs.betteranimationscollection2.handler;

import com.fuzs.betteranimationscollection2.helper.ReflectionHelper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomRenderingHandler {

    private static final Map<Class<? extends Entity>, EntityModel<? extends Entity>> entityRenderers = new ConcurrentHashMap<>();

    public static <T extends Entity> void registerEntityRenderingHandler(Class<T> entityClass, EntityModel<? super T> renderFactory)
    {
        entityRenderers.put(entityClass, renderFactory);
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void renderLivingPre(RenderLivingEvent.Pre evt) {

        EntityModel model = entityRenderers.get(evt.getEntity().getClass());

        if (model != null) {
            ReflectionHelper.setModel(evt.getRenderer(), model);
        }

    }

}
