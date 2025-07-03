/*
        Horse Upgrades Minecraft Mod
        Copyright (C) 2023 Sylvernity
*/

package com.sylvernity.horseupgrades.event;

import com.sylvernity.horseupgrades.HorseUpgrades;
import com.sylvernity.horseupgrades.block.custom.HorseshoeAnvilBlock;
import com.sylvernity.horseupgrades.block.entity.HorseshoeAnvilBlockEntity;
import com.sylvernity.horseupgrades.blockstate.Holding;
import com.sylvernity.horseupgrades.item.custom.HammerItem;
import com.sylvernity.horseupgrades.item.custom.HorseshoeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import com.sylvernity.horseupgrades.blockstate.Material;

import java.util.Objects;
@EventBusSubscriber(modid = HorseUpgrades.MOD_ID)
public class ModEvents {

    private static AttributeModifier attributeModifier;
    private static boolean clickedInitial = false;
    private static int tickCounter = 0;
    private static BlockPos blockPos;
    private static int stepCounter = 0;

    // Run this when equipment has been changed for an entity. Adds speed modifier when horseshoe added
    @SubscribeEvent
    public static void addHorseshoeBonusSpeed(LivingEquipmentChangeEvent event){
        if(!event.getEntity().level().isClientSide()) {
            // If the entity is a horse
            if (event.getEntity() instanceof Horse entity) {
                // If the equipment in the horse's chest/armor slot was changed
                if (event.getSlot() == EquipmentSlot.CHEST) {
                    int bonus = 0;

                    // When old item in slot is horseshoe, remove old speed bonus
                    if (event.getFrom().getItem() instanceof HorseshoeItem){
                        Objects.requireNonNull(entity.getAttribute(Attributes.MOVEMENT_SPEED)).removeModifier(attributeModifier);
                    }
                    // When new item in slot is horseshoe, add new speed bonus
                    if (event.getTo().getItem() instanceof HorseshoeItem newHorseshoe){
                        Holder<ArmorMaterial> material = newHorseshoe.getMaterial();
                        if (material == ArmorMaterials.IRON || material == ArmorMaterials.GOLD) {
                            bonus = 700;
                        } else if (material == ArmorMaterials.DIAMOND) {
                            bonus = 1000;
                        } else if (material == ArmorMaterials.NETHERITE) {
                            bonus = 1200;
                        }
                        /* Uncomment to prevent horses from passing natural maximum speed
                        * if (speed + bonus > 3375) {
                        *   bonus = 3375 - speed;
                        * }
                        * */
                        ResourceLocation attributeLocation = ResourceLocation.fromNamespaceAndPath("horseupgrades", "speed_boost");
                        attributeModifier = new AttributeModifier(attributeLocation, intToFloat(bonus), AttributeModifier.Operation.ADD_VALUE);
                        Objects.requireNonNull(entity.getAttribute(Attributes.MOVEMENT_SPEED)).addTransientModifier(attributeModifier);
                    }

                }

            }
        }
    }

