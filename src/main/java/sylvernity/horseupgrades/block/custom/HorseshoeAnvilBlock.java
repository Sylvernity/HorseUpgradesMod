package sylvernity.horseupgrades.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sylvernity.horseupgrades.block.entity.HorseshoeAnvilBlockEntity;
import sylvernity.horseupgrades.item.custom.HorseshoeBarItem;

public class HorseshoeAnvilBlock extends BaseEntityBlock{

    protected static final VoxelShape SHAPE_BODY = Block.box(5, 0, 5, 11, 10, 11);
    protected static final VoxelShape SHAPE_LOWER_BASE = Block.box(1, 0, 2, 15, 1, 14);
    protected static final VoxelShape SHAPE_UPPER_BASE = Block.box(3, 0, 3, 13, 3, 13);
    public static final VoxelShape SHAPE_BASE = Shapes.or(SHAPE_LOWER_BASE, SHAPE_UPPER_BASE);
    public static final VoxelShape SHAPE_BOTTOM = Shapes.or(SHAPE_BASE, SHAPE_BODY);
    protected static final VoxelShape SHAPE_SMALL_HEAD_TOP = Block.box(11, 13, 7, 13, 14, 9);
    protected static final VoxelShape SHAPE_MAIN_HEAD = Block.box(4, 10, 4, 12, 13, 12);
    protected static final VoxelShape SHAPE_HEAD_EAST_FIRST = Block.box(12, 10, 5, 14, 13, 11);
    protected static final VoxelShape SHAPE_HEAD_EAST_SECOND = Block.box(14, 11, 6, 16, 13, 10);
    protected static final VoxelShape SHAPE_HEAD_EAST_THIRD = Block.box(16, 12, 6, 17, 13, 10);
    protected static final VoxelShape SHAPE_HEAD_WEST = Block.box(2, 11, 5, 4, 13, 11);
    protected static final VoxelShape SHAPE_MAIN_HEAD_TOP = Block.box(5, 13, 5, 11, 14, 11);
    public static final VoxelShape SHAPE_HEAD = Shapes.or(SHAPE_SMALL_HEAD_TOP, SHAPE_MAIN_HEAD, SHAPE_MAIN_HEAD_TOP, SHAPE_HEAD_EAST_FIRST, SHAPE_HEAD_EAST_SECOND, SHAPE_HEAD_EAST_THIRD, SHAPE_HEAD_WEST);
    public static final VoxelShape SHAPE = Shapes.or(SHAPE_BOTTOM, SHAPE_HEAD);

    public static final BooleanProperty HAS_BAR = BooleanProperty.create("has_bar");

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public HorseshoeAnvilBlock(BlockBehaviour.Properties pProperties){
        super(pProperties);
    }

    /* BLOCK ENTITY */

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if(pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof HorseshoeAnvilBlockEntity){
                ((HorseshoeAnvilBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    public static boolean tryPlaceBar(@Nullable Player pPlayer, Level pLevel, BlockPos pPos, BlockState pState, ItemStack pBar) {
        if (!pState.getValue(HAS_BAR)) {
            if (!pLevel.isClientSide) {
                placeBar(pPlayer, pLevel, pPos, pState, pBar);
            }
            return true;
        } else {
            return false;
        }
    }

    private static void placeBar(@Nullable Player pPlayer, Level pLevel, BlockPos pPos, BlockState pState, ItemStack pBar) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof HorseshoeAnvilBlockEntity horseshoeAnvilBlockEntity) {
            horseshoeAnvilBlockEntity.setBar(pBar.split(1));
            pLevel.playSound((Player)null, pPos, SoundEvents.BOOK_PUT, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_CHANGE, pPos);
        }

    }
    @Override
    public @NotNull InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()){
            if(pState.getValue(HAS_BAR)){

            }
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof HorseshoeAnvilBlockEntity){
                // Insert code here for interaction with block
                ItemStack itemStack = pPlayer.getItemInHand(pHand);
                if(itemStack.getItem() instanceof HorseshoeBarItem){

                }
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return null;
    }
}