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
import org.jetbrains.annotations.Nullable;

public class HorseshoeItem extends AnimalArmorItem {
    private ResourceLocation resourceLocation;

    public HorseshoeItem(String pIdentifier, Item.Properties pProperties, Holder<ArmorMaterial> material){
        this(ResourceLocation.fromNamespaceAndPath("horseupgrades", "textures/entity/horseshoe/" + pIdentifier + ".png"), pProperties, material);
    }
    public HorseshoeItem(ResourceLocation pIdentifier, Item.Properties pProperties, Holder<ArmorMaterial> pMaterial){
        super(pMaterial, BodyType.EQUESTRIAN, true, pProperties);
        this.resourceLocation = pIdentifier;
    }

    @Override
    public @Nullable ResourceLocation getTexture() {
        return this.resourceLocation;
    }

}
