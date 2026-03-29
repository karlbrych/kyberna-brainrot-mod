package github.karlbrych.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;

public class DanBlock extends Block {

    public DanBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, BlockHitResult hit) {
        if (world.isClient()) return ActionResult.SUCCESS;

        ServerWorld serverWorld = (ServerWorld) world;

        SkeletonEntity skeleton = new SkeletonEntity(EntityType.SKELETON, serverWorld);
        skeleton.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 0);
        skeleton.initialize(serverWorld, serverWorld.getLocalDifficulty(pos), SpawnReason.SPAWNER, null);
        serverWorld.spawnEntity(skeleton);
        return ActionResult.SUCCESS;
    }
}