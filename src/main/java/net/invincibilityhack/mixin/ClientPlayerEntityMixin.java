package net.invincibilityhack.mixin;

import net.invincibilityhack.SharedVariables;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    @Inject(at = @At("HEAD"), method = "sendChatMessage", cancellable = true)
    public void sendChatMessage(String message, CallbackInfo ci) {
        if (message.equals("*invincibility")) {
            SharedVariables.avoidDeathScreen = !SharedVariables.avoidDeathScreen;
            log(SharedVariables.avoidDeathScreen ? "Toggled invincibility on" : "Toggled invincibility off");
            ci.cancel();
        }
    }

    private static void log(String message) {
        mc.player.sendMessage(Text.of(message), false);
    }
}
