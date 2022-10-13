package net.uku3lig.betterhurtcam;

import lombok.Getter;
import net.uku3lig.betterhurtcam.config.Config;
import net.uku3lig.ukulib.config.ConfigManager;

public class BetterHurtCam {
    @Getter
    private static final ConfigManager<Config> manager = ConfigManager.create(Config.class, "betterhurtcam");

    private BetterHurtCam() {}
}
