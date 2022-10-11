package net.uku3lig.betterhurtcam;

import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import net.uku3lig.ukulib.config.ConfigManager;

import java.io.File;

public class BetterHurtCam implements ModInitializer {
    public static final String MOD = "BetterHurtCam";

    @Getter
    private static final File file = new File("./config/betterhurtcam.toml");
    @Getter
    private static final ConfigManager<BHCConfig> manager = ConfigManager.create(BHCConfig.class, "betterhurtcam");


    @Override
    public void onInitialize() {
        // hi :3
    }
}
