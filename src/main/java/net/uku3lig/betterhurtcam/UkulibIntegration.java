package net.uku3lig.betterhurtcam;

import net.minecraft.client.gui.screen.Screen;
import net.uku3lig.betterhurtcam.config.ConfigScreen;
import net.uku3lig.ukulib.api.UkulibAPI;

import java.util.function.UnaryOperator;

public class UkulibIntegration implements UkulibAPI {
    @Override
    public UnaryOperator<Screen> supplyConfigScreen() {
        return ConfigScreen::new;
    }
}
