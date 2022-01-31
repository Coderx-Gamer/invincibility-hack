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
        String message2 = message.toLowerCase();
        if (message2.equals("*invis") || message2.equals("*i") || message2.equals("*invisible") || message2.equals("*inv") || message2.equals("*in") || message2.equals("*god") || message2.equals("*invi")) {
            SharedVariables.avoidDeathScreen = !SharedVariables.avoidDeathScreen;
            log(SharedVariables.avoidDeathScreen ? "Toggled invincibility on" : "Toggled invincibility off");
            ci.cancel();
        }
        // ay listen man I didn't figure out how to use arrays I had to do this that way sorry (in lua its way more simple :( )
    }

    private static void log(String message) {
        mc.player.sendMessage(Text.of(message), false);
    }
}
