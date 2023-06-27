package net.lightglow.common.entity.projectile;

import net.lightglow.common.registry.SKEntityType;
import net.lightglow.common.registry.SKItemsRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class TidalArrow extends PersistentProjectileEntity {

    private ItemStack stack = ItemStack.EMPTY;

    public TidalArrow(EntityType<? extends TidalArrow> entityType, World world) {
        super(entityType, world);
    }

    public TidalArrow(World world, double x, double y, double z) {
        super(SKEntityType.TIDAL_ARROW, x, y, z, world);
        this.updatePosition(x, y, z);
        this.updateTrackedPosition(x, y, z);
    }

    public TidalArrow(World world, LivingEntity owner) {
        super(SKEntityType.TIDAL_ARROW, owner, world);
        this.stack = stack.copy();
    }


    public void initFromStack(ItemStack stack) {
        stack.isOf(SKItemsRegistry.TIDAL_ARROW);

    }

    protected ItemStack asItemStack() {
            return new ItemStack(SKItemsRegistry.TIDAL_ARROW);
        }

    protected float getDragInWater() {
        return 0.99F;
    }

    public void handleStatus(byte status) {
            super.handleStatus(status);
    }

}

