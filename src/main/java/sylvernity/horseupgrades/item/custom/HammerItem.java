package sylvernity.horseupgrades.item.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.extensions.IForgeItem;

public class HammerItem extends Item implements IForgeItem {
    public HammerItem(String pIdentifier, Properties pProperties){
        this(new ResourceLocation("horseupgrades", "textures/item/" + pIdentifier + ".png"), pProperties);
    }

    public HammerItem(ResourceLocation pIdentifier, Item.Properties pProperties) {
        super(pProperties);
    }

}
