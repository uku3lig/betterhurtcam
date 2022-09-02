package net.uku3lig.betterhurtcam.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

public class ConfigScreen extends AbstractConfigScreen {
    protected ConfigScreen(Screen parent, Config config) {
        super(parent, Text.literal("BetterHurtCam Config"), config);
    }

    @Override
    protected SimpleOption<?>[] getOptions() {
        return new SimpleOption[] {
                SimpleOption.ofBoolean("betterhurtcam.option.enabled", config.isEnabled(), config::setEnabled),
                new SimpleOption<>("betterhurtcam.option.strength", SimpleOption.emptyTooltip(),
                        (text, value) -> GameOptions.getGenericValueText(text, Text.of(value.toString())),
                        SimpleOption.DoubleSliderCallbacks.INSTANCE, config.getMultiplier(), config::setMultiplier)
        };
    }
}
