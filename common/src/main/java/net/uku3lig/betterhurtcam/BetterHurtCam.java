package net.uku3lig.betterhurtcam;

import com.mojang.blaze3d.platform.InputConstants;
import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import net.uku3lig.ukulib.config.ConfigManager;
import net.uku3lig.ukulib.utils.Ukutils;

public class BetterHurtCam {
    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("betterhurtcam", "key"));

    private static final KeyMapping toggle = new KeyMapping("key.betterhurtcam.toggle", InputConstants.Type.KEYBOARD, InputConstants.KEY_F8, CATEGORY);
    private static final KeyMapping plus = new KeyMapping("key.betterhurtcam.plus", InputConstants.Type.KEYBOARD, InputConstants.KEY_F7, CATEGORY);
    private static final KeyMapping minus = new KeyMapping("key.betterhurtcam.minus", InputConstants.Type.KEYBOARD, InputConstants.KEY_F6, CATEGORY);

    @Getter
    private static final ConfigManager<BHCConfig> manager = ConfigManager.createDefault(BHCConfig.class, "betterhurtcam");


    public static void onInitialize() {
        Ukutils.registerToggleBind(toggle, () -> manager.getConfig().isEnabled(), b -> manager.getConfig().setEnabled(b), Component.literal("Hurtcam"));

        Ukutils.registerKeybinding(plus, client -> {
            manager.getConfig().modifyMultiplier(0.1);
            manager.saveConfig();

            if (client.player != null) {
                client.player.sendOverlayMessage(getMultiplierText());
            }
        });

        Ukutils.registerKeybinding(minus, client -> {
            manager.getConfig().modifyMultiplier(-0.1);
            manager.saveConfig();

            if (client.player != null) {
                client.player.sendOverlayMessage(getMultiplierText());
            }
        });
    }

    private static Component getMultiplierText() {
        String multValue = "%.2f".formatted(manager.getConfig().getMultiplier());
        Component multText = Component.literal(multValue).withStyle(ChatFormatting.BOLD, ChatFormatting.DARK_AQUA);
        return Component.literal("Hurtcam multiplier modified to ").append(multText);
    }
}
