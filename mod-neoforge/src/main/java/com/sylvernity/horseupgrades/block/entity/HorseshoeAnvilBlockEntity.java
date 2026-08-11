/*
        Horse Upgrades Minecraft Mod
        Copyright (C) 2023 Sylvernity
*/

package com.sylvernity.horseupgrades.block.entity;

import com.sylvernity.horseupgrades.block.custom.HorseshoeAnvilBlock;
import com.sylvernity.horseupgrades.blockstate.Holding;
import com.sylvernity.horseupgrades.blockstate.Material;
import com.sylvernity.horseupgrades.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.items.ItemStackHandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.sylvernity.horseupgrades.block.custom.HorseshoeAnvilBlock.HOLDING;
import static com.sylvernity.horseupgrades.block.custom.HorseshoeAnvilBlock.MATERIAL;

public class HorseshoeAnvilBlockEntity extends BlockEntity {

    private final ItemStackHandler inventory = new ItemStackHandler(1){
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                updateVisualBlockState();
            }
        }
    };

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public HorseshoeAnvilBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.HORSESHOE_ANVIL.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> HorseshoeAnvilBlockEntity.this.progress;
                    case 1 -> HorseshoeAnvilBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> HorseshoeAnvilBlockEntity.this.progress = pValue;
                    case 1 -> HorseshoeAnvilBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 0;
            }
        };
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider registries) {
        pTag.put("inventory", inventory.serializeNBT(registries));
        super.saveAdditional(pTag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag pTag, HolderLookup.Provider registries) {
        super.loadAdditional(pTag, registries);
        inventory.deserializeNBT(registries, pTag.getCompound("inventory"));
    }

    // Convert item in inventory to blockstate Holding property
    private Holding getHoldingType(@NotNull ItemStack stack) {
        if (stack.is(ModItems.IRON_HORSESHOE_BAR.get())
                || stack.is(ModItems.GOLDEN_HORSESHOE_BAR.get())
                || stack.is(ModItems.DIAMOND_HORSESHOE_BAR.get())) {
            return Holding.BAR;
        }

        if (stack.is(ModItems.IRON_HORSESHOE.get())
                || stack.is(ModItems.GOLDEN_HORSESHOE.get())
                || stack.is(ModItems.DIAMOND_HORSESHOE.get())) {
            return Holding.HORSESHOE;
        }

        return Holding.NONE;
    }

    // Convert item in inventory to blockstate Material property
    private static Material getMaterialType(@NotNull ItemStack stack) {
        if (stack.is(ModItems.IRON_HORSESHOE_BAR.get())
                || stack.is(ModItems.IRON_HORSESHOE.get())) {
            return Material.IRON;
        }

        if (stack.is(ModItems.GOLDEN_HORSESHOE_BAR.get())
                || stack.is(ModItems.GOLDEN_HORSESHOE.get())) {
            return Material.GOLD;
        }

        if (stack.is(ModItems.DIAMOND_HORSESHOE_BAR.get())
                || stack.is(ModItems.DIAMOND_HORSESHOE.get())) {
            return Material.DIAMOND;
        }

        return Material.NONE;
    }

    // Update blockstate by item in inventory
    private void updateVisualBlockState() {
        ItemStack stack = inventory.getStackInSlot(0);

        Holding holding = getHoldingType(stack);
        Material material = getMaterialType(stack);

        BlockState oldState = getBlockState();

        BlockState newState = oldState.setValue(HOLDING, holding).setValue(MATERIAL, material);

        if (!newState.equals(oldState)) {
            level.setBlockAndUpdate(worldPosition, newState);
        }
    }

    public ItemStack retrieveContent() {
        ItemStack currentContent = this.inventory.getStackInSlot(0);
        this.inventory.setStackInSlot(0, ItemStack.EMPTY);
        return currentContent;
    }

    public void setContent(ItemStack pStack) {
        this.inventory.setStackInSlot(0, pStack);
    }

    public void upgradeBar() {
        ItemStack stack = this.inventory.getStackInSlot(0);
        Item horseshoe = null;

        if (stack.is(ModItems.IRON_HORSESHOE_BAR.get())) {
            horseshoe = ModItems.IRON_HORSESHOE.get();
        }

        if (stack.is(ModItems.GOLDEN_HORSESHOE_BAR.get())) {
            horseshoe = ModItems.GOLDEN_HORSESHOE.get();
        }

        if (stack.is(ModItems.DIAMOND_HORSESHOE_BAR.get())) {
            horseshoe = ModItems.DIAMOND_HORSESHOE.get();
        }

        this.inventory.setStackInSlot(0, new ItemStack(horseshoe));
    }

    public void dropContents() {
        if (level == null || level.isClientSide) {
            return;
        }

        ItemStack stack = inventory.getStackInSlot(0);

        if (!stack.isEmpty()) {
            Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), stack.copy());
        }
    }
}
