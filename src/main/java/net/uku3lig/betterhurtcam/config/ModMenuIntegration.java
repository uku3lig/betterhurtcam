package net.uku3lig.betterhurtcam.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.uku3lig.betterhurtcam.BetterHurtCam;

import java.io.IOException;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new TextInputScreen(parent, value -> {
            BetterHurtCam.getConfig().setMultiplier(value);
            try {
                BetterHurtCam.getConfig().writeConfig(BetterHurtCam.getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, BetterHurtCam.getConfig().getMultiplier());
    }
}
