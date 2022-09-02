package net.uku3lig.betterhurtcam;

import lombok.Getter;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class BetterHurtCam implements ModInitializer {
    public static final String MOD = "BetterHurtCam";

    @Getter
    private static final KeyBinding toggle = new KeyBinding("key.betterhurtcam.toggle",InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F8, MOD);
    @Getter
    private static final KeyBinding plus = new KeyBinding("key.betterhurtcam.plus", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, MOD);
    @Getter
    private static final KeyBinding minus = new KeyBinding("key.betterhurtcam.minus", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, MOD);


    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);

        KeyBindingHelper.registerKeyBinding(toggle);
        KeyBindingHelper.registerKeyBinding(plus);
        KeyBindingHelper.registerKeyBinding(minus);
    }
}
