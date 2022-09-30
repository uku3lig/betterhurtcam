package net.uku3lig.betterhurtcam.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.uku3lig.ukulib.config.IConfig;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BHCConfig implements IConfig<BHCConfig> {
    private boolean enabled;
    private double multiplier;

    @Override
    public BHCConfig defaultConfig() {
        return new BHCConfig(true, 0.3);
    }
}
