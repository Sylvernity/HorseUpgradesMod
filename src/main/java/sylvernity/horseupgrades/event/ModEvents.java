package sylvernity.horseupgrades.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sylvernity.horseupgrades.HorseUpgrades;
import sylvernity.horseupgrades.block.custom.HorseshoeAnvilBlock;
import sylvernity.horseupgrades.block.entity.HorseshoeAnvilBlockEntity;
import sylvernity.horseupgrades.blockstate.Holding;
import sylvernity.horseupgrades.blockstate.Material;
import sylvernity.horseupgrades.item.custom.HammerItem;
import sylvernity.horseupgrades.item.custom.HorseshoeItem;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = HorseUpgrades.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    public static AttributeModifier attributeModifier;
    public static boolean clickedInitial = false;
    public static int tickCounter = 0;
    public static BlockPos blockPos;

    // Run this when equipment has been changed for an entity. Adds speed modifier when horseshoe added
    @SubscribeEvent
    public static void addHorseshoeBonusSpeed(LivingEquipmentChangeEvent event){
        if(!event.getEntity().level.isClientSide()) {
            // If the entity is a horse
            if (event.getEntity() instanceof Horse entity) {
                // If the equipment in the horse's chest/armor slot was changed
                if (event.getSlot() == EquipmentSlot.CHEST) {
                    int speed = floatToInt((float) entity.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
                    int bonus = 0;

                    HorseUpgrades.LOGGER.info("Base Horse Speed Value is: {}", speed);
                    // When old item in slot is horseshoe, remove old speed bonus
                    if (event.getFrom().getItem() instanceof HorseshoeItem){
                        Objects.requireNonNull(entity.getAttribute(Attributes.MOVEMENT_SPEED)).removeModifier(attributeModifier);
                    }
                    // When new item in slot is horseshoe, add new speed bonus
                    if (event.getTo().getItem() instanceof HorseshoeItem newHorseshoe){
                        ArmorMaterials material = newHorseshoe.material;
                        if (material == ArmorMaterials.IRON || material == ArmorMaterials.GOLD) {
                            bonus = 700;
                        } else if (material == ArmorMaterials.DIAMOND) {
                            bonus = 1000;
                        } else if (material == ArmorMaterials.NETHERITE) {
                            bonus = 1200;
                        }
                        if (speed + bonus > 3375) {
                            bonus = 3375 - speed;
                        }
                        attributeModifier = new AttributeModifier("Horseshoe Speed Bonus", intToFloat(bonus), AttributeModifier.Operation.ADDITION);
                        Objects.requireNonNull(entity.getAttribute(Attributes.MOVEMENT_SPEED)).addTransientModifier(attributeModifier);
                        HorseUpgrades.LOGGER.info("Bonus Horse Speed Value is: {}", speed + bonus);
                    }

                }

            }
        }
    }

    // Run this when player left clicks block. Changes static global variables when hammer clicks anvil
    @SubscribeEvent
    public static void startHammering(PlayerInteractEvent.LeftClickBlock event) {
        if (!event.getEntity().level.isClientSide()) {
            // If player is holding hammer
            if (event.getItemStack().getItem() instanceof HammerItem) {
                // If block used on is a horseshoe anvil
                if (event.getLevel().getBlockEntity(event.getPos()) instanceof HorseshoeAnvilBlockEntity) {
                    // If anvil has a horseshoe bar
                    if (event.getLevel().getBlockState(event.getPos()).getValue(HorseshoeAnvilBlock.HOLDING) == Holding.BAR) {
                        // Hammer has hit anvil
                        clickedInitial = true;

                        // Get position of anvil hit
                        blockPos = event.getPos();
                    }
                }
            }
        }
    }

    // Run this every tick when startHammer changes global variables
    @SubscribeEvent
    public static void onTickHammer(TickEvent.PlayerTickEvent event) {
        // If the player is swinging a Hammer
        if (event.player.swinging && event.player.getItemInHand(event.player.getUsedItemHand()).getItem() instanceof HammerItem){
            Level level = event.player.level;
            // If the initial click was on a horseshoe anvil and it hasn't been 250 ticks of constant swinging
            if (clickedInitial && tickCounter < 250){
                tickCounter ++;
                HorseUpgrades.LOGGER.info("Also the ticks are now {}", tickCounter);
            }
            // If the hammer has been swinging at the anvil for 250 ticks, change the blockstate of the anvil to a horseshoe
            else if (tickCounter == 250 && event.player.getItemInHand(event.player.getUsedItemHand()).getItem() instanceof HammerItem) {
                clickedInitial = false;
                tickCounter = 0;
                HorseshoeAnvilBlockEntity anvilBlock = (HorseshoeAnvilBlockEntity) event.player.getLevel().getBlockEntity(blockPos);
                Material prevMaterial = anvilBlock.getBlockState().getValue(HorseshoeAnvilBlock.MATERIAL);
                level.setBlock(blockPos, level.getBlockState(blockPos).setValue(HorseshoeAnvilBlock.HOLDING, Holding.HORSESHOE).setValue(HorseshoeAnvilBlock.MATERIAL, prevMaterial), 3);
                level.gameEvent(event.player, GameEvent.BLOCK_CHANGE, blockPos);
            }
        }
        // Reset variables if hammer is no longer being swung
        else {
            clickedInitial = false;
            tickCounter = 0;
        }
    }

    // Convert floats to integers for horse speed
    public static int floatToInt(float floatNumber){
        return (int) (floatNumber * 10000);
    }

    // Convert integers to floats for horse speed
    public static float intToFloat(int intNumber){
        return (float) (intNumber / 10000.0);
    }
}
