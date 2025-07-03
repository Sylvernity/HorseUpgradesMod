/*
        Horse Upgrades Minecraft Mod
        Copyright (C) 2023 Sylvernity
*/

package com.sylvernity.horseupgrades.block.custom;

import com.sylvernity.horseupgrades.block.ModBlocks;
import com.sylvernity.horseupgrades.block.entity.HorseshoeAnvilBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.sylvernity.horseupgrades.blockstate.Holding;
import com.sylvernity.horseupgrades.blockstate.Material;
import com.sylvernity.horseupgrades.item.ModItems;

import static com.sylvernity.horseupgrades.block.ModBlocks.BLOCKS;

public class HorseshoeAnvilBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    protected static final VoxelShape SHAPE_BODY = Block.box(5, 0, 5, 11, 10, 11);
    protected static final VoxelShape SHAPE_UPPER_BASE = Block.box(3, 0, 3, 13, 3, 13);
    protected static final VoxelShape SHAPE_MAIN_HEAD = Block.box(4, 10, 4, 12, 13, 12);
    protected static final VoxelShape SHAPE_MAIN_HEAD_TOP = Block.box(5, 13, 5, 11, 14, 11);
    protected static final VoxelShape X_SHAPE_LOWER_BASE = Block.box(1, 0, 2, 15, 1, 14);
    public static final VoxelShape X_SHAPE_BASE = Shapes.or(X_SHAPE_LOWER_BASE, SHAPE_UPPER_BASE);
    public static final VoxelShape X_SHAPE_BOTTOM = Shapes.or(X_SHAPE_BASE, SHAPE_BODY);
    protected static final VoxelShape X_SHAPE_SMALL_HEAD_TOP = Block.box(11, 13, 7, 13, 14, 9);
    protected static final VoxelShape X_SHAPE_HEAD_EAST_FIRST = Block.box(12, 10, 5, 14, 13, 11);
    protected static final VoxelShape X_SHAPE_HEAD_EAST_SECOND = Block.box(14, 11, 6, 16, 13, 10);
    protected static final VoxelShape X_SHAPE_HEAD_EAST_THIRD = Block.box(16, 12, 7, 17, 13, 9);
    protected static final VoxelShape X_SHAPE_HEAD_WEST = Block.box(2, 11, 5, 4, 13, 11);
    public static final VoxelShape X_SHAPE_HEAD = Shapes.or(X_SHAPE_SMALL_HEAD_TOP, SHAPE_MAIN_HEAD, SHAPE_MAIN_HEAD_TOP, X_SHAPE_HEAD_EAST_FIRST, X_SHAPE_HEAD_EAST_SECOND, X_SHAPE_HEAD_EAST_THIRD, X_SHAPE_HEAD_WEST);
    public static final VoxelShape X_SHAPE = Shapes.or(X_SHAPE_BOTTOM, X_SHAPE_HEAD);


    protected static final VoxelShape Z_SHAPE_LOWER_BASE = Block.box(2, 0, 1, 14, 1, 15);
    public static final VoxelShape Z_SHAPE_BASE = Shapes.or(Z_SHAPE_LOWER_BASE, SHAPE_UPPER_BASE);
    public static final VoxelShape Z_SHAPE_BOTTOM = Shapes.or(Z_SHAPE_BASE, SHAPE_BODY);
    protected static final VoxelShape Z_SHAPE_SMALL_HEAD_TOP = Block.box(7, 13, 11, 9, 14, 13);
    protected static final VoxelShape Z_SHAPE_HEAD_EAST_FIRST = Block.box(5, 10, 12, 11, 13, 14);
    protected static final VoxelShape Z_SHAPE_HEAD_EAST_SECOND = Block.box(6, 11, 14, 10, 13, 16);
    protected static final VoxelShape Z_SHAPE_HEAD_EAST_THIRD = Block.box(7, 12, 16, 9, 13, 17);
    protected static final VoxelShape Z_SHAPE_HEAD_WEST = Block.box(5, 11, 2, 11, 13, 4);
    public static final VoxelShape Z_SHAPE_HEAD = Shapes.or(Z_SHAPE_SMALL_HEAD_TOP, SHAPE_MAIN_HEAD, SHAPE_MAIN_HEAD_TOP, Z_SHAPE_HEAD_EAST_FIRST, Z_SHAPE_HEAD_EAST_SECOND, Z_SHAPE_HEAD_EAST_THIRD, Z_SHAPE_HEAD_WEST);
    public static final VoxelShape Z_SHAPE = Shapes.or(Z_SHAPE_BOTTOM, Z_SHAPE_HEAD);
    public static final EnumProperty<Holding> HOLDING = EnumProperty.create("holding", Holding.class);
    public static final EnumProperty<Material> MATERIAL = EnumProperty.create("material", Material.class);

    public HorseshoeAnvilBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        if(!ModBlocks.HORSESHOE_ANVIL.getKey().equals(BuiltInRegistries.BLOCK.getKey(this))) {
            this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HOLDING, Holding.NONE).setValue(MATERIAL, Material.NONE));
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getClockWise());
    }
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        return direction.getAxis() == Direction.Axis.X ? X_SHAPE : Z_SHAPE;
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(HOLDING);
        builder.add(MATERIAL);
    }


    /* BLOCK ENTITY */

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    // Called by useOn method in HorseshoeBarItem. Calls placeBar method if anvil has no item
    public static boolean tryPlaceBar(@Nullable Player pPlayer, Level pLevel, BlockPos pPos, BlockState pState, ItemStack pBar) {
        if (!pLevel.isClientSide) {
            if (pState.getValue(HOLDING) == Holding.NONE) {
                placeBar(pPlayer, pLevel, pPos, pState, pBar);
            }
            return true;
        } else {
            return false;
        }
    }

    // Place Horseshoe Bar in anvil when called by tryPlaceBar()
    private static void placeBar(@Nullable Player pPlayer, Level pLevel, BlockPos pPos, BlockState pState, ItemStack pBar) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);

        if (blockEntity instanceof HorseshoeAnvilBlockEntity horseshoeAnvilBlockEntity) {
            Item pItem = pBar.getItem();

            pBar.split(1);

            if (pItem.equals(ModItems.IRON_HORSESHOE_BAR.get())) {
                pLevel.setBlock(pPos, pState.setValue(MATERIAL, Material.IRON).setValue(HOLDING, Holding.BAR), 3);
            }
            else if(pItem.equals(ModItems.GOLDEN_HORSESHOE_BAR.get())) {
                pLevel.setBlock(pPos, pState.setValue(MATERIAL, Material.GOLD).setValue(HOLDING, Holding.BAR), 3);
            }
            else if (pItem.equals(ModItems.DIAMOND_HORSESHOE_BAR.get())) {
                pLevel.setBlock(pPos, pState.setValue(MATERIAL, Material.DIAMOND).setValue(HOLDING, Holding.BAR), 3);
            }

            pLevel.playSound((Player)null, pPos, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_CHANGE, pPos);
        }

    }

    // Returns item if player clicks filled anvil
    @Override
    public @NotNull ItemInteractionResult useItemOn(ItemStack itemStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);

            // Retrieve bar from anvil
            if (pState.getValue(HOLDING) == Holding.BAR) {
                if (pState.getValue(MATERIAL) == Material.IRON) {
                    pPlayer.addItem(new ItemStack(ModItems.IRON_HORSESHOE_BAR.get()));
                } else if (pState.getValue(MATERIAL) == Material.GOLD) {
                    pPlayer.addItem(new ItemStack(ModItems.GOLDEN_HORSESHOE_BAR.get()));
                } else if (pState.getValue(MATERIAL) == Material.DIAMOND) {
                    pPlayer.addItem(new ItemStack(ModItems.DIAMOND_HORSESHOE_BAR.get()));
                }
                pLevel.setBlock(pPos, pState.setValue(MATERIAL, Material.NONE).setValue(HOLDING, Holding.NONE), 3);
            }
            
            // Retrieve horseshoe from anvil
            else if (pState.getValue(HOLDING) == Holding.HORSESHOE) {
                    if (pState.getValue(MATERIAL) == Material.IRON) {
                        pPlayer.addItem(new ItemStack(ModItems.IRON_HORSESHOE.get()));
                    } else if (pState.getValue(MATERIAL) == Material.GOLD) {
                        pPlayer.addItem(new ItemStack(ModItems.GOLDEN_HORSESHOE.get()));
                    } else if (pState.getValue(MATERIAL) == Material.DIAMOND) {
                        pPlayer.addItem(new ItemStack(ModItems.DIAMOND_HORSESHOE.get()));
                    }
                pLevel.setBlock(pPos, pState.setValue(MATERIAL, Material.NONE).setValue(HOLDING, Holding.NONE), 3);
            }
        }
        return super.useItemOn(itemStack, pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new HorseshoeAnvilBlockEntity(pPos, pState);
    }
}
