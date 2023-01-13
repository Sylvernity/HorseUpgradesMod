package sylvernity.horseupgrades.item.custom;

import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.HorseArmorItem;

public class HorseshoeItem extends HorseArmorItem {

    public ArmorMaterials material;

    public HorseshoeItem(int pProtection, String pIdentifier, Properties pProperties, ArmorMaterials pMaterial){
        super(pProtection, pIdentifier, pProperties);
        material = pMaterial;
    }

}
