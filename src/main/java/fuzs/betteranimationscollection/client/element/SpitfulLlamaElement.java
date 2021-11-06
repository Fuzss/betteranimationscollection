package fuzs.betteranimationscollection.client.element;

import fuzs.betteranimationscollection.client.renderer.entity.model.SpitfulLlamaModel;
import net.minecraft.client.renderer.entity.layers.LlamaDecorLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.util.SoundEvents;

public class SpitfulLlamaElement extends SoundModelElement {

    public SpitfulLlamaElement() {

        this.defaultSounds.add(SoundEvents.LLAMA_SPIT);
    }

    @Override
    public String[] getDescription() {

        return new String[]{"This one makes llamas open their mouth when spitting. How have they been doing that before?!"};
    }

    @Override
    public void constructClient() {

        this.addLayerTransformer(SpitfulLlamaModel.class, layerRenderer -> layerRenderer instanceof LlamaDecorLayer, () -> new SpitfulLlamaModel<>(0.5F));
    }

    @Override
    protected EntityModel<? extends LivingEntity> getEntityModel() {

        return new SpitfulLlamaModel<>(0.0F);
    }

    @Override
    protected Class<? extends MobEntity> getMobClazz() {

        return LlamaEntity.class;
    }

}
