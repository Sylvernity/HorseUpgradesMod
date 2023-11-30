/*
        Horse Upgrades Minecraft Mod
        Copyright (C) 2023 Sylvernity
*/

package com.sylvernity.horseupgrades.item;

import com.sylvernity.horseupgrades.HorseUpgrades;
import com.sylvernity.horseupgrades.blockstate.Material;
import com.sylvernity.horseupgrades.item.custom.HorseshoeBarItem;
import com.sylvernity.horseupgrades.item.custom.HorseshoeItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.sylvernity.horseupgrades.item.custom.HammerItem;

public class ModItems {

    private ModItems() {
        throw new IllegalStateException("Utility class");
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HorseUpgrades.MODID);

    // Register Horseshoe Bars
    public static final RegistryObject<Item> IRON_HORSESHOE_BAR = ITEMS.register("iron_horseshoe_bar", () -> new HorseshoeBarItem("iron_horseshoe_bar", new Item.Properties().stacksTo(16).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB)));
    public static final RegistryObject<Item> GOLDEN_HORSESHOE_BAR = ITEMS.register("golden_horseshoe_bar", () -> new HorseshoeBarItem("golden_horseshoe_bar", new Item.Properties().stacksTo(16).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB)));
    public static final RegistryObject<Item> DIAMOND_HORSESHOE_BAR = ITEMS.register("diamond_horseshoe_bar", () -> new HorseshoeBarItem("diamond_horseshoe_bar", new Item.Properties().stacksTo(16).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB)));

    // Register Horseshoes
    public static final RegistryObject<Item> IRON_HORSESHOE = ITEMS.register("iron_horseshoe", () -> new HorseshoeItem("iron_horseshoe", (new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB).durability(400)), ArmorMaterials.IRON));
    public static final RegistryObject<Item> GOLDEN_HORSESHOE = ITEMS.register("golden_horseshoe", () -> new HorseshoeItem("golden_horseshoe", (new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB).durability(50)), ArmorMaterials.GOLD));
    public static final RegistryObject<Item> DIAMOND_HORSESHOE = ITEMS.register("diamond_horseshoe", () -> new HorseshoeItem("diamond_horseshoe", (new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB).durability(800)), ArmorMaterials.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_HORSESHOE = ITEMS.register("netherite_horseshoe", () -> new HorseshoeItem("netherite_horseshoe" , (new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB).durability(1000)), ArmorMaterials.NETHERITE));

    // Register Hammers
    public static final RegistryObject<Item> IRON_HAMMER = ITEMS.register("iron_hammer", () -> new HammerItem((new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB).durability(30)), Material.IRON, Tiers.IRON));
    public static final RegistryObject<Item> GOLDEN_HAMMER = ITEMS.register("golden_hammer", () -> new HammerItem((new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB).durability(5)), Material.GOLD, Tiers.GOLD));
    public static final RegistryObject<Item> DIAMOND_HAMMER = ITEMS.register("diamond_hammer", () -> new HammerItem((new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB).durability(50)), Material.DIAMOND, Tiers.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_HAMMER = ITEMS.register("netherite_hammer", () -> new HammerItem((new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB).durability(80)), Material.NETHERITE, Tiers.NETHERITE));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
