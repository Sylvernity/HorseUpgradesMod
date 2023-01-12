package sylvernity.horseupgrades.item.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sylvernity.horseupgrades.HorseUpgrades;

@Mod.EventBusSubscriber(modid = HorseUpgrades.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HorseshoeItem extends HorseArmorItem {

    public ArmorMaterials material = null;

    public HorseshoeItem(int pProtection, String pIdentifier, Properties pProperties, ArmorMaterials pMaterial){
        super(pProtection, pIdentifier, pProperties);
        material = pMaterial;
    }

}
