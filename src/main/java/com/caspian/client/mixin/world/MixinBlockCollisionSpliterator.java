package com.caspian.client.mixin.world;

import com.caspian.client.Caspian;
import com.caspian.client.impl.event.world.BlockCollisionEvent;
import com.caspian.client.util.Globals;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockCollisionSpliterator;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 *
 *
 * @author linus
 * @since 1.0
 */
@Mixin(BlockCollisionSpliterator.class)
public class MixinBlockCollisionSpliterator implements Globals
{
    /**
     *
     * @param instance
     * @param blockView
     * @param pos
     * @param shapeContext
     * @return
     */
    @Redirect(method = "computeNext()Lnet/minecraft/util/shape/VoxelShape;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/" +
                    "BlockState;getCollisionShape(Lnet/minecraft/world/" +
                    "BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/" +
                    "minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;"))
    private VoxelShape hookGetCollisionShape(BlockState instance,
                                             BlockView blockView, BlockPos pos,
                                             ShapeContext shapeContext)
    {
        VoxelShape voxelShape = instance.getCollisionShape(blockView, pos,
                shapeContext);
        if (blockView != mc.world)
        {
            return voxelShape;
        }
        BlockCollisionEvent blockCollisionEvent =
                new BlockCollisionEvent(pos, instance);
        Caspian.EVENT_HANDLER.dispatch(blockCollisionEvent);
        if (blockCollisionEvent.isCanceled())
        {
            return blockCollisionEvent.getVoxelShape();
        }
        return voxelShape;
    }
}
