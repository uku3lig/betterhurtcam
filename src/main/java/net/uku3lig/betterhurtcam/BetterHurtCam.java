package net.uku3lig.betterhurtcam;

import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import net.uku3lig.ukulib.config.ConfigManager;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BetterHurtCam implements ModInitializer {
    public static final String MOD = "BetterHurtCam";
    private static final DecimalFormat decimalFormat = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ROOT));

    @Getter
    private static final File file = new File("./config/betterhurtcam.toml");
    @Getter
    private static final ConfigManager<BHCConfig> manager = ConfigManager.create(BHCConfig.class, "betterhurtcam");


    @Override
    public void onInitialize() {
        decimalFormat.setMaximumFractionDigits(2);
    }

    public static String format(double d) {
        return decimalFormat.format(d);
    }

    public static double round(double d) {
        BigDecimal bigDecimal = BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
