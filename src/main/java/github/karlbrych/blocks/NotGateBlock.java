package github.karlbrych.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class NotGateBlock extends Block {

    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;

    public NotGateBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(POWERED, false)
                .with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED, FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(POWERED, false);
    }

    private boolean NotGateFunction(World world, BlockPos pos, BlockState state) {
        Direction facing = state.get(FACING);
        Direction input = facing.getOpposite();
        int power = world.getEmittedRedstonePower(pos.offset(input), input.getOpposite());
        return power == 0;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos,
                               Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        if (world.isClient()) return;

        boolean shouldBePowered = NotGateFunction(world, pos, state);
        if (state.get(POWERED) != shouldBePowered) {
            world.setBlockState(pos, state.with(POWERED, shouldBePowered));
            world.updateNeighborsAlways(pos.offset(state.get(FACING)), this, null);
        }
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world,
                                    BlockPos pos, Direction direction) {
        if (state.get(POWERED) && direction == state.get(FACING).getOpposite()) {
            return 15;
        }
        return 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }
}