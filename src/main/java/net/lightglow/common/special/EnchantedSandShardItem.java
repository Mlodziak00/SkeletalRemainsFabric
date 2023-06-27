package net.lightglow.common.special;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnchantedSandShardItem extends Item {
    public EnchantedSandShardItem(Settings settings) {
        super(settings);
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
