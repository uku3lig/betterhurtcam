package net.uku3lig.betterhurtcam.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import net.uku3lig.ukulib.config.IConfig;

@Log4j2
@Getter
@Setter
@AllArgsConstructor
public class Config implements IConfig<Config> {
    private boolean enabled;
    private float multiplier;

    @Override
    public Config defaultConfig() {
        return new Config(true, 0.3f);
    }
}
