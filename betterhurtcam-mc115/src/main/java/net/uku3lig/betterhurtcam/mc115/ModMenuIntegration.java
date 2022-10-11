package net.uku3lig.betterhurtcam.mc115;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.uku3lig.betterhurtcam.BetterHurtCam;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ConfigScreen(parent, BetterHurtCam.getManager());
    }
}
