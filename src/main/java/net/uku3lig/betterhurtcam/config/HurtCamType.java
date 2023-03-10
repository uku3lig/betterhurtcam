package net.uku3lig.betterhurtcam.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.util.TranslatableOption;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum HurtCamType implements TranslatableOption {
    OLD(0, "betterhurtcam.type.old"),
    YAW_BASED(1, "betterhurtcam.type.yawBased"),
    ;

    private final int id;
    private final String translationKey;

    public static HurtCamType findById(int id) {
        return Arrays.stream(values())
                .filter(h -> h.id == id)
                .findFirst()
                .orElse(null);
    }
}
