package net.uku3lig.betterhurtcam.mixin;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import net.uku3lig.ukulib.config.ConfigManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
@Slf4j
public class MixinMinecraftClient {
    @Shadow public ClientPlayerEntity player;

    @Inject(at = @At("RETURN"), method = "tick")
    private void onEndTick(CallbackInfo info) {
        ConfigManager<BHCConfig> manager = BetterHurtCam.getManager();

        while(BetterHurtCam.getToggle().wasPressed()) {
            manager.getConfig().setEnabled(!manager.getConfig().isEnabled());
            manager.saveConfig();

            player.sendMessage(Text.of("§fHurtcam " + (manager.getConfig().isEnabled() ? "§a§lON" : "§c§lOFF")), true);
        }


        while (BetterHurtCam.getPlus().wasPressed()) {
            manager.getConfig().setMultiplier(Math.min(2, manager.getConfig().getMultiplier() + 0.1));
            manager.saveConfig();

            player.sendMessage(Text.of("§fHurtcam multiplier increased to §3§l%.1f".formatted(manager.getConfig().getMultiplier())), true);
        }

        while (BetterHurtCam.getMinus().wasPressed()) {
            manager.getConfig().setMultiplier(Math.max(0, manager.getConfig().getMultiplier() - 0.1));
            manager.saveConfig();

            player.sendMessage(Text.of("§fHurtcam multiplier decreased to §3§l%.1f".formatted(manager.getConfig().getMultiplier())), true);
        }
    }
}
