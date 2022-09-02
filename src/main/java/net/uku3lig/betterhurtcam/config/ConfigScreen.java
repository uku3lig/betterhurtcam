package net.uku3lig.betterhurtcam.config;

import com.mojang.serialization.Codec;
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
                        new SimpleOption.ValidatingIntSliderCallbacks(0, 20).withModifier(value -> value / 10., value -> (int) (value * 10)),
                        Codec.doubleRange(0, 2), config.getMultiplier(), config::setMultiplier)
        };
    }
}
