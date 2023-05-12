package net.uku3lig.betterhurtcam.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.util.TranslatableOption;

@Getter
@AllArgsConstructor
public enum HurtCamType implements TranslatableOption {
    OLD(0, "betterhurtcam.type.old"),
    YAW_BASED(1, "betterhurtcam.type.yawBased"),
    ;

    private final int id;
    private final String translationKey;
}
