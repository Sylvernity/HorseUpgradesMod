package sylvernity.horseupgrades.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import sylvernity.horseupgrades.block.ModBlocks;
import sylvernity.horseupgrades.block.custom.HorseshoeAnvilBlock;

public class HorseshoeBarItem extends Item {

    private final ResourceLocation texture;

    public HorseshoeBarItem(String pIdentifier, Properties pProperties){
        this(new ResourceLocation("horseupgrades", "textures/item/" + pIdentifier + ".png"), pProperties);
    }

    public HorseshoeBarItem(ResourceLocation pIdentifier, Item.Properties pProperties) {
        super(pProperties);
        this.texture = pIdentifier;
    }

    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if (blockstate.getBlock().equals(ModBlocks.HORSESHOE_ANVIL)) {
            return HorseshoeAnvilBlock.tryPlaceBar(pContext.getPlayer(), level, blockpos, blockstate, pContext.getItemInHand()) ? InteractionResult.sidedSuccess(level.isClientSide) : InteractionResult.PASS;
        } else {
            return InteractionResult.PASS;
        }
    }
}