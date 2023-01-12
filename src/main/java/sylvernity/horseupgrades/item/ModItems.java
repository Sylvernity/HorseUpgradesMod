package sylvernity.horseupgrades.item;

import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import sylvernity.horseupgrades.HorseUpgrades;
import sylvernity.horseupgrades.item.custom.HorseshoeItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HorseUpgrades.MODID);

    public static final RegistryObject<Item> WOODEN_HORSESHOE = ITEMS.register("wooden_horseshoe", () -> new HorseshoeItem(0, "wooden_horseshoe" /*new ResourceLocation("horseupgrades", "models/item/wooden_horseshoe.png")*/, (new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC)), ArmorMaterials.LEATHER));
    public static final RegistryObject<Item> IRON_HORSESHOE = ITEMS.register("iron_horseshoe", () -> new HorseshoeItem(0, "iron_horseshoe" /*new ResourceLocation("horseupgrades", "models/item/iron_horseshoe.png")*/, (new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC)), ArmorMaterials.IRON));
    public static final RegistryObject<Item> GOLDEN_HORSESHOE = ITEMS.register("golden_horseshoe", () -> new HorseshoeItem(0, "golden_horseshoe" /*new ResourceLocation("horseupgrades", "models/item/golden_horseshoe.png")*/, (new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC)), ArmorMaterials.GOLD));
    public static final RegistryObject<Item> DIAMOND_HORSESHOE = ITEMS.register("diamond_horseshoe", () -> new HorseshoeItem(0, "diamond_horseshoe" /*new ResourceLocation("horseupgrades", "models/item/diamond_horseshoe.png")*/, (new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC)), ArmorMaterials.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_HORSESHOE = ITEMS.register("netherite_horseshoe", () -> new HorseshoeItem(0, "netherite_horseshoe" /*new ResourceLocation("horseupgrades", "models/item/netherite_horseshoe.png")*/, (new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC)), ArmorMaterials.NETHERITE));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
