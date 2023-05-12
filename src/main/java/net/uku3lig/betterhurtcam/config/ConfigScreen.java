package net.uku3lig.betterhurtcam.config;

import net.minecraft.client.gui.screen.Screen;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.ukulib.config.option.CyclingOption;
import net.uku3lig.ukulib.config.option.TypedInputOption;
import net.uku3lig.ukulib.config.option.WidgetCreator;
import net.uku3lig.ukulib.config.screen.AbstractConfigScreen;

import java.util.Optional;

public class ConfigScreen extends AbstractConfigScreen<BHCConfig> {
    public ConfigScreen(Screen parent) {
        super("BetterHurtCam Config", parent, BetterHurtCam.getManager());
    }

    @Override
    protected WidgetCreator[] getWidgets(BHCConfig config) {
        return new WidgetCreator[] {
                CyclingOption.ofBoolean("betterhurtcam.option.enabled", config.isEnabled(), config::setEnabled),
                new TypedInputOption<>("betterhurtcam.option.strength", "%.2f".formatted(config.getMultiplier()), config::setMultiplier, this::convert),
                CyclingOption.ofBoolean("betterhurtcam.option.heartBlink", config.isHeartBlink(), config::setHeartBlink),
                CyclingOption.ofTranslatableEnum("betterhurtcam.option.type", HurtCamType.class, config.getType(), config::setType)
        };
    }

    private Optional<Double> convert(String value) {
        try {
            return Optional.of(Double.parseDouble(value));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
