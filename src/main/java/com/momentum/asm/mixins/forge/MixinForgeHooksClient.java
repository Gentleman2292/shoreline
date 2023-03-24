package com.momentum.asm.mixins.forge;

import com.momentum.Momentum;
import com.momentum.api.util.Wrapper;
import com.momentum.impl.events.forge.InputUpdateEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.ForgeHooksClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgeHooksClient.class)
public class MixinForgeHooksClient implements Wrapper {

    /**
     * Called when the player input is updated
     */
    @Inject(method = "onInputUpdate", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onOnInputUpdate(EntityPlayer player, MovementInput movementInput, CallbackInfo ci) {

        // player input
        if (player == mc.player) {

            // post input update event
            InputUpdateEvent inputUpdateEvent = new InputUpdateEvent(movementInput);
            Momentum.EVENT_BUS.dispatch(inputUpdateEvent);
        }
    }
}