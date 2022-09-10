package net.uku3lig.betterhurtcam.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.uku3lig.betterhurtcam.BetterHurtCam;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        BHCConfig config = BetterHurtCam.getConfig();
        return parent -> new TextInputScreen(parent, config::setMultiplier, config.getMultiplier(), config, BetterHurtCam.getFile());
    }
}
