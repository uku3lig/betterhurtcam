package net.uku3lig.betterhurtcam.config;

import net.minecraft.client.gui.Screen;

public class ConfigScreen extends AbstractConfigScreen {
    protected ConfigScreen(Screen parent, Config config) {
        super(parent, "BetterHurtCam Config", config);
    }


    @Override
    protected ConfigOption[] getOptions() {
        return new ConfigOption[]{
                new ConfigOption(69, "betterhurtcam.option.enabled", i -> config.setEnabled(!config.isEnabled()),
                        () -> config.isEnabled() ? 1f : 0f, false, ConfigOption.OptionType.BOOLEAN),
                new ConfigOption(70, "betterhurtcam.option.strength", i -> config.setMultiplier(config.getMultiplier() + (i * 0.1f)),
                        config::getMultiplier, true, ConfigOption.OptionType.DOUBLE),
        };
    }
}
