package net.uku3lig.betterhurtcam.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.uku3lig.ukulib.config.IConfig;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BHCConfig implements IConfig<BHCConfig> {
    private double multiplier;

    @Override
    public BHCConfig defaultConfig() {
        return new BHCConfig(0.3);
    }
}
