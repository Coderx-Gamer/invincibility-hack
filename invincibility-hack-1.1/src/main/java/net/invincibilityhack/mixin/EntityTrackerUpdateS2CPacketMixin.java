package net.invincibilityhack.mixin;

import net.invincibilityhack.SharedVariables;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EntityTrackerUpdateS2CPacket.class)
public abstract class EntityTrackerUpdateS2CPacketMixin {
    @Shadow @Nullable public abstract List<DataTracker.Entry<?>> getTrackedValues();
    @Shadow public abstract int id();

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    @Inject(at = @At("HEAD"), method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", cancellable = true)
    public void apply(ClientPlayPacketListener clientPlayPacketListener, CallbackInfo ci) {
        if (SharedVariables.avoidDeathScreen) {
            if (mc.player != null) {
                if (id() == mc.player.getId()) {
                    ci.cancel();
                }
            }
        }
    }
}
