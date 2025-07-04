/*
        Horse Upgrades Minecraft Mod
        Copyright (C) 2023 Sylvernity
*/

package com.sylvernity.horseupgrades.item;

import com.sylvernity.horseupgrades.HorseUpgrades;
import com.sylvernity.horseupgrades.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

//@EventBusSubscriber(modid = HorseUpgrades.MOD_ID)
public class ModCreativeModeTab {
    private static DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HorseUpgrades.MOD_ID);

    //CREATIVE_MODE_TABS is a DeferredRegister<CreativeModeTab>
    public static final Supplier<CreativeModeTab> HORSE_UPGRADES_TAB = CREATIVE_MODE_TABS.register("horseupgrades", () -> CreativeModeTab.builder()
            //Set the title of the tab. Don't forget to add a translation!
            .title(Component.translatable("itemGroup." + HorseUpgrades.MOD_ID))
            //Set the icon of the tab.
            .icon(() -> new ItemStack(ModItems.IRON_HORSESHOE.get()))
            //Add your items to the tab.
            .displayItems((params, output) -> {
                output.accept(new ItemStack(ModBlocks.HORSESHOE_ANVIL.get()));

                output.accept(new ItemStack(ModItems.IRON_HORSESHOE_BAR.get()));
                output.accept(new ItemStack(ModItems.GOLDEN_HORSESHOE_BAR.get()));
                output.accept(new ItemStack(ModItems.DIAMOND_HORSESHOE_BAR.get()));

                output.accept(new ItemStack(ModItems.IRON_HORSESHOE.get()));
                output.accept(new ItemStack(ModItems.GOLDEN_HORSESHOE.get()));
                output.accept(new ItemStack(ModItems.DIAMOND_HORSESHOE.get()));
                output.accept(new ItemStack(ModItems.NETHERITE_HORSESHOE.get()));

                output.accept(new ItemStack(ModItems.IRON_HAMMER.get()));
                output.accept(new ItemStack(ModItems.GOLDEN_HAMMER.get()));
                output.accept(new ItemStack(ModItems.DIAMOND_HAMMER.get()));
                output.accept(new ItemStack(ModItems.NETHERITE_HAMMER.get()));
            })
            .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
