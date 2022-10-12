package net.uku3lig.betterhurtcam.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Getter
@AllArgsConstructor
public class ConfigOption {
    private final int id;
    private final String translationKey;
    private final Consumer<Integer> setter;
    private final Supplier<Float> getter;
    private final boolean isSlider;
    private final OptionType type;

    public enum OptionType {
        BOOLEAN,
        INTEGER,
        DOUBLE
    }
}
