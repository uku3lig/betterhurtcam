package net.uku3lig.betterhurtcam;

import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.uku3lig.betterhurtcam.config.Config;
import org.lwjgl.glfw.GLFW;

import java.io.File;

public class BetterHurtCam implements ModInitializer {
    public static final String MOD = "BetterHurtCam";

    @Getter
    private static final KeyBinding toggle = new KeyBinding("key.betterhurtcam.toggle",InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F8, MOD);
    @Getter
    private static final KeyBinding plus = new KeyBinding("key.betterhurtcam.plus", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, MOD);
    @Getter
    private static final KeyBinding minus = new KeyBinding("key.betterhurtcam.minus", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, MOD);

    @Getter
    private static final File file = new File("./config/betterhurtcam.toml");
    @Getter
    private static final Config config = Config.readConfig(file);


    @Override
    public void onInitialize() {
        KeyBindingHelper.registerKeyBinding(toggle);
        KeyBindingHelper.registerKeyBinding(plus);
        KeyBindingHelper.registerKeyBinding(minus);
    }
}
