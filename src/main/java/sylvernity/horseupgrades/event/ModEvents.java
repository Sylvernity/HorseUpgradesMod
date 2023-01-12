package sylvernity.horseupgrades.event;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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
            LivingEntity entity = event.getEntity();
            if (entity instanceof Horse) {
                if (event.getSlot() == EquipmentSlot.CHEST) {
                    HorseUpgrades.LOGGER.info("Horse armor was changed.");

                    float speed = entity.getSpeed();

                    HorseUpgrades.LOGGER.info("Current Horse Speed Value is: {}", speed);

                    // When previous item in slot was horseshoe, subtract previous speed bonus
                    if (event.getFrom().getItem() instanceof HorseshoeItem newHorseshoe){
                        ArmorMaterials material = newHorseshoe.material;
                        subtractOldSpeed(material, speed, entity);
                        HorseUpgrades.LOGGER.info("The horseshoe is now {}", newHorseshoe);
                        HorseUpgrades.LOGGER.info("New Horse Speed Value is: {}", speed);
                    }

                    // When new item in slot is horseshoe, add new speed bonus
                    else if (event.getTo().getItem() instanceof HorseshoeItem newHorseshoe){
                        ArmorMaterials material = newHorseshoe.material;
                        addNewSpeed(material, speed, entity);
                        HorseUpgrades.LOGGER.info("The horseshoe is now {}", newHorseshoe);
                        HorseUpgrades.LOGGER.info("New Horse Speed Value is: {}", speed);
                    }
                }
            }
        }
    }

    public static void addNewSpeed(ArmorMaterials material, float newSpeed, LivingEntity entity){
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
            newSpeed = 0.3375f;
        }
        if (newSpeed > 0.3375f){
            newSpeed = 0.3375f;
        }
        entity.setSpeed(newSpeed);
    }

    public static void subtractOldSpeed(ArmorMaterials material, float newSpeed, LivingEntity entity){
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
        entity.setSpeed(newSpeed);
    }
}
