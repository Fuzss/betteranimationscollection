package fuzs.betteranimationscollection.mixin.client;

import fuzs.betteranimationscollection.BetterAnimationsCollection;
import fuzs.betteranimationscollection.client.element.AnimatedPlayerElement;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@SuppressWarnings("unused")
@Mixin(PlayerModel.class)
public abstract class PlayerModelMixin<T extends LivingEntity> extends BipedModel<T> {

    @Shadow
    @Final
    public ModelRenderer leftSleeve;
    @Shadow
    @Final
    public ModelRenderer rightSleeve;

    public PlayerModelMixin(float p_i1148_1_) {

        super(p_i1148_1_);
    }

    @Inject(method = "setupAnim", at = @At("TAIL"))
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {

        AnimatedPlayerElement element = (AnimatedPlayerElement) BetterAnimationsCollection.ANIMATED_PLAYER;
        if (!element.isEnabled()) {

            return;
        }

        if (element.eatingAnimation) {

            this.setupEatingAnim(entityIn, ageInTicks);
        }

        if (element.rowingAnimation) {

            this.setupRowingAnim(entityIn, limbSwing);
        }

        if (element.ridingAnimation) {

            this.setupRidingAnim(entityIn, ageInTicks);
        }

        if (!element.inspectableItems.isEmpty()) {

            this.setupInspectableAnim(entityIn, ageInTicks, element.inspectableItems);
        }

        this.hat.copyFrom(this.head);
        this.rightSleeve.copyFrom(this.rightArm);
        this.leftSleeve.copyFrom(this.leftArm);
    }

    private void setupEatingAnim(T entityIn, float ageInTicks) {

        int remainingUseTicks = entityIn.getUseItemRemainingTicks();
        if (entityIn.isUsingItem() && remainingUseTicks > 0) {

            ItemStack stackInHand = entityIn.getItemInHand(entityIn.getUsedItemHand());
            if (stackInHand.getUseAnimation() == UseAction.EAT || stackInHand.getUseAnimation() == UseAction.DRINK) {

                final float partialTicks = MathHelper.frac(ageInTicks);
                float partialUseTicks = remainingUseTicks - partialTicks + 1.0F;
                float useDurationRatio = partialUseTicks / (float) stackInHand.getUseDuration();
                float useFactor = 1.0F - (float) Math.pow(useDurationRatio, 27.0);
                if (useDurationRatio < 0.8F) {

                    useFactor += MathHelper.abs(MathHelper.cos(partialUseTicks / 4.0F * (float) Math.PI) * 0.1F);
                }

                this.head.xRot = useFactor * 0.5F;
                this.head.yRot = 0.0F;
                useFactor *= 0.75F;
                HandSide mainArm = entityIn.getMainArm();
                if ((entityIn.getUsedItemHand() == Hand.MAIN_HAND ? mainArm : mainArm.getOpposite()) == HandSide.RIGHT) {

                    this.rightArm.xRot = useFactor * (this.rightArm.xRot * 0.5F - ((float) Math.PI * 4.0F / 10.0F));
                    this.rightArm.yRot = useFactor * (float) Math.PI / -6F;
                } else {

                    this.leftArm.xRot = useFactor * (this.leftArm.xRot * 0.5F - ((float) Math.PI * 4.0F / 10.0F));
                    this.leftArm.yRot = useFactor * (float) Math.PI / 6F;
                }
            }
        }
    }
    
    private void setupRowingAnim(T entityIn, float limbSwing) {

        // rotate arms same way as paddles so it looks like the player is actually doing something
        if (entityIn.getVehicle() instanceof BoatEntity) {

            BoatEntity boatEntity = (BoatEntity) entityIn.getVehicle();
            float rowingTimeRight = boatEntity.getRowingTime(1, limbSwing);
            if (rowingTimeRight > 0.0F) {

                this.rightArm.xRot = -0.5F + (float) MathHelper.clampedLerp(-(float) Math.PI / 3.0F, -(float) Math.PI / 12.0F, (MathHelper.sin(-rowingTimeRight) + 1.0F) / 2.0F);
                this.rightArm.yRot = (float) MathHelper.clampedLerp(-(float) Math.PI / 24.0F, (float) Math.PI / 3.0F, (MathHelper.sin(-rowingTimeRight + 1.0F) + 1.0F) / 8.0F);
            }

            float rowingTimeLeft = boatEntity.getRowingTime(0, limbSwing);
            if (rowingTimeLeft > 0.0F) {

                this.leftArm.xRot = -0.5F + (float) MathHelper.clampedLerp(-(float) Math.PI / 3.0F, -(float) Math.PI / 12.0F, (MathHelper.sin(-rowingTimeLeft) + 1.0F) / 2.0F);
                this.leftArm.yRot = -(float) MathHelper.clampedLerp(-(float) Math.PI / 24.0F, (float) Math.PI / 3.0F, (MathHelper.sin(-rowingTimeLeft + 1.0F) + 1.0F) / 8.0F);
            }
        }
    }

