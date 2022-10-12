package net.uku3lig.betterhurtcam.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConfigOption {
    ENABLED(69, "Hurtcam", false, true),
    MULTIPLIER(70, "Hurtcam Multiplier", true, false),
    ;

    private final int id;
    private final String text;
    private final boolean isSlider;
    private final boolean isToggle;
}
