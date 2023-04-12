package com.sylvernity.horseupgrades.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab HORSE_UPGRADES_TAB = new CreativeModeTab("horseupgradestab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.NETHERITE_HORSESHOE.get());
        }
    };
}
