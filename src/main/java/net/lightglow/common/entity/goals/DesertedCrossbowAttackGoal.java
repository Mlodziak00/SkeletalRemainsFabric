package net.lightglow.common.entity.goals;

import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.CrossbowAttackGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.EnumSet;

public class DesertedCrossbowAttackGoal <T extends HostileEntity & RangedAttackMob & CrossbowUser> extends Goal {
    public static final UniformIntProvider COOLDOWN_RANGE = TimeHelper.betweenSeconds(1, 2);
    private final T actor;
    private DesertedCrossbowAttackGoal.Stage stage;
    private final double speed;
    private final float squaredRange;
    private int seeingTargetTicker;
    private int chargedTicksLeft;
    private int cooldown;
    private boolean movingToLeft;
    private boolean backward;
    private int combatTicks = -1;


    public DesertedCrossbowAttackGoal(T actor, double speed, float range) {
        this.stage = DesertedCrossbowAttackGoal.Stage.UNCHARGED;
        this.actor = actor;
        this.speed = speed;
        this.squaredRange = range * range;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    public boolean canStart() {
        return this.hasAliveTarget() && this.isEntityHoldingCrossbow();
    }

    private boolean isEntityHoldingCrossbow() {
        return this.actor.isHolding(Items.CROSSBOW);
    }

    public boolean shouldContinue() {
        return this.hasAliveTarget() && (this.canStart() || !this.actor.getNavigation().isIdle()) && this.isEntityHoldingCrossbow();
    }

    private boolean hasAliveTarget() {
        return this.actor.getTarget() != null && this.actor.getTarget().isAlive();
    }

    public void stop() {
        super.stop();
        this.actor.setAttacking(false);
        this.actor.setTarget((LivingEntity)null);
        this.seeingTargetTicker = 0;
        if (this.actor.isUsingItem()) {
            this.actor.clearActiveItem();
            ((CrossbowUser)this.actor).setCharging(false);
            CrossbowItem.setCharged(this.actor.getActiveItem(), false);
        }

    }

    public boolean shouldRunEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingEntity = this.actor.getTarget();
        if (livingEntity != null) {
            double d = this.actor.squaredDistanceTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
            boolean bl = this.actor.getVisibilityCache().canSee(livingEntity);
            boolean bl2 = this.seeingTargetTicker > 0;
            if (bl != bl2) {
                this.seeingTargetTicker = 0;
            }

            if (bl) {
                ++this.seeingTargetTicker;
            } else {
                --this.seeingTargetTicker;
            }

            if (!(d > (double)this.squaredRange) && this.seeingTargetTicker >= 20) {
                this.actor.getNavigation().stop();
                ++this.combatTicks;
            } else {
                this.actor.getNavigation().startMovingTo(livingEntity, this.speed);
                this.combatTicks = -1;
            }

            double b = this.actor.squaredDistanceTo(livingEntity);
            boolean bl3 = (b > (double)this.squaredRange || this.seeingTargetTicker < 5) && this.chargedTicksLeft == 0;
            if (bl3) {
                --this.cooldown;
                if (this.cooldown <= 0) {
                    this.actor.getNavigation().startMovingTo(livingEntity, this.isUncharged() ? this.speed : this.speed * 0.5);
                    this.cooldown = COOLDOWN_RANGE.get(this.actor.getRandom());
                }
            } else {
                this.cooldown = 0;
                this.actor.getNavigation().stop();
            }
            if (this.combatTicks >= 20) {
                if ((double)this.actor.getRandom().nextFloat() < 0.3) {
                    this.movingToLeft = !this.movingToLeft;
                }

                if ((double)this.actor.getRandom().nextFloat() < 0.3) {
                    this.backward = !this.backward;
                }

                this.combatTicks = 0;
            }

            if (this.combatTicks > -1) {
                if (d > (double)(this.squaredRange * 0.75F)) {
                    this.backward = false;
                } else if (d < (double)(this.squaredRange * 0.25F)) {
                    this.backward = true;
                }

                this.actor.getMoveControl().strafeTo(this.backward ? -0.5F : 0.5F, this.movingToLeft ? 0.5F : -0.5F);
                Entity var7 = this.actor.getControllingVehicle();
                if (var7 instanceof MobEntity) {
                    MobEntity mobEntity = (MobEntity)var7;
                    mobEntity.lookAtEntity(livingEntity, 30.0F, 30.0F);
                }

                this.actor.lookAtEntity(livingEntity, 30.0F, 30.0F);
            } else {
                this.actor.getLookControl().lookAt(livingEntity, 30.0F, 30.0F);
            }

            this.actor.getLookControl().lookAt(livingEntity, 30.0F, 30.0F);
            if (this.stage == DesertedCrossbowAttackGoal.Stage.UNCHARGED) {
                if (!bl3) {
                    this.actor.setCurrentHand(ProjectileUtil.getHandPossiblyHolding(this.actor, Items.CROSSBOW));
                    this.stage = DesertedCrossbowAttackGoal.Stage.CHARGING;
                    ((CrossbowUser)this.actor).setCharging(true);
                }
            } else if (this.stage == DesertedCrossbowAttackGoal.Stage.CHARGING) {
                if (!this.actor.isUsingItem()) {
                    this.stage = DesertedCrossbowAttackGoal.Stage.UNCHARGED;
                }

                int i = this.actor.getItemUseTime();
                ItemStack itemStack = this.actor.getActiveItem();
                if (i >= CrossbowItem.getPullTime(itemStack)) {
                    this.actor.stopUsingItem();
                    this.stage = DesertedCrossbowAttackGoal.Stage.CHARGED;
                    this.chargedTicksLeft = 20 + this.actor.getRandom().nextInt(20);
                    ((CrossbowUser)this.actor).setCharging(false);
                }
            } else if (this.stage == DesertedCrossbowAttackGoal.Stage.CHARGED) {
                --this.chargedTicksLeft;
                if (this.chargedTicksLeft == 0) {
                    this.stage = DesertedCrossbowAttackGoal.Stage.READY_TO_ATTACK;
                }
            } else if (this.stage == DesertedCrossbowAttackGoal.Stage.READY_TO_ATTACK && bl) {
                ((RangedAttackMob)this.actor).attack(livingEntity, 1.0F);
                ItemStack itemStack2 = this.actor.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this.actor, Items.CROSSBOW));
                CrossbowItem.setCharged(itemStack2, false);
                this.stage = DesertedCrossbowAttackGoal.Stage.UNCHARGED;
            }

        }
    }

    private boolean isUncharged() {
        return this.stage == DesertedCrossbowAttackGoal.Stage.UNCHARGED;
    }

    static enum Stage {
        UNCHARGED,
        CHARGING,
        CHARGED,
        READY_TO_ATTACK;

        private Stage() {
        }
    }
}

