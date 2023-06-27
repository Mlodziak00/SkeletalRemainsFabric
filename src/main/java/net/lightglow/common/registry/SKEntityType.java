package net.lightglow.common.registry;


import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.lightglow.SkeletalRemains;
import net.lightglow.common.entity.*;
import net.lightglow.common.entity.projectile.TidalArrow;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class SKEntityType {
    public static final EntityType<OvergrownSkeleton> OVERGROWN_SKELETON = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SkeletalRemains.MOD_ID, "overgrownskeleton"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, OvergrownSkeleton::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 2.0f)).build());

    public static final EntityType<SharpshooterSkeleton> SHARPSHOOTER_SKELETON = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SkeletalRemains.MOD_ID, "sharpshooterskeleton"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SharpshooterSkeleton::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 2.0f)).build());
    public static final EntityType<SunkenSkeleton> SUNKEN_SKELETON = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SkeletalRemains.MOD_ID, "sunkenskeleton"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SunkenSkeleton::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 2.0f)).build());
    public static final EntityType<CharredSkeleton> CHARRED_SKELETON = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SkeletalRemains.MOD_ID, "charredskeleton"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CharredSkeleton::new)
                    .fireImmune().dimensions(EntityDimensions.fixed(0.8f, 2.2f)).build());

    public static final EntityType<FallenSkeleton> FALLEN_SKELETON = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SkeletalRemains.MOD_ID, "fallenskeleton"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, FallenSkeleton::new)
                    .fireImmune().dimensions(EntityDimensions.fixed(0.8f, 2.2f)).build());

    public static final EntityType<SwampedSkeleton> SWAMPED_SKELETON = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SkeletalRemains.MOD_ID, "swampedskeleton"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SwampedSkeleton::new)
                    .fireImmune().dimensions(EntityDimensions.fixed(0.75f, 2.0f)).build());

    public static final EntityType<DesertedSkeleton> DESERTED_SKELETON = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SkeletalRemains.MOD_ID, "desertedskeleton"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, DesertedSkeleton::new)
                    .fireImmune().dimensions(EntityDimensions.fixed(0.75f, 2.0f)).build());



    public static final EntityType<TidalArrow> TIDAL_ARROW = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SkeletalRemains.MOD_ID, "tidalarrow"),
            FabricEntityTypeBuilder.<TidalArrow>create(SpawnGroup.MISC, TidalArrow::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackable(128, 4
                    ).build());
}

