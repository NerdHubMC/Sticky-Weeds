package nerdhub.stickyweeds.mixins;

import nerdhub.stickyweeds.StickyWeeds;
import nerdhub.stickyweeds.config.SWConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity {

    @Inject(method = "checkBlockCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;onBlockCollision(Lnet/minecraft/block/BlockState;)V", shift = At.Shift.AFTER))
    private void onUpdateMovement(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        if(!entity.isLogicalSideForUpdatingMovement()) {
            BlockState state = entity.world.getBlockState(entity.getBlockPos());
            if(!state.isAir()) {
                Block block = state.getBlock();
                if(StickyWeeds.GRASS_LIKE.contains(block) || StickyWeeds.CROP_LIKE.contains(block)) {
                    Vec3d velocity = entity.getVelocity();
                    SWConfig config = StickyWeeds.getConfig();
                    entity.setVelocity(velocity.x * config.velocityModifier, velocity.y, velocity.z * config.velocityModifier);
                    entity.velocityModified = true;
                }
            }
        }
    }
}
