package com.sylvernity.horseupgrades.item.custom;

import com.sylvernity.horseupgrades.block.custom.HorseshoeAnvilBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class HorseshoeBarItem extends Item {

    public HorseshoeBarItem(String pIdentifier, Properties pProperties){
        this(new ResourceLocation("horseupgrades", "textures/item/" + pIdentifier + ".png"), pProperties);
    }

    public HorseshoeBarItem(ResourceLocation pIdentifier, Item.Properties pProperties) {
        super(pProperties);
    }

    // Try to place Horseshoe Bar on anvil when used on one. Calls HorseshoeAnvilBlock#tryPlaceBar method.
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();

        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if (blockstate.getBlock() instanceof HorseshoeAnvilBlock) {
            return HorseshoeAnvilBlock.tryPlaceBar(pContext.getPlayer(), level, blockpos, blockstate, pContext.getItemInHand()) ? InteractionResult.sidedSuccess(level.isClientSide) : InteractionResult.PASS;
        } else {
            return InteractionResult.PASS;
        }
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
    }
}