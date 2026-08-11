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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.sylvernity.horseupgrades.blockstate.Holding;
import com.sylvernity.horseupgrades.blockstate.Material;

public class HorseshoeAnvilBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    protected static final VoxelShape SHAPE_BODY = Block.box(5, 0, 5, 11, 10, 11);
    protected static final VoxelShape SHAPE_UPPER_BASE = Block.box(3, 0, 3, 13, 3, 13);
    protected static final VoxelShape SHAPE_MAIN_HEAD = Block.box(4, 10, 4, 12, 13, 12);
    protected static final VoxelShape SHAPE_MAIN_HEAD_TOP = Block.box(5, 13, 5, 11, 14, 11);

    // ===== WEST (original "W_SHAPE") =====
    protected static final VoxelShape W_SHAPE_LOWER_BASE = Block.box(1, 0, 2, 15, 1, 14);
    public static final VoxelShape W_SHAPE_BASE = Shapes.or(W_SHAPE_LOWER_BASE, SHAPE_UPPER_BASE);
    public static final VoxelShape W_SHAPE_BOTTOM = Shapes.or(W_SHAPE_BASE, SHAPE_BODY);
    protected static final VoxelShape W_SHAPE_SMALL_HEAD_TOP = Block.box(11, 13, 7, 13, 14, 9);
    protected static final VoxelShape W_SHAPE_HEAD_EAST_FIRST = Block.box(12, 10, 5, 14, 13, 11);
    protected static final VoxelShape W_SHAPE_HEAD_EAST_SECOND = Block.box(14, 11, 6, 16, 13, 10);
    protected static final VoxelShape W_SHAPE_HEAD_EAST_THIRD = Block.box(16, 12, 7, 17, 13, 9);
    protected static final VoxelShape W_SHAPE_HEAD_WEST = Block.box(2, 11, 5, 4, 13, 11);
    public static final VoxelShape W_SHAPE_HEAD = Shapes.or(W_SHAPE_SMALL_HEAD_TOP, SHAPE_MAIN_HEAD, SHAPE_MAIN_HEAD_TOP, W_SHAPE_HEAD_EAST_FIRST, W_SHAPE_HEAD_EAST_SECOND, W_SHAPE_HEAD_EAST_THIRD, W_SHAPE_HEAD_WEST);
    public static final VoxelShape W_SHAPE = Shapes.or(W_SHAPE_BOTTOM, W_SHAPE_HEAD);

    // ===== NORTH (original "N_SHAPE") =====
    protected static final VoxelShape N_SHAPE_LOWER_BASE = Block.box(2, 0, 1, 14, 1, 15);
    public static final VoxelShape N_SHAPE_BASE = Shapes.or(N_SHAPE_LOWER_BASE, SHAPE_UPPER_BASE);
    public static final VoxelShape N_SHAPE_BOTTOM = Shapes.or(N_SHAPE_BASE, SHAPE_BODY);
    protected static final VoxelShape N_SHAPE_SMALL_HEAD_TOP = Block.box(7, 13, 11, 9, 14, 13);
    protected static final VoxelShape N_SHAPE_HEAD_EAST_FIRST = Block.box(5, 10, 12, 11, 13, 14);
    protected static final VoxelShape N_SHAPE_HEAD_EAST_SECOND = Block.box(6, 11, 14, 10, 13, 16);
    protected static final VoxelShape N_SHAPE_HEAD_EAST_THIRD = Block.box(7, 12, 16, 9, 13, 17);
    protected static final VoxelShape N_SHAPE_HEAD_WEST = Block.box(5, 11, 2, 11, 13, 4);
    public static final VoxelShape N_SHAPE_HEAD = Shapes.or(N_SHAPE_SMALL_HEAD_TOP, SHAPE_MAIN_HEAD, SHAPE_MAIN_HEAD_TOP, N_SHAPE_HEAD_EAST_FIRST, N_SHAPE_HEAD_EAST_SECOND, N_SHAPE_HEAD_EAST_THIRD, N_SHAPE_HEAD_WEST);
    public static final VoxelShape N_SHAPE = Shapes.or(N_SHAPE_BOTTOM, N_SHAPE_HEAD);

    // ===== EAST (W_SHAPE rotated 180°: x,z -> 16-x, 16-z) =====
    protected static final VoxelShape E_SHAPE_LOWER_BASE = W_SHAPE_LOWER_BASE; // symmetric, unchanged
    public static final VoxelShape E_SHAPE_BASE = Shapes.or(E_SHAPE_LOWER_BASE, SHAPE_UPPER_BASE);
    public static final VoxelShape E_SHAPE_BOTTOM = Shapes.or(E_SHAPE_BASE, SHAPE_BODY);
    protected static final VoxelShape E_SHAPE_SMALL_HEAD_TOP = Block.box(3, 13, 7, 5, 14, 9);
    protected static final VoxelShape E_SHAPE_HEAD_WEST_FIRST = Block.box(2, 10, 5, 4, 13, 11);
    protected static final VoxelShape E_SHAPE_HEAD_WEST_SECOND = Block.box(0, 11, 6, 2, 13, 10);
    protected static final VoxelShape E_SHAPE_HEAD_WEST_THIRD = Block.box(-1, 12, 7, 0, 13, 9);
    protected static final VoxelShape E_SHAPE_HEAD_EAST = Block.box(12, 11, 5, 14, 13, 11);
    public static final VoxelShape E_SHAPE_HEAD = Shapes.or(E_SHAPE_SMALL_HEAD_TOP, SHAPE_MAIN_HEAD, SHAPE_MAIN_HEAD_TOP, E_SHAPE_HEAD_WEST_FIRST, E_SHAPE_HEAD_WEST_SECOND, E_SHAPE_HEAD_WEST_THIRD, E_SHAPE_HEAD_EAST);
    public static final VoxelShape E_SHAPE = Shapes.or(E_SHAPE_BOTTOM, E_SHAPE_HEAD);

    // ===== SOUTH (N_SHAPE rotated 180°: x,z -> 16-x, 16-z) =====
    protected static final VoxelShape S_SHAPE_LOWER_BASE = N_SHAPE_LOWER_BASE; // symmetric, unchanged
    public static final VoxelShape S_SHAPE_BASE = Shapes.or(S_SHAPE_LOWER_BASE, SHAPE_UPPER_BASE);
    public static final VoxelShape S_SHAPE_BOTTOM = Shapes.or(S_SHAPE_BASE, SHAPE_BODY);
    protected static final VoxelShape S_SHAPE_SMALL_HEAD_TOP = Block.box(7, 13, 3, 9, 14, 5);
    protected static final VoxelShape S_SHAPE_HEAD_NORTH_FIRST = Block.box(5, 10, 2, 11, 13, 4);
    protected static final VoxelShape S_SHAPE_HEAD_NORTH_SECOND = Block.box(6, 11, 0, 10, 13, 2);
    protected static final VoxelShape S_SHAPE_HEAD_NORTH_THIRD = Block.box(7, 12, -1, 9, 13, 0);
    protected static final VoxelShape S_SHAPE_HEAD_SOUTH = Block.box(5, 11, 12, 11, 13, 14);
    public static final VoxelShape S_SHAPE_HEAD = Shapes.or(S_SHAPE_SMALL_HEAD_TOP, SHAPE_MAIN_HEAD, SHAPE_MAIN_HEAD_TOP, S_SHAPE_HEAD_NORTH_FIRST, S_SHAPE_HEAD_NORTH_SECOND, S_SHAPE_HEAD_NORTH_THIRD, S_SHAPE_HEAD_SOUTH);
    public static final VoxelShape S_SHAPE = Shapes.or(S_SHAPE_BOTTOM, S_SHAPE_HEAD);
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
        return switch (direction) {
            case WEST -> W_SHAPE;
            case NORTH -> N_SHAPE;
            case EAST -> E_SHAPE;
            case DOWN -> null;
            case UP -> null;
            case SOUTH -> S_SHAPE;
        };
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

            // Update inventory of Anvil
            horseshoeAnvilBlockEntity.setContent(new ItemStack(pItem));

            pLevel.playSound((Player)null, pPos, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_CHANGE, pPos);
        }

    }

    // Returns item if player clicks filled anvil
    @Override
    public @NotNull ItemInteractionResult useItemOn(ItemStack itemStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()){
            // Retrieve bar or horseshoe from anvil
            if (pState.getValue(HOLDING) != Holding.NONE) {
                // Update inventory of Anvil
                HorseshoeAnvilBlockEntity anvilBlockEntity = (HorseshoeAnvilBlockEntity) pLevel.getBlockEntity(pPos);
                pPlayer.addItem(anvilBlockEntity.retrieveContent());
            }
        }
        return super.useItemOn(itemStack, pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new HorseshoeAnvilBlockEntity(pPos, pState);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            if (level.getBlockEntity(pos) instanceof HorseshoeAnvilBlockEntity anvilBlockEntity) {
                anvilBlockEntity.dropContents();
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
