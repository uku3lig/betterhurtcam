package net.uku3lig.betterhurtcam.mc119;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import net.uku3lig.betterhurtcam.config.MultiplierInputScreen;
import net.uku3lig.ukulib.config.ConfigManager;
import net.uku3lig.ukulib.config.screen.AbstractConfigScreen;
import net.uku3lig.ukulib.utils.Ukutils;

public class ConfigScreen extends AbstractConfigScreen<BHCConfig> {
    public ConfigScreen(Screen parent, ConfigManager<BHCConfig> manager) {
        super(parent, Text.of("BetterHurtCam Config"), manager);
    }

    @Override
    protected SimpleOption<?>[] getOptions(BHCConfig config) {
        return new SimpleOption[] {
                SimpleOption.ofBoolean("betterhurtcam.option.enabled", config.isEnabled(), config::setEnabled),
                Ukutils.createButton("betterhurtcam.option.strength", config.getMultiplier(), parent -> new MultiplierInputScreen(parent, manager))
        };
    }
}