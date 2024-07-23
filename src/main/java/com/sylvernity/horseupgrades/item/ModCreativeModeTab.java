/*
        Horse Upgrades Minecraft Mod
        Copyright (C) 2023 Sylvernity
*/

package com.sylvernity.horseupgrades.item;

import com.sylvernity.horseupgrades.HorseUpgrades;
import com.sylvernity.horseupgrades.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HorseUpgrades.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    private static CreativeModeTab horseUpgradesTab;

    @SubscribeEvent
    public static void onCreativeModeTabRegister(CreativeModeTabEvent.Register event) {
        horseUpgradesTab = event.registerCreativeModeTab(new ResourceLocation(HorseUpgrades.MODID, "horseupgrades"),
                builder -> builder.icon(() -> new ItemStack(ModItems.NETHERITE_HORSESHOE.get()))
                .title(Component.translatable("creativemodetab.horseupgrades"))
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
                }));
    }

    @SubscribeEvent
    public static void onCreativeModeTabBuildContents(CreativeModeTabEvent.BuildContents event) {
        var entries = event.getEntries();
        var visibility = CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;
        if (event.getTab() == horseUpgradesTab) {
            event.accept(new ItemStack(ModBlocks.HORSESHOE_ANVIL.get()));

            event.accept(new ItemStack(ModItems.IRON_HORSESHOE_BAR.get()));
            event.accept(new ItemStack(ModItems.GOLDEN_HORSESHOE_BAR.get()));
            event.accept(new ItemStack(ModItems.DIAMOND_HORSESHOE_BAR.get()));

            event.accept(new ItemStack(ModItems.IRON_HORSESHOE.get()));
            event.accept(new ItemStack(ModItems.GOLDEN_HORSESHOE.get()));
            event.accept(new ItemStack(ModItems.DIAMOND_HORSESHOE.get()));
            event.accept(new ItemStack(ModItems.NETHERITE_HORSESHOE.get()));

            event.accept(new ItemStack(ModItems.IRON_HAMMER.get()));
            event.accept(new ItemStack(ModItems.GOLDEN_HAMMER.get()));
            event.accept(new ItemStack(ModItems.DIAMOND_HAMMER.get()));
            event.accept(new ItemStack(ModItems.NETHERITE_HAMMER.get()));
        }
    }
}
