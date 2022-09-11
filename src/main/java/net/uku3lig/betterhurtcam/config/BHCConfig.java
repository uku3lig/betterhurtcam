package net.uku3lig.betterhurtcam.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.uku3lig.ukulib.config.AbstractConfig;

import java.io.File;

@Slf4j
@Getter
@Setter
public class BHCConfig extends AbstractConfig {
    private double multiplier;

    public BHCConfig(File file, double multiplier) {
        super(file);
        this.multiplier = multiplier;
    }

    public BHCConfig(File file) {
        super(file);
    }

    @Override
    protected AbstractConfig defaultConfig() {
        return new BHCConfig(file, 0.3);
    }
}
