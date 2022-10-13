package net.uku3lig.betterhurtcam.config;

import net.minecraft.client.gui.Screen;
import net.uku3lig.ukulib.config.ConfigManager;
import net.uku3lig.ukulib.config.ConfigOption;
import net.uku3lig.ukulib.config.screen.AbstractConfigScreen;

public class ConfigScreen extends AbstractConfigScreen<Config> {
    public ConfigScreen(Screen parent, ConfigManager<Config> manager) {
        super(parent, "BetterHurtCam Config", manager);
    }

    @Override
    protected ConfigOption<?>[] getOptions(Config config) {
        return new ConfigOption[] {
                new ConfigOption<>(69, "Enabled", false, true, config::isEnabled, ConfigOption.boolToString, f -> config.setEnabled(!config.isEnabled())),
                new ConfigOption<>(70, "Multiplier", true, false, config::getMultiplier, ConfigOption.floatToString, config::setMultiplier)
        };
    }
}
