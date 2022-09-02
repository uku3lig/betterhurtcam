package net.uku3lig.betterhurtcam.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Shadow public ClientPlayerEntity player;

    @Inject(at = @At("RETURN"), method = "tick")
    private void onEndTick(CallbackInfo info) {
        ConfigHolder<ModConfig> holder = AutoConfig.getConfigHolder(ModConfig.class);

        while(BetterHurtCam.getToggle().wasPressed()) {
            holder.getConfig().setEnabled(!holder.getConfig().isEnabled());
            holder.save();

            player.sendMessage(Text.of("§fHurtcam " + (holder.getConfig().isEnabled() ? "§a§lON" : "§c§lOFF")), true);
        }

        while (BetterHurtCam.getPlus().wasPressed()) {
            holder.getConfig().setStrength(Math.min(20, holder.getConfig().getStrength() + 1));
            holder.save();

            player.sendMessage(Text.of("§fHurtcam strength increased to §3§l" + holder.getConfig().getStrength()), true);
        }

        while (BetterHurtCam.getMinus().wasPressed()) {
            holder.getConfig().setStrength(Math.max(0, holder.getConfig().getStrength() - 1));
            holder.save();

            player.sendMessage(Text.of("§fHurtcam strength decreased to §3§l" + holder.getConfig().getStrength()), true);
        }
    }
}
