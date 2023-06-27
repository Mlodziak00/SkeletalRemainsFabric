package net.lightglow.common.entity;

import net.lightglow.common.entity.goals.DesertedCrossbowAttackGoal;
import net.lightglow.common.registry.SKItemsRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

import java.util.Map;

public class DesertedSkeleton extends SkeletonEntity implements CrossbowUser {
    private static final TrackedData<Boolean> CHARGING;


    public DesertedSkeleton(EntityType<? extends SkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(3, new DesertedCrossbowAttackGoal<>(this, 1.0, 8.0F));
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return SkeletonEntity.createMobAttributes()
                    .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.50f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.5f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(CHARGING, false);
    }

    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        int randomNumber = (int) (Math.random() * 4) + 1;
        if (randomNumber <= 3){
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
        } else if (randomNumber == 4) {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(SKItemsRegistry.SAND_BAIN));
        }
    }

    protected void enchantMainHandItem(Random random, float power) {
        super.enchantMainHandItem(random, power);
        ItemStack itemStack = this.getMainHandStack();
        if (itemStack.isOf(SKItemsRegistry.SAND_BAIN)) {
            Map<Enchantment, Integer> map = EnchantmentHelper.get(itemStack);
            map.putIfAbsent(Enchantments.SMITE, 6);
            EnchantmentHelper.set(map, itemStack);
            this.equipStack(EquipmentSlot.MAINHAND, itemStack);
        }
    }
    public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
        return weapon == Items.CROSSBOW;
    }

    public boolean isCharging() {
        return (Boolean)this.dataTracker.get(CHARGING);
    }

    @Override
    public void setCharging(boolean charging) {
        this.dataTracker.set(CHARGING, charging);
    }


    @Override
    public void shoot(LivingEntity target, ItemStack crossbow, ProjectileEntity projectile, float multiShotSpray) {
        this.shoot(this, target, projectile, multiShotSpray, 1.6F);
    }

    @Override
    public void postShoot() {
        this.despawnCounter = 0;
    }
    static {
        CHARGING = DataTracker.registerData(PillagerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    public DesertedSkeleton.State getState() {
        if (this.isCharging()) {
            return DesertedSkeleton.State.CROSSBOW_CHARGE;
        } else if (this.isHolding(Items.CROSSBOW)) {
            return DesertedSkeleton.State.CROSSBOW_HOLD;
        } else {
            return this.isAttacking() ? DesertedSkeleton.State.ATTACKING : DesertedSkeleton.State.NEUTRAL;
        }
    }


    public static enum State {
        CROSSED,
        ATTACKING,
        NEUTRAL,
        CROSSBOW_HOLD,
        CROSSBOW_CHARGE;

        private State() {
        }
    }
}
