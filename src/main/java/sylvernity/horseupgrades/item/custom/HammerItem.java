package sylvernity.horseupgrades.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeItem;
import sylvernity.horseupgrades.HorseUpgrades;

public class HammerItem extends Item implements IForgeItem {
    public HammerItem(String pIdentifier, Properties pProperties){
        this(new ResourceLocation("horseupgrades", "textures/item/" + pIdentifier + ".png"), pProperties);
    }

    public HammerItem(ResourceLocation pIdentifier, Item.Properties pProperties) {
        super(pProperties);
    }

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
//        // pPlayer.startUsingItem(pUsedHand);
//        HorseUpgrades.LOGGER.info("The use method is being called.");
//        return super.use(pLevel, pPlayer, pUsedHand);
//    }
}
