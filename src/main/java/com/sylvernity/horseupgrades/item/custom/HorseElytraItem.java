package com.sylvernity.horseupgrades.item.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItem;

public class HorseElytraItem extends HorseArmorItem implements IForgeItem {

    public HorseElytraItem(int pProtection, String pIdentifier, Properties pProperties){
        this(pProtection, new ResourceLocation("horseupgrades", "textures/entity/horseshoe/" + pIdentifier + ".png"), pProperties);
    }
    public HorseElytraItem(int pProtection, ResourceLocation pIdentifier, Properties pProperties){
        super(pProtection, pIdentifier, pProperties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
    }

}