    // Run this when player left clicks block. Changes static global variables when hammer clicks anvil
    @SubscribeEvent
    public static void startHammering(PlayerInteractEvent.LeftClickBlock event) {
        if (!event.getEntity().level().isClientSide()) {
            // If player is holding hammer
            Item itemInUse = event.getItemStack().getItem();
            if (itemInUse instanceof HammerItem) {
                // If block used on is a horseshoe anvil
                if (event.getLevel().getBlockEntity(event.getPos()) instanceof HorseshoeAnvilBlockEntity) {
                    // If anvil has a horseshoe bar with material value lower or equal to hammer
                    BlockState anvilBlockState = event.getLevel().getBlockState(event.getPos());
                    if (anvilBlockState.getValue(HorseshoeAnvilBlock.HOLDING) == Holding.BAR){
                        if (anvilBlockState.getValue(HorseshoeAnvilBlock.MATERIAL) != Material.DIAMOND || ((HammerItem) itemInUse).getMaterial().equals("diamond") || ((HammerItem) itemInUse).getMaterial().equals("netherite")) {
                            // Hammer has hit anvil
                            clickedInitial = true;

                            // Get position of anvil hit
                            blockPos = event.getPos();

                            // Play Anvil working sound
                            event.getLevel().playSound((Player)null, event.getPos(), SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                        }
                    }
                }
            }
        }
    }

    // Run this every tick when startHammer changes global variables
    @SubscribeEvent
    public static void onTickHammer(PlayerTickEvent.Post event) {
        ItemStack itemStackUsed = event.getEntity().getItemInHand(event.getEntity().getUsedItemHand());
        Item itemInUse = itemStackUsed.getItem();

        // If the player is swinging a Hammer
        if (event.getEntity().swinging && itemInUse instanceof HammerItem){
            Level level = event.getEntity().level();
            int ticksNeeded = 2250;

            // Determine the amount of hammering required based on hammer tier
            ticksNeeded /= (int) ((HammerItem) itemInUse).getTier().getSpeed();

            // If the initial click was on a horseshoe anvil and it hasn't been long enough of constant swinging, increment by 1
            if (clickedInitial && tickCounter < ticksNeeded){
                tickCounter ++;
            }

            // If the hammer has been swinging at the anvil long enough, change the blockstate of the anvil to a horseshoe
            else if (tickCounter == ticksNeeded) {
                clickedInitial = false;
                tickCounter = 0;

                // Change blockstate and play anvil sound
                HorseshoeAnvilBlockEntity anvilBlock = (HorseshoeAnvilBlockEntity) event.getEntity().level().getBlockEntity(blockPos);
                Material prevMaterial = anvilBlock.getBlockState().getValue(HorseshoeAnvilBlock.MATERIAL);
                level.setBlock(blockPos, level.getBlockState(blockPos).setValue(HorseshoeAnvilBlock.HOLDING, Holding.HORSESHOE).setValue(HorseshoeAnvilBlock.MATERIAL, prevMaterial), 3);
                level.playSound((Player)null, blockPos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);

                // Fire blockstate change event
                level.gameEvent(event.getEntity(), GameEvent.BLOCK_CHANGE, blockPos);

                // Remove 1 durability from the tool used
                itemStackUsed.hurtAndBreak(1, event.getEntity(), event.getEntity().getEquipmentSlotForItem(itemStackUsed));
            }
        }
        // Reset variables if hammer is no longer being swung
        else {
            clickedInitial = false;
            tickCounter = 0;
        }
    }

    // Run this when a block is broken
    @SubscribeEvent
    public static void onBlockBrokenWithHammer(BlockEvent.BreakEvent event) {
        ItemStack itemStackUsed = event.getPlayer().getItemInHand(event.getPlayer().getUsedItemHand());
        if (itemStackUsed.getItem() instanceof HammerItem) {
            // Remove 1 Durability from the Hammer when it is used to break a block
            itemStackUsed.hurtAndBreak(1, event.getPlayer(), event.getPlayer().getEquipmentSlotForItem(itemStackUsed));
        }
    }

    // Run this when horse takes a step. Handle durability loss.
    @SubscribeEvent
    public static void onHorseStepWithHorseshoe(PlayerTickEvent.Post event) {
        LivingEntity entity = event.getEntity();
        // If entity is horse and has passenger
        if (!entity.level().isClientSide() && entity instanceof Horse && ((Horse) entity).hasExactlyOnePlayerPassenger() && ((Horse) entity).getBodyArmorItem().getItem() instanceof HorseshoeItem) {
            Horse horse = (Horse) entity;

            double xMotion = horse.getControllingPassenger().getDeltaMovement().get(Direction.Axis.X);
            double zMotion = horse.getControllingPassenger().getDeltaMovement().get(Direction.Axis.Z);

            ItemStack horseshoeStack = horse.getBodyArmorItem();
            if (horseshoeStack.getItem() instanceof HorseshoeItem) {
                if(xMotion != 0 || zMotion != 0) {
                    stepCounter += 1;
                    if(stepCounter == 3) {
                        stepCounter = 0;
                        horseshoeStack.hurtAndBreak(1, horse, event.getEntity().getEquipmentSlotForItem(horseshoeStack));
                    }
                }
            }
        }
    }

    // Convert floats to integers for horse speed
    private static int floatToInt(float floatNumber){
        return (int) (floatNumber * 10000);
    }


    // Convert integers to floats for horse speed
    private static float intToFloat(int intNumber){
        return (float) (intNumber / 10000.0);
    }
}
