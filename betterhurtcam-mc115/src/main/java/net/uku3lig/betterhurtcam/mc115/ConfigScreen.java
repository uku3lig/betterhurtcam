package net.uku3lig.betterhurtcam.mc115;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.options.CyclingOption;
import net.minecraft.client.options.Option;
import net.minecraft.text.LiteralText;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import net.uku3lig.ukulib.config.ConfigManager;
import net.uku3lig.ukulib.config.screen.AbstractConfigScreen;
import net.uku3lig.ukulib.utils.Ukutils;

public class ConfigScreen extends AbstractConfigScreen<BHCConfig> {
    public ConfigScreen(Screen parent, ConfigManager<BHCConfig> manager) {
        super(parent, new LiteralText("BetterHurtCam Config"), manager);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    protected Option[] getOptions(BHCConfig config) {
        return new Option[] {
                new CyclingOption("betterhurtcam.option.enabled", (opt, amount) -> config.setEnabled((amount % 2 == 0) == config.isEnabled()),
                        (opt, option) -> option.getDisplayPrefix() + config.isEnabled()),
                Ukutils.createButton("betterhurtcam.option.strength", config.getMultiplier(), screen -> minecraft.openScreen(new MultiplierInputScreen(screen, manager)))
        };
    }
}
