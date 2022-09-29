package net.uku3lig.betterhurtcam.mc118;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.uku3lig.betterhurtcam.BetterHurtCam;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ConfigScreen(parent, BetterHurtCam.getManager());
    }
}
