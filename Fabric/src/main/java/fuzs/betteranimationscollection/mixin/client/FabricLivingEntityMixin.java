package fuzs.betteranimationscollection.mixin.client;

import fuzs.betteranimationscollection.api.event.entity.living.LivingEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class FabricLivingEntityMixin extends Entity {

    public FabricLivingEntityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tick(CallbackInfo callback) {
        if (LivingEvents.TICK.invoker().onLivingTick((LivingEntity) (Object) this).isPresent()) callback.cancel();
    }
}
