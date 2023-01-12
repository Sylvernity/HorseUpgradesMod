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
            HorseUpgrades.LOGGER.info("Equipment was changed!");
            if (entity instanceof Horse) {
                if (event.getSlot() == EquipmentSlot.CHEST) {
                    float originalSpeed = entity.getSpeed();
                    float newSpeed = originalSpeed;

                    // When previous and new item in armor slot are horseshoes, subtract the previous speed bonus and add the new speed bonus
                    try {
                        HorseshoeItem oldHorseshoe = (HorseshoeItem) event.getFrom().getItem();
                        HorseshoeItem newHorseshoe = (HorseshoeItem) event.getTo().getItem();
                        HorseUpgrades.LOGGER.info("The horseshoe is {}", oldHorseshoe);
                        HorseUpgrades.LOGGER.info("The horseshoe is {}", newHorseshoe);
                        HorseUpgrades.LOGGER.info("Current Horse Speed Value is: {}", originalSpeed);
                        newSpeed = originalSpeed;

                        ArmorMaterials material = newHorseshoe.material;

                        // Subtract previous speed bonus
                        subtractNewSpeed(material, newSpeed, entity);

                        // Add new speed bonus
                        addNewSpeed(material, newSpeed, entity);
                        HorseUpgrades.LOGGER.info("New Horse Speed Value is: {}", newSpeed);
                    }
                    catch (Exception e) {
                        if (event.getTo().isEmpty() && event.getFrom().getItem() instanceof HorseshoeItem armor){
                            ArmorMaterials material = newHorseshoe.material;
                            subtractNewSpeed(material, newSpeed, entity);
                        }
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

    public static void subtractNewSpeed(ArmorMaterials material, float newSpeed, LivingEntity entity){
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
