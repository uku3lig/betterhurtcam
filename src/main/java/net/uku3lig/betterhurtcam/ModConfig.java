package net.uku3lig.betterhurtcam;

import lombok.Getter;
import lombok.Setter;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "betterhurtcam")
@Getter
@Setter
public class ModConfig implements ConfigData {
    private boolean enabled = true;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 20)
    @ConfigEntry.Gui.Tooltip
    private int strength = 10;
}
