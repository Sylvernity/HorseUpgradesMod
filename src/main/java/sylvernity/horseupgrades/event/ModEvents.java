package sylvernity.horseupgrades.event;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sylvernity.horseupgrades.HorseUpgrades;
import sylvernity.horseupgrades.item.custom.HorseshoeItem;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = HorseUpgrades.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void addHorseshoeBonusSpeed(LivingEquipmentChangeEvent event){
        if(!event.getEntity().level.isClientSide()) {
            if (event.getEntity() instanceof Horse entity) {
                if (event.getSlot() == EquipmentSlot.CHEST) {
                    int speed = floatToInt((float) entity.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
                    int bonus;

                    HorseUpgrades.LOGGER.info("Base Horse Speed Value is: {}", speed);

                    // When new item in slot is horseshoe, add new speed bonus
                    if (event.getTo().getItem() instanceof HorseshoeItem newHorseshoe){
                        ArmorMaterials material = newHorseshoe.material;
                        if (material == ArmorMaterials.LEATHER) {
                            bonus = 300;
                        } else if (material == ArmorMaterials.IRON) {
                            bonus = 700;
                        } else if (material == ArmorMaterials.GOLD) {
                            bonus = 700;
                        } else if (material == ArmorMaterials.DIAMOND) {
                            bonus = 1000;
                        } else if (material == ArmorMaterials.NETHERITE) {
                            bonus = 1200;
                        }
                        else {
                            bonus = 0;
                        }
                        if (speed + bonus > 3375) {
                            speed = 3375;
                            bonus = 0;
                        }
                        Objects.requireNonNull(entity.getAttribute(Attributes.MOVEMENT_SPEED)).addTransientModifier(new AttributeModifier("Horseshoe Speed Bonus", intToFloat(bonus), AttributeModifier.Operation.ADDITION));
                        HorseUpgrades.LOGGER.info("Bonus Horse Speed Value is: {}", speed + bonus);
                    }
                }

            }
        }
    }

    public static int floatToInt(float floatNumber){
        return (int) (floatNumber * 10000);
    }

    public static float intToFloat(int intNumber){
        return (float) (intNumber / 10000.0);
    }
}
