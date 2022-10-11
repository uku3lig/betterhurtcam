package net.uku3lig.betterhurtcam.mc118;

import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import static net.uku3lig.betterhurtcam.BetterHurtCam.MOD;

public class KeybindingManager implements ModInitializer {
    @Getter
    private static final KeyBinding toggle = new KeyBinding("key.betterhurtcam.toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F8, MOD);
    @Getter
    private static final KeyBinding plus = new KeyBinding("key.betterhurtcam.plus", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F7, MOD);
    @Getter
    private static final KeyBinding minus = new KeyBinding("key.betterhurtcam.minus", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F6, MOD);

    @Override
    public void onInitialize() {
        KeyBindingHelper.registerKeyBinding(toggle);
        KeyBindingHelper.registerKeyBinding(plus);
        KeyBindingHelper.registerKeyBinding(minus);
    }
}
