package net.uku3lig.betterhurtcam;

import lombok.Getter;
import net.minecraft.client.resource.language.TranslationStorage;
import net.uku3lig.betterhurtcam.config.Config;
import net.uku3lig.betterhurtcam.config.ConfigOption;

import java.io.File;

public class BetterHurtCam {
    @Getter
    private static final File file = new File("./config/betterhurtcam.toml");
    @Getter
    private static final Config config = Config.readConfig(file);

    public static void saveConfig() {
        config.writeConfig(file);
    }

    public static void saveSliderValue(ConfigOption option, float value) {
        if (option == ConfigOption.MULTIPLIER) {
            config.setMultiplier(value);
            config.writeConfig(file);
        }
    }

    public static String getTranslatedValue(ConfigOption option) {
        TranslationStorage storage = TranslationStorage.getInstance();
        String text = option.getText() + ": ";
        if (option.isSlider()) {
            float value = getFloatValue(option);
            return value == 0.0F ? text + storage.translate("options.off") : text + (int)(value * 100.0F) + "%";
        } else if (option.isToggle()) {
            boolean value = getBooleanValue(option);
            return value ? text + storage.translate("options.on") : text + storage.translate("options.off");
        } else {
            return text;
        }
    }

    public static float getFloatValue(ConfigOption option) {
        if (option == ConfigOption.MULTIPLIER) {
            return config.getMultiplier();
        } else {
            return 0.0F;
        }
    }

    public static boolean getBooleanValue(ConfigOption option) {
        if (option == ConfigOption.ENABLED) {
            return config.isEnabled();
        } else {
            return false;
        }
    }

    private BetterHurtCam() {}
}
