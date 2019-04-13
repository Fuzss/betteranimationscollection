package fuzs.tbac2.tweaks;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemBow;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;

public class EntityAIAttackRangedEasyBowOld extends EntityAIBase
{
    /** The entity the AI instance has been applied to */
    private final EntityLiving entityHost;

    /**
     * The entity (as a RangedAttackMob) the AI instance has been applied to.
     */
    private final IRangedAttackMob rangedAttackEntityHost;
    private EntityLivingBase attackTarget;

    /**
     * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
     * maxattackTime.
     */
    private int attackTime;
    private double entityMoveSpeed;
    private int seeTime;
    private int field_96561_g;

    /**
     * The maximum time the AI has to wait before peforming another ranged attack.
     */
    private int maxattackTime;
    private float field_96562_i;
    private float maxAttackDistance;

    public EntityAIAttackRangedEasyBowOld(IRangedAttackMob attacker, double movespeed, int p_i1650_4_, int maxattackTime, float maxAttackDistanceIn)
    {
        this.attackTime = -1;

        if (!(attacker instanceof EntityLivingBase))
        {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        }
        else
        {
            this.rangedAttackEntityHost = attacker;
            this.entityHost = (EntityLiving)attacker;
            this.entityMoveSpeed = movespeed;
            this.field_96561_g = p_i1650_4_;
            this.maxattackTime = maxattackTime;
            this.field_96562_i = maxAttackDistanceIn;
            this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
            this.setMutexBits(3);
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.entityHost.getAttackTarget();

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
        return !this.entityHost.getHeldItemMainhand().isEmpty() && this.entityHost.getHeldItemMainhand().getItem() instanceof ItemBow;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return this.shouldExecute() || !this.entityHost.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        super.startExecuting();
        ((IRangedAttackMob)this.entityHost).setSwingingArms(true);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.attackTarget = null;
        ((IRangedAttackMob)this.entityHost).setSwingingArms(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.entityHost.resetActiveHand();
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {

        double d0 = this.entityHost.getDistanceSq(attackTarget.posX, attackTarget.getEntityBoundingBox().minY, attackTarget.posZ);
        boolean flag = this.entityHost.getEntitySenses().canSee(attackTarget);

        if (flag != this.seeTime > 0)
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

        if (d0 <= (double)this.maxAttackDistance && this.seeTime >= 20)
        {
            this.entityHost.getNavigator().clearPath();
        }
        else
        {
            this.entityHost.getNavigator().tryMoveToEntityLiving(attackTarget, this.entityMoveSpeed);
        }

        this.entityHost.getLookHelper().setLookPositionWithEntity(attackTarget, 30.0F, 30.0F);

        if (--this.attackTime == 0 && this.entityHost.isHandActive())
        {
            if (d0 > (double)this.maxAttackDistance || (!flag && this.seeTime < -60))
            {
                this.entityHost.resetActiveHand();
                return;
            }

            int i = this.entityHost.getItemInUseMaxCount();

            if (i >= 20) {
                this.entityHost.resetActiveHand();
                this.rangedAttackEntityHost.attackEntityWithRangedAttack(attackTarget, ItemBow.getArrowVelocity(i));
                float f = MathHelper.sqrt(d0) / this.field_96562_i;
                this.attackTime = MathHelper.floor(f * (float) (this.maxattackTime - this.field_96561_g) + (float) this.field_96561_g);
            }
        }
        else if (this.attackTime < 0)
        {
            this.entityHost.setActiveHand(EnumHand.MAIN_HAND);
            float f2 = MathHelper.sqrt(d0) / this.field_96562_i;
            this.attackTime = MathHelper.floor(f2 * (float)(this.maxattackTime - this.field_96561_g) + (float)this.field_96561_g);
        }
    }
}
