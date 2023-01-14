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

    public static AttributeModifier attributeModifier;
    @SubscribeEvent
    public static void addHorseshoeBonusSpeed(LivingEquipmentChangeEvent event){
        if(!event.getEntity().level.isClientSide()) {
            if (event.getEntity() instanceof Horse entity) {
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

    public static int floatToInt(float floatNumber){
        return (int) (floatNumber * 10000);
    }

    public static float intToFloat(int intNumber){
        return (float) (intNumber / 10000.0);
    }
}
