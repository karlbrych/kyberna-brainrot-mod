package github.karlbrych.items;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Random;
public class TrumpItem extends Item {

    public TrumpItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;
            Random random = new Random();
    
            net.minecraft.util.math.Vec3d eyePos = user.getEyePos();
            net.minecraft.util.math.Vec3d lookVec = user.getRotationVec(1.0f);
            net.minecraft.util.math.Vec3d targetPos = eyePos.add(lookVec.multiply(100));

            net.minecraft.util.hit.HitResult raycast = world.raycast(new net.minecraft.world.RaycastContext(
                    eyePos,
                    targetPos,
                    net.minecraft.world.RaycastContext.ShapeType.OUTLINE,
                    net.minecraft.world.RaycastContext.FluidHandling.NONE,
                    user
            ));

            net.minecraft.util.math.Vec3d strikePos = (raycast.getType() != net.minecraft.util.hit.HitResult.Type.MISS)
                    ? raycast.getPos()
                    : targetPos;

            // Spawn 64 TNT above the hit position
            for (int i = 0; i < 64; i++) {
                double offsetX = (random.nextDouble() - 0.5) * 20;
                double offsetZ = (random.nextDouble() - 0.5) * 20;
                double spawnY = strikePos.y + 20 + random.nextDouble() * 10;

                TntEntity tnt = new TntEntity(
                        serverWorld,
                        strikePos.x + offsetX,
                        spawnY,
                        strikePos.z + offsetZ,
                        null
                );

                tnt.setFuse(40);

                tnt.setVelocity(
                        (random.nextDouble() - 0.5) * 0.3,
                        -1.5,
                        (random.nextDouble() - 0.5) * 0.3
                );

                serverWorld.spawnEntity(tnt);
            }

            user.getItemCooldownManager().set(
                    net.minecraft.util.Identifier.of(github.karlbrych.Testmod.MOD_ID, "trump"), 20
            );
        }

        return ActionResult.SUCCESS;
    }
}