    private void setupRidingAnim(T entityIn, float ageInTicks) {

        // calculate same way as horse head angle
        if (entityIn.getVehicle() instanceof AbstractHorseEntity && !(entityIn.getVehicle() instanceof LlamaEntity)) {

            AbstractHorseEntity horseEntity = (AbstractHorseEntity) entityIn.getVehicle();
            final float partialTicks = MathHelper.frac(ageInTicks);

            float limbSwing = this.getLimbSwing(horseEntity, partialTicks);
            float limbSwingAmount = this.getLimbSwingAmount(horseEntity, partialTicks);
            float xRot = MathHelper.lerp(partialTicks, horseEntity.xRotO, horseEntity.xRot) * ((float) Math.PI / 180.0F);
            if (limbSwingAmount > 0.2F) {

                xRot += MathHelper.cos(limbSwing * 0.4F) * 0.15F * limbSwingAmount;
            }

            float eatAnimTicks = horseEntity.getEatAnim(partialTicks);
            float standAnimTicks = horseEntity.getStandAnim(partialTicks);
            float mouthAnimTicks = horseEntity.getMouthAnim(partialTicks);
            float lookAngle = (1.0F - Math.max(standAnimTicks, eatAnimTicks)) * (((float) Math.PI / 6.0F) + xRot + mouthAnimTicks * MathHelper.sin(ageInTicks) * 0.05F);
            float horseHeadAngle = standAnimTicks * (0.2617994F + xRot) + eatAnimTicks * (2.1816616F + MathHelper.sin(ageInTicks) * 0.05F) + lookAngle;
            this.rightArm.xRot = this.leftArm.xRot = -((float) Math.PI / 15.0F) - horseHeadAngle;
            this.rightArm.yRot -= (float) Math.PI / 9.0F;
            this.leftArm.yRot += (float) Math.PI / 9.0F;
            
        }
    }

    private float getLimbSwingAmount(AbstractHorseEntity horseEntity, float partialTicks) {

        float limbSwingAmount = MathHelper.lerp(partialTicks, horseEntity.animationSpeedOld, horseEntity.animationSpeed);
        if (limbSwingAmount > 1.0F) {

            limbSwingAmount = 1.0F;
        }

        return limbSwingAmount;
    }

    private float getLimbSwing(AbstractHorseEntity horseEntity, float partialTicks) {

        float limbSwing = horseEntity.animationPosition - horseEntity.animationSpeed * (1.0F - partialTicks);
        if (horseEntity.isBaby()) {

            limbSwing *= 3.0F;
        }

        return limbSwing;
    }

    private void setupInspectableAnim(T entityIn, float ageInTicks, Set<Item> admirableItems) {

        if (this.swimAmount <= 0.0F && this.attackTime <= 0.0F && admirableItems.contains(entityIn.getMainHandItem().getItem())) {

            final float headXRot = 0.5F;
            final float headYRot = 0.0F;
            final float admiringZone = 0.5F;
            if (Math.abs(this.head.xRot - headXRot) < admiringZone && Math.abs(this.head.yRot - headYRot) < admiringZone) {

                this.head.xRot = headXRot;
                this.head.yRot = headYRot;
                if (entityIn.getMainArm() == HandSide.RIGHT) {

                    this.rightArm.xRot = -0.9F;
                    this.rightArm.yRot = -0.5F;
                    if (this.crouching) {

                        this.rightArm.xRot += 0.15F;
                    }

                    // bobArms
                    this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
                    this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
                } else {

                    this.leftArm.xRot = -0.9F;
                    this.leftArm.yRot = 0.5F;
                    if (this.crouching) {

                        this.leftArm.xRot += 0.15F;
                    }

                    // bobArms
                    this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
                    this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
                }
            }
        }
    }

}
