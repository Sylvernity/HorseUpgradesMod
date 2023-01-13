package sylvernity.horseupgrades.event;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sylvernity.horseupgrades.HorseUpgrades;
import sylvernity.horseupgrades.item.custom.HorseshoeItem;

@Mod.EventBusSubscriber(modid = HorseUpgrades.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void addHorseshoeBonusSpeed(LivingEquipmentChangeEvent event){
        if(!event.getEntity().level.isClientSide()) {
            if (event.getEntity() instanceof Horse entity) {
                if (event.getSlot() == EquipmentSlot.CHEST) {
                    HorseUpgrades.LOGGER.info("Horse armor was changed on horse {}.", entity);

                    int speed = floatToInt((float) entity.getAttributeValue(Attributes.MOVEMENT_SPEED));


                    HorseUpgrades.LOGGER.info("Old Horse Speed Value is: {}", speed);

                    // When previous item in slot was horseshoe, subtract previous speed bonus
                    if (event.getFrom().getItem() instanceof HorseshoeItem oldHorseshoe){
                        ArmorMaterials material = oldHorseshoe.material;
                        if (material == ArmorMaterials.LEATHER) {
                            speed -= 300;
                        } else if (material == ArmorMaterials.IRON) {
                            speed -= 700;
                        } else if (material == ArmorMaterials.GOLD) {
                            speed -= 700;
                        } else if (material == ArmorMaterials.DIAMOND) {
                            speed -= 1000;
                        } else if (material == ArmorMaterials.NETHERITE) {
                            speed -= 1200;
                        }
                        HorseUpgrades.LOGGER.info("New Horse Speed Value is: {}", speed);
                    }

                    // When new item in slot is horseshoe, add new speed bonus
                    if (event.getTo().getItem() instanceof HorseshoeItem newHorseshoe){
                        ArmorMaterials material = newHorseshoe.material;
                        if (material == ArmorMaterials.LEATHER) {
                            speed += 300;
                        } else if (material == ArmorMaterials.IRON) {
                            speed += 700;
                        } else if (material == ArmorMaterials.GOLD) {
                            speed += 700;
                        } else if (material == ArmorMaterials.DIAMOND) {
                            speed += 1000;
                        } else if (material == ArmorMaterials.NETHERITE) {
                            speed += 1200;
                        }
                        HorseUpgrades.LOGGER.info("New Horse Speed Value is: {}", speed);
                    }
                    entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(intToFloat(speed));
                }
            }
        }
    }

    /* public static double addNewSpeed(ArmorMaterials material, double newSpeed, LivingEntity entity){
        if (material == ArmorMaterials.LEATHER) {
            newSpeed += 0.02;
        } else if (material == ArmorMaterials.IRON) {
            newSpeed += 0.08;
        } else if (material == ArmorMaterials.GOLD) {
            newSpeed += 0.08;
        } else if (material == ArmorMaterials.DIAMOND) {
            newSpeed += 0.12;
        } else if (material == ArmorMaterials.NETHERITE) {
            newSpeed += 0.18;
        } else {
            newSpeed = 0.3375;
        }
        if (newSpeed > 0.3375){
            newSpeed = 0.3375;
        }
        entity.setSpeed((float) newSpeed);
        return newSpeed;
    }

    public static double subtractOldSpeed(ArmorMaterials material, double newSpeed, LivingEntity entity){
        if (material == ArmorMaterials.LEATHER) {
            newSpeed -= 0.02;
        } else if (material == ArmorMaterials.IRON) {
            newSpeed -= 0.08;
        } else if (material == ArmorMaterials.GOLD) {
            newSpeed -= 0.08;
        } else if (material == ArmorMaterials.DIAMOND) {
            newSpeed -= 0.12;
        } else if (material == ArmorMaterials.NETHERITE) {
            newSpeed -= 0.18;
        }
        entity.setSpeed((float) newSpeed);
        return newSpeed;
    }
    */

    public static int floatToInt(float floatNumber){
        return (int) (floatNumber * 10000);
    }

    public static float intToFloat(int intNumber){
        return (float) (intNumber / 10000.0);
    }
}
