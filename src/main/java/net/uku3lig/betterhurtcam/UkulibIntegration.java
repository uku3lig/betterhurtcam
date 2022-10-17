package net.uku3lig.betterhurtcam;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.client.gui.screen.Screen;
import net.uku3lig.ukulib.api.UkulibAPI;
import net.uku3lig.ukulib.config.screen.AbstractConfigScreen;

import java.util.function.Function;

public class UkulibIntegration implements UkulibAPI {
    @Override
    public Function<Screen, AbstractConfigScreen<?>> supplyConfigScreen() {
        return FabricLoader.getInstance().getEntrypointContainers("bhc", UkulibAPI.class)
                .stream()
                .filter(e -> e.getProvider().getMetadata().getId().startsWith("betterhurtcam-mc"))
                .findFirst()
                .map(EntrypointContainer::getEntrypoint)
                .map(UkulibAPI::supplyConfigScreen)
                .orElseThrow(() -> new IllegalArgumentException("Could not find a config screen."));
    }
}
