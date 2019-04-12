package fuzs.tbac2.tweaks;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
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

    public EntityAIAttackRangedEasyBowOld(IRangedAttackMob attacker, double movespeed, int p_i1649_4_, float p_i1649_5_)
    {
        this(attacker, movespeed, p_i1649_4_, p_i1649_4_, p_i1649_5_);
    }

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
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return this.shouldExecute() || !this.entityHost.getNavigator().noPath();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.attackTarget = null;
        this.seeTime = 0;
        this.attackTime = -1;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        double d0 = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
        boolean flag = this.entityHost.getEntitySenses().canSee(this.attackTarget);

        if (flag)
        {
            ++this.seeTime;
        }
        else
        {
            this.seeTime = 0;
        }

        if (d0 <= (double)this.maxAttackDistance && this.seeTime >= 20)
        {
            this.entityHost.getNavigator().clearPath();
        }
        else
        {
            this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
        }

        this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);

        if (--this.attackTime == 0)
        {
            if (d0 > (double)this.maxAttackDistance || !flag)
            {
                return;
            }

            float f = MathHelper.sqrt(d0) / this.field_96562_i;
            float lvt_5_1_ = MathHelper.clamp(f, 0.1F, 1.0F);
            this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.attackTarget, lvt_5_1_);
            this.attackTime = MathHelper.floor(f * (float)(this.maxattackTime - this.field_96561_g) + (float)this.field_96561_g);
        }
        else if (this.attackTime < 0)
        {
            float f2 = MathHelper.sqrt(d0) / this.field_96562_i;
            this.attackTime = MathHelper.floor(f2 * (float)(this.maxattackTime - this.field_96561_g) + (float)this.field_96561_g);
        }
    }
}
