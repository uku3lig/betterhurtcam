package net.uku3lig.betterhurtcam.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BHCConfig implements Serializable {
    private boolean enabled = true;
    private double multiplier = 0.3;
    private boolean heartBlink = true;
    private HurtCamType type = HurtCamType.YAW_BASED;
}
