package net.lightglow.util;

import net.lightglow.common.registry.SKItemsRegistry;
import net.lightglow.common.special.TidalArrowItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;

public class TidalProjectileUtil {
    public TidalProjectileUtil() {
    }

    public static HitResult getCollision(Entity entity, Predicate<Entity> predicate) {
        Vec3d vec3d = entity.getVelocity();
        World world = entity.getWorld();
        Vec3d vec3d2 = entity.getPos();
        Vec3d vec3d3 = vec3d2.add(vec3d);
        HitResult hitResult = world.raycast(new RaycastContext(vec3d2, vec3d3, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, entity));
        if (((HitResult)hitResult).getType() != HitResult.Type.MISS) {
            vec3d3 = ((HitResult)hitResult).getPos();
        }

        HitResult hitResult2 = getEntityCollision(world, entity, vec3d2, vec3d3, entity.getBoundingBox().stretch(entity.getVelocity()).expand(1.0), predicate);
        if (hitResult2 != null) {
            hitResult = hitResult2;
        }

        return (HitResult)hitResult;
    }

    @Nullable
    public static EntityHitResult raycast(Entity entity, Vec3d min, Vec3d max, Box box, Predicate<Entity> predicate, double d) {
        World world = entity.getWorld();
        double e = d;
        Entity entity2 = null;
        Vec3d vec3d = null;
        Iterator var12 = world.getOtherEntities(entity, box, predicate).iterator();

        while(true) {
            while(var12.hasNext()) {
                Entity entity3 = (Entity)var12.next();
                Box box2 = entity3.getBoundingBox().expand((double)entity3.getTargetingMargin());
                Optional<Vec3d> optional = box2.raycast(min, max);
                if (box2.contains(min)) {
                    if (e >= 0.0) {
                        entity2 = entity3;
                        vec3d = (Vec3d)optional.orElse(min);
                        e = 0.0;
                    }
                } else if (optional.isPresent()) {
                    Vec3d vec3d2 = (Vec3d)optional.get();
                    double f = min.squaredDistanceTo(vec3d2);
                    if (f < e || e == 0.0) {
                        if (entity3.getRootVehicle() == entity.getRootVehicle()) {
                            if (e == 0.0) {
                                entity2 = entity3;
                                vec3d = vec3d2;
                            }
                        } else {
                            entity2 = entity3;
                            vec3d = vec3d2;
                            e = f;
                        }
                    }
                }
            }

            if (entity2 == null) {
                return null;
            }

            return new EntityHitResult(entity2, vec3d);
        }
    }

    @Nullable
    public static EntityHitResult getEntityCollision(World world, Entity entity, Vec3d min, Vec3d max, Box box, Predicate<Entity> predicate) {
        return getEntityCollision(world, entity, min, max, box, predicate, 0.3F);
    }

    @Nullable
    public static EntityHitResult getEntityCollision(World world, Entity entity, Vec3d min, Vec3d max, Box box, Predicate<Entity> predicate, float f) {
        double d = Double.MAX_VALUE;
        Entity entity2 = null;
        Iterator var10 = world.getOtherEntities(entity, box, predicate).iterator();

        while(var10.hasNext()) {
            Entity entity3 = (Entity)var10.next();
            Box box2 = entity3.getBoundingBox().expand((double)f);
            Optional<Vec3d> optional = box2.raycast(min, max);
            if (optional.isPresent()) {
                double e = min.squaredDistanceTo((Vec3d)optional.get());
                if (e < d) {
                    entity2 = entity3;
                    d = e;
                }
            }
        }

        if (entity2 == null) {
            return null;
        } else {
            return new EntityHitResult(entity2);
        }
    }

    public static void setRotationFromVelocity(Entity entity, float delta) {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.lengthSquared() != 0.0) {
            double d = vec3d.horizontalLength();
            entity.setYaw((float)(MathHelper.atan2(vec3d.z, vec3d.x) * 57.2957763671875) + 90.0F);
            entity.setPitch((float)(MathHelper.atan2(d, vec3d.y) * 57.2957763671875) - 90.0F);

            while(entity.getPitch() - entity.prevPitch < -180.0F) {
                entity.prevPitch -= 360.0F;
            }

            while(entity.getPitch() - entity.prevPitch >= 180.0F) {
                entity.prevPitch += 360.0F;
            }

            while(entity.getYaw() - entity.prevYaw < -180.0F) {
                entity.prevYaw -= 360.0F;
            }

            while(entity.getYaw() - entity.prevYaw >= 180.0F) {
                entity.prevYaw += 360.0F;
            }

            entity.setPitch(MathHelper.lerp(delta, entity.prevPitch, entity.getPitch()));
            entity.setYaw(MathHelper.lerp(delta, entity.prevYaw, entity.getYaw()));
        }
    }

    public static Hand getHandPossiblyHolding(LivingEntity entity, Item item) {
        return entity.getMainHandStack().isOf(item) ? Hand.MAIN_HAND : Hand.OFF_HAND;
    }

    public static PersistentProjectileEntity createArrowProjectile(LivingEntity entity, ItemStack stack, float damageModifier) {
        TidalArrowItem tidalArrowItem = (TidalArrowItem)(stack.getItem() instanceof TidalArrowItem ? stack.getItem() : SKItemsRegistry.TIDAL_ARROW);
        PersistentProjectileEntity persistentProjectileEntity = tidalArrowItem.createArrow(entity.getWorld(), stack, entity);
        persistentProjectileEntity.applyEnchantmentEffects(entity, damageModifier);

        return persistentProjectileEntity;
    }
}

