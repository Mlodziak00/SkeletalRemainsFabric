package net.lightglow.common.special;

import net.lightglow.common.entity.projectile.TidalArrow;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TidalArrowItem extends ArrowItem {
    public TidalArrowItem(Settings settings) {
        super(settings);
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        TidalArrow tidalArrow = new TidalArrow(world, shooter);
        tidalArrow.initFromStack(stack);
        return tidalArrow;
    }
}
