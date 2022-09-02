package net.uku3lig.betterhurtcam.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Shadow public ClientPlayerEntity player;

    @Inject(at = @At("RETURN"), method = "tick")
    private void onEndTick(CallbackInfo info) {
        Config config = BetterHurtCam.getConfig();

        while(BetterHurtCam.getToggle().wasPressed()) {
            config.setEnabled(!config.isEnabled());
            save(config);

            player.sendMessage(Text.of("§fHurtcam " + (config.isEnabled() ? "§a§lON" : "§c§lOFF")), true);
        }

        while (BetterHurtCam.getPlus().wasPressed()) {
            int max = config.isIncreased() ? 10 : 2;
            config.setMultiplier(Math.min(max, config.getMultiplier() + 0.1));
            save(config);

            player.sendMessage(Text.of("§fHurtcam multiplier increased to §3§l%.1f".formatted(config.getMultiplier())), true);
        }

        while (BetterHurtCam.getMinus().wasPressed()) {
            config.setMultiplier(Math.max(0, config.getMultiplier() - 0.1));
            save(config);

            player.sendMessage(Text.of("§fHurtcam multiplier decreased to §3§l%.1f".formatted(config.getMultiplier())), true);
        }
    }

    private void save(Config cfg) {
        try {
            cfg.writeConfig(BetterHurtCam.getFile());
        } catch (IOException ignored) {
            // ignored
        }
    }
}
