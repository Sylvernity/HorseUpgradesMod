/*
        Horse Upgrades Minecraft Mod
        Copyright (C) 2023 Sylvernity
*/

package com.sylvernity.horseupgrades.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.Item;

public class HorseshoeItem extends AnimalArmorItem {
    public HorseshoeItem(String pIdentifier, Item.Properties pProperties, Holder<ArmorMaterial> material){
        this(0, ResourceLocation.fromNamespaceAndPath("horseupgrades", "textures/entity/horseshoe/" + pIdentifier + ".png"), pProperties, material);
    }
    public HorseshoeItem(int pProtection, ResourceLocation pIdentifier, Item.Properties pProperties, Holder<ArmorMaterial> pMaterial){
        super(pMaterial, BodyType.EQUESTRIAN, true, pProperties);
    }
}
