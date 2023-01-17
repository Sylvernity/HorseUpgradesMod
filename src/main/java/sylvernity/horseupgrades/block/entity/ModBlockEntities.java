package sylvernity.horseupgrades.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import sylvernity.horseupgrades.HorseUpgrades;
import sylvernity.horseupgrades.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HorseUpgrades.MODID);

    public static final RegistryObject<BlockEntityType<HorseshoeAnvilBlockEntity>> HORSESHOE_ANVIL =
            BLOCK_ENTITIES.register("horseshoe_anvil", () ->
                    BlockEntityType.Builder.of(HorseshoeAnvilBlockEntity::new,
                            ModBlocks.HORSESHOE_ANVIL.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
