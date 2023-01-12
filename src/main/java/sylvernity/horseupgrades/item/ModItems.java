package sylvernity.horseupgrades.item;

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

    public static final RegistryObject<Item> WOOD_HORSESHOE = ITEMS.register("wood_horseshoe", () -> new HorseshoeItem(0, "wood_horseshoe" /*new ResourceLocation("horseupgrades", "models/item/wood_horseshoe.png")*/, (new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC))));
    public static final RegistryObject<Item> IRON_HORSESHOE = ITEMS.register("iron_horseshoe", () -> new HorseshoeItem(0, "iron_horseshoe" /*new ResourceLocation("horseupgrades", "models/item/wood_horseshoe.png")*/, (new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC))));
    public static final RegistryObject<Item> GOLD_HORSESHOE = ITEMS.register("gold_horseshoe", () -> new HorseshoeItem(0, "gold_horseshoe" /*new ResourceLocation("horseupgrades", "models/item/wood_horseshoe.png")*/, (new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC))));
    public static final RegistryObject<Item> DIAMOND_HORSESHOE = ITEMS.register("diamond_horseshoe", () -> new HorseshoeItem(0, "diamond_horseshoe" /*new ResourceLocation("horseupgrades", "models/item/wood_horseshoe.png")*/, (new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC))));
    public static final RegistryObject<Item> NETHERITE_HORSESHOE = ITEMS.register("netherite_horseshoe", () -> new HorseshoeItem(0, "netherite_horseshoe" /*new ResourceLocation("horseupgrades", "models/item/wood_horseshoe.png")*/, (new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC))));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
