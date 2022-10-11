package net.uku3lig.betterhurtcam.mc114;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;
import net.uku3lig.betterhurtcam.BetterHurtCam;

import java.util.function.Function;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return parent -> new ConfigScreen(parent, BetterHurtCam.getManager());
    }

    @Override
    public String getModId() {
        return BetterHurtCam.MOD;
    }
}
