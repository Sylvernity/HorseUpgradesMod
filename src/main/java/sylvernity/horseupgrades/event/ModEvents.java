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
                    try {
                        HorseshoeItem armor = (HorseshoeItem) event.getTo().getItem();
                        HorseUpgrades.LOGGER.info("The horseshoe is {}", armor);
                        HorseUpgrades.LOGGER.info("Current Horse Speed Value is: {}", originalSpeed);
                        newSpeed = originalSpeed;
                        if (armor.material == ArmorMaterials.LEATHER) {
                            newSpeed += 0.02;
                        } else if (armor.material == ArmorMaterials.IRON) {
                            newSpeed += 0.08;
                        } else if (armor.material == ArmorMaterials.GOLD) {
                            newSpeed += 0.08;
                        } else if (armor.material == ArmorMaterials.DIAMOND) {
                            newSpeed += 0.12;
                        } else if (armor.material == ArmorMaterials.NETHERITE) {
                            newSpeed += 0.18;
                        } else {
                            newSpeed = 0.3375f;
                        }
                        if (newSpeed > 0.3375f){
                            newSpeed = 0.3375f;
                        }
                        HorseUpgrades.LOGGER.info("New Horse Speed Value is: {}", newSpeed);
                    }
                    catch (Exception e) {
                        if (event.getTo().isEmpty() && event.getFrom().getItem() instanceof HorseshoeItem armor){
                            newSpeed = originalSpeed;
                            if (armor.material == ArmorMaterials.LEATHER) {
                                newSpeed -= 0.02;
                            } else if (armor.material == ArmorMaterials.IRON) {
                                newSpeed -= 0.08;
                            } else if (armor.material == ArmorMaterials.GOLD) {
                                newSpeed -= 0.08;
                            } else if (armor.material == ArmorMaterials.DIAMOND) {
                                newSpeed -= 0.12;
                            } else if (armor.material == ArmorMaterials.NETHERITE) {
                                newSpeed -= 0.18;
                            }
                        }
                    }
                    entity.setSpeed(newSpeed);
                }
            }
        }
    }
}
