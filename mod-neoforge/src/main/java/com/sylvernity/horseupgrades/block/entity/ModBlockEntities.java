/*
        Horse Upgrades Minecraft Mod
        Copyright (C) 2023 Sylvernity
*/

package com.sylvernity.horseupgrades.block.entity;

import com.sylvernity.horseupgrades.HorseUpgrades;
import com.sylvernity.horseupgrades.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, HorseUpgrades.MOD_ID);

    public static final Supplier<BlockEntityType<HorseshoeAnvilBlockEntity>> HORSESHOE_ANVIL =
            BLOCK_ENTITIES.register("horseshoe_anvil", () ->
                    BlockEntityType.Builder.of(HorseshoeAnvilBlockEntity::new,
                            ModBlocks.HORSESHOE_ANVIL.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
