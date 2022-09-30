package net.uku3lig.betterhurtcam.mc116;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.Option;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
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
                new CyclingOption("betterhurtcam.option.enabled", (opt, amount) -> config.setEnabled((amount % 2 == 0) == config.isEnabled()),
                        (opt, option) -> new TranslatableText("options.generic_value", "betterhurtcam.option.enabled", config.isEnabled())),
                Ukutils.createButton("betterhurtcam.option.strength", config.getMultiplier(), screen -> client.openScreen(new MultiplierInputScreen(screen, manager)))
        };
    }
}
