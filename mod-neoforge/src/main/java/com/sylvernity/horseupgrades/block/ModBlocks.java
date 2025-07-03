/*
        Horse Upgrades Minecraft Mod
        Copyright (C) 2023 Sylvernity
*/

package com.sylvernity.horseupgrades.block;

import com.sylvernity.horseupgrades.HorseUpgrades;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.sylvernity.horseupgrades.block.custom.HorseshoeAnvilBlock;
import com.sylvernity.horseupgrades.item.ModItems;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(HorseUpgrades.MOD_ID);

    public static final DeferredBlock<Block> HORSESHOE_ANVIL = registerBlock("horseshoe_anvil", (properties) -> new
            HorseshoeAnvilBlock(properties.strength(6f).requiresCorrectToolForDrops()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), new Item.Properties().setNoRepair()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
