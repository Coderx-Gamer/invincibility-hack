package net.invincibilityhack.mixin;

import net.invincibilityhack.SharedVariables;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(HealthUpdateS2CPacket.class)
public abstract class HealthUpdateS2CPacketMixin {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    @Inject(at = @At("HEAD"), method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", cancellable = true)
    public void apply(ClientPlayPacketListener clientPlayPacketListener, CallbackInfo ci) {
        if (SharedVariables.avoidDeathScreen) {
            if (mc.player != null) {
                ci.cancel();
            }
        }
    }
}
