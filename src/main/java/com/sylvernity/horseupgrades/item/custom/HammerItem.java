/*
        Horse Upgrades Minecraft Mod
        Copyright (C) 2023 Sylvernity
*/

package com.sylvernity.horseupgrades.item.custom;

import com.sylvernity.horseupgrades.HorseUpgrades;
import com.sylvernity.horseupgrades.block.entity.HorseshoeAnvilBlockEntity;
import com.sylvernity.horseupgrades.blockstate.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.common.extensions.IForgeItem;

public class HammerItem extends TieredItem implements IForgeItem {
    public static final EnumProperty<Material> MATERIAL = EnumProperty.create("material", Material.class);

    private Material toolMaterial;

    public HammerItem(Properties pProperties, Material pMaterial, Tier pTier){
        this(pTier, pProperties);
        toolMaterial = pMaterial;
    }

    private HammerItem(Tier pTier, Item.Properties pProperties) {
        super(pTier, pProperties);
    }

    public String getMaterial() {
        HorseUpgrades.LOGGER.info("This tool has the {} Tier.", this.getTier());
        return toolMaterial.getSerializedName();
    }

    // Prevent Hammer from breaking Horseshoe Anvil
    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        return player.getLevel().getBlockEntity(pos) instanceof HorseshoeAnvilBlockEntity;
    }

    public int getMaterialValue() {
        return toolMaterial.ordinal();
    }


}
