package com.sylvernity.horseupgrades.block;

import com.sylvernity.horseupgrades.HorseUpgrades;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.sylvernity.horseupgrades.block.custom.HorseshoeAnvilBlock;
import com.sylvernity.horseupgrades.item.ModCreativeModeTab;
import com.sylvernity.horseupgrades.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HorseUpgrades.MODID);

    public static final RegistryObject<Block> HORSESHOE_ANVIL = registerBlock("horseshoe_anvil", () -> new
            HorseshoeAnvilBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModCreativeModeTab.HORSE_UPGRADES_TAB)));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
