package net.uku3lig.betterhurtcam;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class BetterHurtCam implements ModInitializer {
    private static final String MOD = "BetterHurtCam";

    @Override
    public void onInitialize() {
        ConfigHolder<ModConfig> holder = AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);

        KeyBinding toggle = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.betterhurtcam.toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F8, MOD));
        KeyBinding plus = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.betterhurtcam.plus", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, MOD));
        KeyBinding minus = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.betterhurtcam.minus", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, MOD));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(toggle.wasPressed()) {
                if (holder.getConfig().getStrength() == 0) {
                    holder.getConfig().setStrength(holder.getConfig().getOldValue());
                } else {
                    holder.getConfig().setOldValue(holder.getConfig().getStrength());
                    holder.getConfig().setStrength(0);
                }
                holder.save();

                client.player.sendMessage(Text.of("§fHurtcam " + (holder.getConfig().getStrength() == 0 ? "§c§lOFF" : "§a§lON")), true);
            }

            while (plus.wasPressed()) {
                holder.getConfig().setStrength(Math.min(20, holder.getConfig().getStrength() + 1));
                holder.save();

                client.player.sendMessage(Text.of("§fHurtcam strength increased to §3§l" + holder.getConfig().getStrength()), true);
            }

            while (minus.wasPressed()) {
                holder.getConfig().setStrength(Math.max(0, holder.getConfig().getStrength() - 1));
                holder.save();

                client.player.sendMessage(Text.of("§fHurtcam strength decreased to §3§l" + holder.getConfig().getStrength()), true);
            }
        });
    }
}
