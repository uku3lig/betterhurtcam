package net.uku3lig.betterhurtcam.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.uku3lig.ukulib.config.AbstractConfig;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Config extends AbstractConfig {
    private boolean enabled;
    private double multiplier;
    private boolean increased;

    @Override
    protected AbstractConfig defaultConfig() {
        return new Config(true, 0.3, false);
    }
}
