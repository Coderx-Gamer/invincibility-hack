package net.invincibilityhack.mixin;

import net.invincibilityhack.SharedVariables;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.DeathMessageS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathMessageS2CPacket.class)
public class DeathMessageS2CPacketMixin {
    @Inject(at = @At("HEAD"), method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", cancellable = true)
    public void apply(ClientPlayPacketListener clientPlayPacketListener, CallbackInfo ci) {
        if (SharedVariables.avoidDeathScreen) {
            ci.cancel();
        }
    }
}
