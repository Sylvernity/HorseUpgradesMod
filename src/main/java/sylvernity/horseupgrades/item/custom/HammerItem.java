package sylvernity.horseupgrades.item.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HammerItem extends Item {
    public HammerItem(String pIdentifier, Properties pProperties){
        this(new ResourceLocation("horseupgrades", "textures/item/" + pIdentifier + ".png"), pProperties);
    }

    public HammerItem(ResourceLocation pIdentifier, Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return super.getUseDuration(pStack);
    }
}
