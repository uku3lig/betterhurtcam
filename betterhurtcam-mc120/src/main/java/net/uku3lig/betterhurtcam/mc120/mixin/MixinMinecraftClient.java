package net.uku3lig.betterhurtcam.mc120.mixin;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import net.uku3lig.betterhurtcam.mc120.KeybindingManager;
import net.uku3lig.ukulib.config.ConfigManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
@Slf4j
public class MixinMinecraftClient {
    private static final Text ON = Text.literal("ON").formatted(Formatting.BOLD, Formatting.GREEN);
    private static final Text OFF = Text.literal("OFF").formatted(Formatting.BOLD, Formatting.RED);

    @Shadow public ClientPlayerEntity player;

    @Inject(at = @At("RETURN"), method = "tick")
    private void onEndTick(CallbackInfo info) {
        ConfigManager<BHCConfig> manager = BetterHurtCam.getManager();

        while(KeybindingManager.getToggle().wasPressed()) {
            manager.getConfig().setEnabled(!manager.getConfig().isEnabled());
            manager.saveConfig();

            player.sendMessage(Text.literal("Hurtcam ").append(manager.getConfig().isEnabled() ? ON : OFF), true);
        }


        while (KeybindingManager.getPlus().wasPressed()) {
            manager.getConfig().setMultiplier(manager.getConfig().getMultiplier() + 0.1);
            manager.saveConfig();

            Text multiplier = Text.literal(BetterHurtCam.format(manager.getConfig().getMultiplier())).formatted(Formatting.BOLD, Formatting.DARK_AQUA);
            player.sendMessage(Text.literal("Hurtcam multiplier increased to ").append(multiplier), true);
        }

        while (KeybindingManager.getMinus().wasPressed()) {
            manager.getConfig().setMultiplier(manager.getConfig().getMultiplier() - 0.1);
            manager.saveConfig();

            Text multiplier = Text.literal(BetterHurtCam.format(manager.getConfig().getMultiplier())).formatted(Formatting.BOLD, Formatting.DARK_AQUA);
            player.sendMessage(Text.literal("Hurtcam multiplier decreased to ").append(multiplier), true);
        }
    }
}
