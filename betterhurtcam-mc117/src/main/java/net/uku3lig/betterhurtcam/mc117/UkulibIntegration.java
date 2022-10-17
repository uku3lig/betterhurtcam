package net.uku3lig.betterhurtcam.mc117;

import net.minecraft.client.gui.screen.Screen;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.ukulib.api.UkulibAPI;
import net.uku3lig.ukulib.config.screen.AbstractConfigScreen;

import java.util.function.Function;

public class UkulibIntegration implements UkulibAPI {
    @Override
    public Function<Screen, AbstractConfigScreen<?>> supplyConfigScreen() {
        return parent -> new ConfigScreen(parent, BetterHurtCam.getManager());
    }
}
