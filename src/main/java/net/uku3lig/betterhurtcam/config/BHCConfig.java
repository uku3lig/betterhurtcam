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
public class BHCConfig extends AbstractConfig {
    private double multiplier;

    @Override
    protected AbstractConfig defaultConfig() {
        return new BHCConfig(0.3);
    }
}
