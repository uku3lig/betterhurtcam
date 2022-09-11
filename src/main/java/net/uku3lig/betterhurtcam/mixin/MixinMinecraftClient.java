package net.uku3lig.betterhurtcam.mixin;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(MinecraftClient.class)
@Slf4j
public class MixinMinecraftClient {
    @Shadow public ClientPlayerEntity player;

    @Inject(at = @At("RETURN"), method = "tick")
    private void onEndTick(CallbackInfo info) {
        BHCConfig config = BetterHurtCam.getConfig();

        while (BetterHurtCam.getPlus().wasPressed()) {
            config.setMultiplier(Math.min(2, config.getMultiplier() + 0.1));
            save(config);

            player.sendMessage(Text.of("§fHurtcam multiplier increased to §3§l%.1f".formatted(config.getMultiplier())), true);
        }

        while (BetterHurtCam.getMinus().wasPressed()) {
            config.setMultiplier(Math.max(0, config.getMultiplier() - 0.1));
            save(config);

            player.sendMessage(Text.of("§fHurtcam multiplier decreased to §3§l%.1f".formatted(config.getMultiplier())), true);
        }
    }

    private void save(BHCConfig cfg) {
        try {
            cfg.writeConfig();
        } catch (IOException e) {
            log.warn("Could not save config", e);
        }
    }
}
