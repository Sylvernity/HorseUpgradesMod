package sylvernity.horseupgrades.item;

import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import sylvernity.horseupgrades.HorseUpgrades;
import sylvernity.horseupgrades.item.custom.HammerItem;
import sylvernity.horseupgrades.item.custom.HorseshoeBarItem;
import sylvernity.horseupgrades.item.custom.HorseshoeItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HorseUpgrades.MODID);

    public static final RegistryObject<Item> IRON_HORSESHOE_BAR = ITEMS.register("iron_horseshoe_bar", () -> new HorseshoeBarItem("iron_horseshoe_bar", new Item.Properties().stacksTo(16).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB)));
    public static final RegistryObject<Item> GOLDEN_HORSESHOE_BAR = ITEMS.register("golden_horseshoe_bar", () -> new HorseshoeBarItem("golden_horseshoe_bar", new Item.Properties().stacksTo(16).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB)));
    public static final RegistryObject<Item> DIAMOND_HORSESHOE_BAR = ITEMS.register("diamond_horseshoe_bar", () -> new HorseshoeBarItem("diamond_horseshoe_bar", new Item.Properties().stacksTo(16).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB)));

    public static final RegistryObject<Item> IRON_HORSESHOE = ITEMS.register("iron_horseshoe", () -> new HorseshoeItem(0, "iron_horseshoe", (new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB)), ArmorMaterials.IRON));
    public static final RegistryObject<Item> GOLDEN_HORSESHOE = ITEMS.register("golden_horseshoe", () -> new HorseshoeItem(0, "golden_horseshoe", (new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB)), ArmorMaterials.GOLD));
    public static final RegistryObject<Item> DIAMOND_HORSESHOE = ITEMS.register("diamond_horseshoe", () -> new HorseshoeItem(0, "diamond_horseshoe", (new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB)), ArmorMaterials.DIAMOND));
    public static final RegistryObject<Item> NETHERITE_HORSESHOE = ITEMS.register("netherite_horseshoe", () -> new HorseshoeItem(0, "netherite_horseshoe" , (new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB)), ArmorMaterials.NETHERITE));

    public static final RegistryObject<Item> IRON_HAMMER = ITEMS.register("iron_hammer", () -> new HammerItem("iron_hammer" , (new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.HORSE_UPGRADES_TAB))));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
