package github.karlbrych.items;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class NetanyahuItem extends Item {

    public NetanyahuItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;

            Vec3d eyePos = user.getEyePos();
            Vec3d lookVec = user.getRotationVec(1.0f);
            Vec3d targetPos = eyePos.add(lookVec.multiply(50));

            HitResult raycast = world.raycast(new RaycastContext(
                    eyePos,
                    targetPos,
                    RaycastContext.ShapeType.OUTLINE,
                    RaycastContext.FluidHandling.NONE,
                    user
            ));

            Vec3d strikePos = (raycast.getType() != HitResult.Type.MISS)
                    ? raycast.getPos()
                    : targetPos;

            BlockPos strikeBlock = BlockPos.ofFloored(strikePos);

            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, serverWorld);
            lightning.setPosition(strikeBlock.getX() + 0.5, strikeBlock.getY(), strikeBlock.getZ() + 0.5);
            serverWorld.spawnEntity(lightning);

            user.getItemCooldownManager().set(
            	    net.minecraft.util.Identifier.of("test-mod", "netanyahu"), 10
            	);
        }

        return ActionResult.SUCCESS;
    }
}