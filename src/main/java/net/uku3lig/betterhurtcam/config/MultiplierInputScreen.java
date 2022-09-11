package net.uku3lig.betterhurtcam.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.uku3lig.ukulib.config.TextInputScreen;

import java.util.Optional;

public class MultiplierInputScreen extends TextInputScreen<Double> {
    protected MultiplierInputScreen(Screen parent, BHCConfig config) {
        super(parent, Text.of("BetterHurtCam Config"), Text.translatable("betterhurtcam.option.strength"), config::setMultiplier, config.getMultiplier(), config);
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
