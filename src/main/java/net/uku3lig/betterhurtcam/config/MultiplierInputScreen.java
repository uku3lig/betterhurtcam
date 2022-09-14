package net.uku3lig.betterhurtcam.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.uku3lig.ukulib.config.ConfigManager;
import net.uku3lig.ukulib.config.TextInputScreen;

import java.util.Optional;

public class MultiplierInputScreen extends TextInputScreen<Double> {
    public MultiplierInputScreen(Screen parent, ConfigManager<BHCConfig> manager) {
        super(parent, Text.of("BetterHurtCam Config"), Text.translatable("betterhurtcam.option.strength"),
                v -> manager.getConfig().setMultiplier(v), manager.getConfig().getMultiplier(), manager);
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
