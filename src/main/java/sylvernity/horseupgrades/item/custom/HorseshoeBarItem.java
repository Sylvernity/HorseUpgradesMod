package sylvernity.horseupgrades.item.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class HorseshoeBarItem extends Item {

    private final ResourceLocation texture;

    public HorseshoeBarItem(String pIdentifier, Properties pProperties){
        this(new ResourceLocation("horseupgrades", "textures/item/" + pIdentifier + ".png"), pProperties);
    }

    public HorseshoeBarItem(ResourceLocation pIdentifier, Item.Properties pProperties) {
        super(pProperties);
        this.texture = pIdentifier;
    }
}
