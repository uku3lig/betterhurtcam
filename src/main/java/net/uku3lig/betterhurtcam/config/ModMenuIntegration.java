package net.uku3lig.betterhurtcam.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import lombok.extern.slf4j.Slf4j;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;

@Slf4j
public class ModMenuIntegration implements ModMenuApi {
    private static final ConfigScreenFactory<?> factory;

    static {
        factory = FabricLoader.getInstance()
                .getEntrypointContainers("modmenu", ModMenuApi.class).stream()
                .filter(ec -> ec.getProvider().getMetadata().getId().contains("betterhurtcam-"))
                .map(EntrypointContainer::getEntrypoint)
                .map(ModMenuApi::getModConfigScreenFactory)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (factory != null) return factory;
        else return NoConfigScreen::new;
    }
}