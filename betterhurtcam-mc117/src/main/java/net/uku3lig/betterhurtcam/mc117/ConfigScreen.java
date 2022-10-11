package net.uku3lig.betterhurtcam.mc117;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.Option;
import net.minecraft.text.Text;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import net.uku3lig.ukulib.config.ConfigManager;
import net.uku3lig.ukulib.config.screen.AbstractConfigScreen;
import net.uku3lig.ukulib.utils.Ukutils;

public class ConfigScreen extends AbstractConfigScreen<BHCConfig> {
    public ConfigScreen(Screen parent, ConfigManager<BHCConfig> manager) {
        super(parent, Text.of("BetterHurtCam Config"), manager);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    protected Option[] getOptions(BHCConfig config) {
        return new Option[] {
                CyclingOption.create("betterhurtcam.option.enabled", opt -> config.isEnabled(), (opt, option, value) -> config.setEnabled(value)),
                Ukutils.createButton("betterhurtcam.option.strength", BetterHurtCam.format(config.getMultiplier()),
                        screen -> client.setScreen(new MultiplierInputScreen(screen, manager)))
        };
    }
}
