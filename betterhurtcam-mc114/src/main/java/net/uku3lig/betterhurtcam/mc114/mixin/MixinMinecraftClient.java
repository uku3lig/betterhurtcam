package net.uku3lig.betterhurtcam.mc114.mixin;

import lombok.extern.log4j.Log4j2;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import net.uku3lig.betterhurtcam.mc114.KeybindingManager;
import net.uku3lig.ukulib.config.ConfigManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(MinecraftClient.class)
@Log4j2
public class MixinMinecraftClient {
    private static final Text ON = new LiteralText("ON").formatted(Formatting.BOLD, Formatting.GREEN);
    private static final Text OFF = new LiteralText("OFF").formatted(Formatting.BOLD, Formatting.RED);

    @Shadow public ClientPlayerEntity player;

    @Inject(at = @At("RETURN"), method = "tick")
    private void onEndTick(CallbackInfo info) {
        ConfigManager<BHCConfig> manager = BetterHurtCam.getManager();

        while(KeybindingManager.getToggle().wasPressed()) {
            manager.getConfig().setEnabled(!manager.getConfig().isEnabled());
            manager.saveConfig();

            player.addChatMessage(new LiteralText("Hurtcam ").append(manager.getConfig().isEnabled() ? ON : OFF), true);
        }


        while (KeybindingManager.getPlus().wasPressed()) {
            manager.getConfig().setMultiplier(manager.getConfig().getMultiplier() + 0.1);
            manager.saveConfig();

            Text multiplier = new LiteralText(BetterHurtCam.format(manager.getConfig().getMultiplier())).formatted(Formatting.BOLD, Formatting.DARK_AQUA);
            player.addChatMessage(new LiteralText("Hurtcam multiplier increased to ").append(multiplier), true);
        }

        while (KeybindingManager.getMinus().wasPressed()) {
            manager.getConfig().setMultiplier(manager.getConfig().getMultiplier() - 0.1);
            manager.saveConfig();

            Text multiplier = new LiteralText(BetterHurtCam.format(manager.getConfig().getMultiplier())).formatted(Formatting.BOLD, Formatting.DARK_AQUA);
            player.addChatMessage(new LiteralText("Hurtcam multiplier decreased to ").append(multiplier), true);
        }
    }
}
