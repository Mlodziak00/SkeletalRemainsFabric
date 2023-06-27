package net.lightglow.common.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.lightglow.SkeletalRemains;
import net.lightglow.common.config.SkeletalConfig;
import net.lightglow.common.config.SkeletalConfigSpawn;
import net.lightglow.common.entity.SunkenSkeleton;
import net.lightglow.common.registry.SKEntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class SKEntitySpawn {


    public static void addEntitySpawn() {

        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), SpawnGroup.MONSTER,
                SKEntityType.OVERGROWN_SKELETON, SkeletalRemains.CONFIG.overgrownweight(), SkeletalRemains.CONFIG.overgrownminspawn(), SkeletalRemains.CONFIG.overgrownmaxspawn());

        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_BADLANDS), SpawnGroup.MONSTER,
                SKEntityType.SHARPSHOOTER_SKELETON, SkeletalRemains.CONFIG.sharpshooterweight(), SkeletalRemains.CONFIG.sharpshooterminspawn() ,SkeletalRemains.CONFIG.sharpshootermaxspawn());

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.NETHER_WASTES), SpawnGroup.MONSTER,
                SKEntityType.CHARRED_SKELETON, SkeletalRemains.CONFIG.charredweight(), SkeletalRemains.CONFIG.charredminspawn(), SkeletalRemains.CONFIG.charredmaxspawn());

        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_OCEAN), SpawnGroup.MONSTER,
                SKEntityType.SUNKEN_SKELETON, SkeletalRemains.CONFIG.sunkenweight(), SkeletalRemains.CONFIG.sunkenminspawn(), SkeletalRemains.CONFIG.sunkenmaxspawn());
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_DEEP_OCEAN), SpawnGroup.MONSTER,
                SKEntityType.SUNKEN_SKELETON, SkeletalRemains.CONFIG.sunkenweight(), SkeletalRemains.CONFIG.sunkenminspawn(), SkeletalRemains.CONFIG.sunkenmaxspawn());

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.MANGROVE_SWAMP), SpawnGroup.MONSTER,
                SKEntityType.SWAMPED_SKELETON, SkeletalRemains.CONFIG.swampedweight(), SkeletalRemains.CONFIG.swampedminspawn(), SkeletalRemains.CONFIG.swampedmaxspawn());
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), SpawnGroup.MONSTER,
                SKEntityType.SWAMPED_SKELETON, SkeletalRemains.CONFIG.swampedweight(), SkeletalRemains.CONFIG.swampedminspawn(), SkeletalRemains.CONFIG.swampedmaxspawn());
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.SWAMP_HUT_HAS_STRUCTURE), SpawnGroup.MONSTER,
                SKEntityType.SWAMPED_SKELETON, SkeletalRemains.CONFIG.swampedweight(), SkeletalRemains.CONFIG.swampedminspawn(), SkeletalRemains.CONFIG.swampedmaxspawn());

        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.DESERT_PYRAMID_HAS_STRUCTURE), SpawnGroup.MONSTER,
                SKEntityType.DESERTED_SKELETON, SkeletalRemains.CONFIG.desertedweight(), SkeletalRemains.CONFIG.swampedminspawn(), SkeletalRemains.CONFIG.swampedmaxspawn());
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.RUINED_PORTAL_DESERT_HAS_STRUCTURE), SpawnGroup.MONSTER,
                SKEntityType.DESERTED_SKELETON, SkeletalRemains.CONFIG.desertedweight(), SkeletalRemains.CONFIG.swampedminspawn(), SkeletalRemains.CONFIG.swampedmaxspawn());
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.VILLAGE_DESERT_HAS_STRUCTURE), SpawnGroup.MONSTER,
                SKEntityType.DESERTED_SKELETON, SkeletalRemains.CONFIG.desertedweight(), SkeletalRemains.CONFIG.swampedminspawn(), SkeletalRemains.CONFIG.swampedmaxspawn());

        SpawnRestriction.register(SKEntityType.OVERGROWN_SKELETON, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
        SpawnRestriction.register(SKEntityType.SHARPSHOOTER_SKELETON, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
        SpawnRestriction.register(SKEntityType.CHARRED_SKELETON, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
        SpawnRestriction.register(SKEntityType.SUNKEN_SKELETON, SpawnRestriction.Location.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SunkenSkeleton::canSpawn);
        SpawnRestriction.register(SKEntityType.SWAMPED_SKELETON, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
    }
}
