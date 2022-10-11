package net.uku3lig.betterhurtcam.mc117;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import net.uku3lig.ukulib.config.ConfigManager;
import net.uku3lig.ukulib.config.screen.TextInputScreen;

import java.util.Optional;

public class MultiplierInputScreen extends TextInputScreen<Double> {
    public MultiplierInputScreen(Screen parent, ConfigManager<BHCConfig> manager) {
        super(parent, Text.of("BetterHurtCam Config"), new TranslatableText("betterhurtcam.option.strength"),
                v -> manager.getConfig().setMultiplier(BetterHurtCam.round(v)), manager.getConfig().getMultiplier(), manager);
    }

    @Override
    public Optional<Double> convert(String s) {
        try {
            return Optional.of(Double.parseDouble(s));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
