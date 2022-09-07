package net.uku3lig.betterhurtcam.config;

import com.mojang.serialization.Codec;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.ukulib.config.AbstractConfigScreen;

public class BHCConfigScreen extends AbstractConfigScreen<BHCConfig> {
    protected BHCConfigScreen(Screen parent, BHCConfig config) {
        super(parent, Text.literal("BetterHurtCam Config"), config, BetterHurtCam.getFile());
    }

    @Override
    protected SimpleOption<?>[] getOptions() {
        int max = config.isIncreased() ? 10 : 2;

        return new SimpleOption[] {
                SimpleOption.ofBoolean("betterhurtcam.option.enabled", config.isEnabled(), config::setEnabled),
                new SimpleOption<>("betterhurtcam.option.strength", SimpleOption.emptyTooltip(), this::getMultiplierText,
                        new SimpleOption.ValidatingIntSliderCallbacks(0, max * 10).withModifier(value -> value / 10., value -> (int) (value * 10)),
                        Codec.doubleRange(0, max), config.getMultiplier(), config::setMultiplier),
                SimpleOption.ofBoolean("betterhurtcam.option.increased", config.isIncreased(), config::setIncreased)
        };
    }

    private Text getMultiplierText(Text prefix, double value) {
        return Text.of("%s: %.1fx".formatted(prefix.getString(), value));
    }
}
