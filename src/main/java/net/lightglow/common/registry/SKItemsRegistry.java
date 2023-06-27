package net.lightglow.common.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lightglow.SkeletalRemains;
import net.lightglow.common.special.*;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class SKItemsRegistry {

    public static final Item TIDAL_ARROW = registerItem("tidal_arrow",
            new TidalArrowItem(new FabricItemSettings()));

    public static final Item OVERGROWN_SPAWN_EGG = registerItem("overgrown_spawn_egg",
            new SpawnEggItem(SKEntityType.OVERGROWN_SKELETON, 0x6e8267, 0x589946,
                    new FabricItemSettings()));
    public static final Item SHARPSHOOTER_SPAWN_EGG = registerItem("sharpshooter_spawn_egg",
            new SpawnEggItem(SKEntityType.SHARPSHOOTER_SKELETON, 0x54543f, 0x452d1b,
                    new FabricItemSettings()));
    public static final Item CHARRED_SPAWN_EGG = registerItem("charred_spawn_egg",
            new SpawnEggItem(SKEntityType.CHARRED_SKELETON, 0x545454, 0x171717,
                    new FabricItemSettings()));
    public static final Item SUNKEN_SPAWN_EGG = registerItem("sunken_spawn_egg",
            new SpawnEggItem(SKEntityType.SUNKEN_SKELETON, 0x354747, 0x257a7a,
                    new FabricItemSettings()));
    public static final Item FALLEN_SPAWN_EGG = registerItem("fallen_spawn_egg",
            new SpawnEggItem(SKEntityType.FALLEN_SKELETON, 0x7a7a85, 0xb4b4d4,
                    new FabricItemSettings()));

    public static final Item SWAMPED_SPAWN_EGG = registerItem("swamped_spawn_egg",
            new SpawnEggItem(SKEntityType.SWAMPED_SKELETON, 0x212912, 0x1a6117,
                    new FabricItemSettings()));
    public static final Item DESERTED_SPAWN_EGG = registerItem("deserted_spawn_egg",
            new SpawnEggItem(SKEntityType.DESERTED_SKELETON, 0x5f4e46, 0xc9c2af,
                    new FabricItemSettings()));

    public static final Item WILL_O_BOW = registerItem("will_o_bow",
            new WillOBowItem(new FabricItemSettings().maxCount(1).maxDamage(468)));

    public static final Item ENCHANTED_SAND_SHARD = registerItem("enchanted_sand_shard",
            new EnchantedSandShardItem(new FabricItemSettings().maxCount(64)));

    public static final Item SAND_BAIN = registerItem("sand_bane",
            new SandBaneItem(SandMaterial.SAND, 3, -2f, new FabricItemSettings().maxCount(1).maxDamage(256).rarity(Rarity.UNCOMMON)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SkeletalRemains.MOD_ID, name), item);
    }

    public static void registerSKItems() {
        SkeletalRemains.LOGGER.debug("Registering Some Bones for " + SkeletalRemains.MOD_ID);
    }
}
