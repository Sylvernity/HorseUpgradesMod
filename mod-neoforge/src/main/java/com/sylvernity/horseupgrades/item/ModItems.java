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
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.sylvernity.horseupgrades.item.custom.HammerItem;

public class ModItems {

    private ModItems() {
        throw new IllegalStateException("Utility class");
    }

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HorseUpgrades.MOD_ID);

    // Register Horseshoe Bars
    public static final DeferredItem<Item> IRON_HORSESHOE_BAR = ITEMS.registerItem("iron_horseshoe_bar", properties -> new HorseshoeBarItem("iron_horseshoe_bar", new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> GOLDEN_HORSESHOE_BAR = ITEMS.registerItem("golden_horseshoe_bar", properties -> new HorseshoeBarItem("golden_horseshoe_bar", new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> DIAMOND_HORSESHOE_BAR = ITEMS.registerItem("diamond_horseshoe_bar", properties -> new HorseshoeBarItem("diamond_horseshoe_bar", new Item.Properties().stacksTo(16)));

    // Register Horseshoes
    public static final DeferredItem<Item> IRON_HORSESHOE = ITEMS.registerItem("iron_horseshoe", properties -> new HorseshoeItem("iron_horseshoe", (new Item.Properties().stacksTo(1).durability(400)), ArmorMaterials.IRON));
    public static final DeferredItem<Item> GOLDEN_HORSESHOE = ITEMS.registerItem("golden_horseshoe", properties -> new HorseshoeItem("golden_horseshoe", (new Item.Properties().stacksTo(1).durability(50)), ArmorMaterials.GOLD));
    public static final DeferredItem<Item> DIAMOND_HORSESHOE = ITEMS.registerItem("diamond_horseshoe", properties -> new HorseshoeItem("diamond_horseshoe", (new Item.Properties().stacksTo(1).durability(800)), ArmorMaterials.DIAMOND));
    public static final DeferredItem<Item> NETHERITE_HORSESHOE = ITEMS.registerItem("netherite_horseshoe", properties -> new HorseshoeItem("netherite_horseshoe" , (new Item.Properties().stacksTo(1).durability(1000)), ArmorMaterials.NETHERITE));

    // Register Hammers
    public static final DeferredItem<Item> IRON_HAMMER = ITEMS.registerItem("iron_hammer", properties -> new HammerItem((new Item.Properties().stacksTo(1).durability(30)), Material.IRON, Tiers.IRON));
    public static final DeferredItem<Item> GOLDEN_HAMMER = ITEMS.registerItem("golden_hammer", properties -> new HammerItem((new Item.Properties().stacksTo(1).durability(5)), Material.GOLD, Tiers.GOLD));
    public static final DeferredItem<Item> DIAMOND_HAMMER = ITEMS.registerItem("diamond_hammer", properties -> new HammerItem((new Item.Properties().stacksTo(1).durability(50)), Material.DIAMOND, Tiers.DIAMOND));
    public static final DeferredItem<Item> NETHERITE_HAMMER = ITEMS.registerItem("netherite_hammer", properties -> new HammerItem((new Item.Properties().stacksTo(1).durability(80)), Material.NETHERITE, Tiers.NETHERITE));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
