package net.uku3lig.betterhurtcam;

import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import net.uku3lig.betterhurtcam.config.Config;

import java.io.File;

public class BetterHurtCam implements ModInitializer {
    @Getter
    private static final File file = new File("./config/betterhurtcam.toml");
    @Getter
    private static final Config config = Config.readConfig(file);


    @Override
    public void onInitialize() {
        // hi
    }
}
