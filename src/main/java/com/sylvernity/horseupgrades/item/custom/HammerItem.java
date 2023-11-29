package com.sylvernity.horseupgrades.item.custom;

import com.sylvernity.horseupgrades.HorseUpgrades;
import com.sylvernity.horseupgrades.blockstate.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class HammerItem extends TieredItem {
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

    public int getMaterialValue() {
        return toolMaterial.ordinal();
    }


}
