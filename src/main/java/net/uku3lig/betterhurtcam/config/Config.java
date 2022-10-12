package net.uku3lig.betterhurtcam.config;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;

@Log4j2
@Getter
@Setter
@AllArgsConstructor
public class Config {
    private boolean enabled;
    private float multiplier;

    public Config() {
        this(true, 0.3f);
    }

    public static Config readConfig(File file) {
        if (!file.exists()) {
            try {
                new Config().writeConfig(file);
            } catch (IOException e) {
                log.warn("Could not write default configuration file", e);
            }
            return new Config();
        } else {
            return new Toml().read(file).to(Config.class);
        }
    }

    public void writeConfig(File file) throws IOException {
        new TomlWriter().write(this, file);
    }
}
