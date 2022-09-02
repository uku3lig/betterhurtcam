package net.uku3lig.betterhurtcam.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.option.Option;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class ConfigScreen extends AbstractConfigScreen {
    protected ConfigScreen(Screen parent, Config config) {
        super(parent, new LiteralText("BetterHurtCam Config"), config);
    }

    @Override
    protected Option[] getOptions() {
        int max = config.isIncreased() ? 10 : 2;

        return new Option[] {
                CyclingOption.create("betterhurtcam.option.enabled", opt -> config.isEnabled(), (opt, option, value) -> config.setEnabled(value)),
                new DoubleOption("betterhurtcam.option.strength", 0, max, 0.1f, opt -> config.getMultiplier(), (opt, value) -> config.setMultiplier(value),
                        (opt, option) -> getMultiplierText(new TranslatableText("betterhurtcam.option.strength"), config.getMultiplier())),
                CyclingOption.create("betterhurtcam.option.increased", opt -> config.isIncreased(), (opt, option, value) -> config.setIncreased(value))
        };
    }

    private Text getMultiplierText(Text prefix, double value) {
        return Text.of("%s: %.1fx".formatted(prefix.getString(), value));
    }
}
