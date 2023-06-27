package net.lightglow;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.lightglow.common.config.SkeletalConfig;
import net.lightglow.common.config.SkeletalConfigSpawn;
import net.lightglow.common.entity.*;
import net.lightglow.common.gen.SKWorldGen;
import net.lightglow.common.registry.SKEntityType;
import net.lightglow.common.registry.SKItemsRegistry;
import net.lightglow.util.SkeletalModelPredicateProvider;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkeletalRemains implements ModInitializer {

	public static final SkeletalConfigSpawn CONFIG = SkeletalConfigSpawn.createAndLoad();

	public static final String MOD_ID = "skeletalremains";
	public static final Logger LOGGER = LoggerFactory.getLogger("skeletalremains");

	private static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "skeletalremains"));

	@Override
	public void onInitialize() {


		LOGGER.info("Hello Fabric world!");

		SKWorldGen.genWorldGen();

		SKItemsRegistry.registerSKItems();

		Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, FabricItemGroup.builder()
				.displayName(Text.literal("Skeletal Remains"))
				.icon(() -> new ItemStack(Items.BONE))
				.entries((context, entries) -> {
					entries.add(SKItemsRegistry.WILL_O_BOW);
					entries.add(SKItemsRegistry.SAND_BAIN);
					entries.add(SKItemsRegistry.TIDAL_ARROW);
					entries.add(SKItemsRegistry.ENCHANTED_SAND_SHARD);
					entries.add(SKItemsRegistry.OVERGROWN_SPAWN_EGG);
					entries.add(SKItemsRegistry.SHARPSHOOTER_SPAWN_EGG);
					entries.add(SKItemsRegistry.CHARRED_SPAWN_EGG);
					entries.add(SKItemsRegistry.SUNKEN_SPAWN_EGG);
					entries.add(SKItemsRegistry.FALLEN_SPAWN_EGG);
					entries.add(SKItemsRegistry.SWAMPED_SPAWN_EGG);
					entries.add(SKItemsRegistry.DESERTED_SPAWN_EGG);
				})
				.build()
		);

		FabricDefaultAttributeRegistry.register(SKEntityType.OVERGROWN_SKELETON, OvergrownSkeleton.setAttributes());
		FabricDefaultAttributeRegistry.register(SKEntityType.SHARPSHOOTER_SKELETON, SharpshooterSkeleton.setAttributes());
		FabricDefaultAttributeRegistry.register(SKEntityType.CHARRED_SKELETON, CharredSkeleton.setAttributes());
		FabricDefaultAttributeRegistry.register(SKEntityType.SUNKEN_SKELETON, SunkenSkeleton.setAttributes());
		FabricDefaultAttributeRegistry.register(SKEntityType.FALLEN_SKELETON, FallenSkeleton.setAttributes());
		FabricDefaultAttributeRegistry.register(SKEntityType.SWAMPED_SKELETON, SwampedSkeleton.setAttributes());
		FabricDefaultAttributeRegistry.register(SKEntityType.DESERTED_SKELETON, DesertedSkeleton.setAttributes());

	}
	public static Identifier createEntityId(String path) {
		return new Identifier(MOD_ID, path);
	}
}