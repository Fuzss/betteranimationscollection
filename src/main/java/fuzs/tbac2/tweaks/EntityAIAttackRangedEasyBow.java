package fuzs.tbac2.tweaks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemBow;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;

public class EntityAIAttackRangedEasyBow<T extends EntityMob & IRangedAttackMob> extends EntityAIBase
{
    private final T entity;
    private EntityLivingBase attackTarget;
    private final double moveSpeedAmp;
    private int attackCooldown;
    private float maxAttackDistance;
    private float maxAttackDistanceSq;
    private int attackTime = -1;
    private int seeTime;

    public EntityAIAttackRangedEasyBow(T p_i47515_1_, double movespeed, int attackCooldownIn, float maxAttackDistanceIn)
    {
        this.entity = p_i47515_1_;
        this.moveSpeedAmp = movespeed;
        this.attackCooldown = attackCooldownIn;
        this.maxAttackDistance = maxAttackDistanceIn;
        this.maxAttackDistanceSq = maxAttackDistanceIn * maxAttackDistanceIn;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.entity.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        else
        {
            this.attackTarget = entitylivingbase;
            return this.isBowInMainhand();
        }
    }

    private boolean isBowInMainhand()
    {
        return !this.entity.getHeldItemMainhand().isEmpty() && this.entity.getHeldItemMainhand().getItem() instanceof ItemBow;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return (this.shouldExecute() || !this.entity.getNavigator().noPath()) && this.isBowInMainhand();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        super.startExecuting();
        this.entity.setSwingingArms(true);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        super.resetTask();
        this.attackTarget = null;
        this.entity.setSwingingArms(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.entity.resetActiveHand();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        double d0 = this.entity.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
        boolean flag = this.entity.getEntitySenses().canSee(this.attackTarget);
        boolean flag1 = this.seeTime > 0;

        if (flag != flag1)
        {
            this.seeTime = 0;
        }

        if (flag)
        {
            ++this.seeTime;
        }
        else
        {
            --this.seeTime;
        }

        if (d0 <= (double)this.maxAttackDistanceSq && this.seeTime >= 20)
        {
            this.entity.getNavigator().clearPath();
        }
        else
        {
            this.entity.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.moveSpeedAmp);
        }

        this.entity.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);

//        if (this.entity.isHandActive())
//        {
//            if (!flag && this.seeTime < -60)
//            {
//                this.entity.resetActiveHand();
//            }
//            else if (flag)
//            {
//                int i = this.entity.getItemInUseMaxCount();
//
//                if (i >= 20)
//                {
//                    this.entity.resetActiveHand();
//                    this.entity.attackEntityWithRangedAttack(this.attackTarget, ItemBow.getArrowVelocity(i));
//                    float f = MathHelper.sqrt(d0) / this.maxAttackDistance;
//                    this.attackTime = MathHelper.floor(f * (float)(60 - this.attackCooldown) + (float)this.attackCooldown);
//                }
//            }
//        }
//        else if (--this.attackTime <= 0 && this.seeTime >= -60)
//        {
//            this.entity.setActiveHand(EnumHand.MAIN_HAND);
//            float f2 = MathHelper.sqrt(d0) / this.maxAttackDistance;
//            this.attackTime = MathHelper.floor(f2 * (float)(60 - this.attackCooldown) + (float)this.attackCooldown);
//        }

        if (--this.attackTime == 0)
        {
            float f = MathHelper.sqrt(d0) / this.maxAttackDistance;
            float lvt_5_1_ = MathHelper.clamp(f, 0.1F, 1.0F);
            this.entity.attackEntityWithRangedAttack(this.attackTarget, lvt_5_1_);
            this.attackTime = MathHelper.floor(f * (float)(60 - this.attackCooldown) + (float)this.attackCooldown);
        }
        else if (this.attackTime < 0)
        {
            float f2 = MathHelper.sqrt(d0) / this.maxAttackDistance;
            this.attackTime = MathHelper.floor(f2 * (float)(60 - this.attackCooldown) + (float)this.attackCooldown);
        }
    }
}