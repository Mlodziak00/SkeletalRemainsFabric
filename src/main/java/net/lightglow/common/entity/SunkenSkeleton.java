package net.lightglow.common.entity;

import net.lightglow.util.TidalProjectileUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;

public class SunkenSkeleton extends SkeletonEntity {
    boolean targetingUnderwater;
    protected final SwimNavigation waterNavigation;
    protected final MobNavigation landNavigation;
    public SunkenSkeleton(EntityType<? extends SkeletonEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.waterNavigation = new SwimNavigation(this, world);
        this.landNavigation = new MobNavigation(this, world);
    }
    boolean isTargetingUnderwater() {
        if (this.targetingUnderwater) {
            return true;
        } else {
            LivingEntity livingEntity = this.getTarget();
            return livingEntity != null && livingEntity.isTouchingWater();
        }
    }
    public void updateSwimming() {
        if (!this.getWorld().isClient) {
            if (this.canMoveVoluntarily() && this.isTouchingWater() && this.isTargetingUnderwater()) {
                this.navigation = this.waterNavigation;
                this.setSwimming(true);
            } else {
                this.navigation = this.landNavigation;
                this.setSwimming(false);
            }
        }

    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return SkeletonEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.00f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f);
    }
    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
        return TidalProjectileUtil.createArrowProjectile(this, arrow, damageModifier);
    }

    public static boolean canSpawn(EntityType<SunkenSkeleton> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        if (!world.getFluidState(pos.down()).isIn(FluidTags.WATER)) {
            return false;
        } else {
            RegistryEntry<Biome> registryEntry = world.getBiome(pos);
            boolean bl = world.getDifficulty() != Difficulty.PEACEFUL && isSpawnDark(world, pos, random) && (spawnReason == SpawnReason.SPAWNER || world.getFluidState(pos).isIn(FluidTags.WATER));
            if (registryEntry.isIn(BiomeTags.MORE_FREQUENT_DROWNED_SPAWNS)) {
                return random.nextInt(15) == 0 && bl;
            } else {
                return random.nextInt(40) == 0 && isValidSpawnDepth(world, pos) && bl;
            }
        }
    }

    public static boolean isValidSpawnDepth(WorldAccess world, BlockPos pos) {
        return pos.getY() < world.getSeaLevel() - 5;
    }

    public boolean canSpawn(WorldView world) {
        return world.doesNotIntersectEntities(this);
    }

}
