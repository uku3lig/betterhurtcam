package net.uku3lig.betterhurtcam.mixin;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.screen.VideoSettingsScreen;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.gui.widgets.OptionButton;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.config.ConfigOption;
import net.uku3lig.betterhurtcam.config.ConfigSlider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VideoSettingsScreen.class)
public class MixinVideoSettingsScreen extends Screen {
    @SuppressWarnings("unchecked")
    @Inject(method = "init()V", at = @At("RETURN"))
    public void addButtons(CallbackInfo ci) {
        int counter = 8;
        for(ConfigOption option : ConfigOption.values()) {
            if (!option.isSlider()) {
                this.buttons.add(new OptionButton(option.getId(), this.width / 2 - 155 + counter % 2 * 160, this.height / 6 + 24 * (counter >> 1), null, BetterHurtCam.getTranslatedValue(option)));
            } else {
                this.buttons.add(new ConfigSlider(option.getId(), this.width / 2 - 155 + counter % 2 * 160, this.height / 6 + 24 * (counter >> 1), option, BetterHurtCam.getTranslatedValue(option), BetterHurtCam.getFloatValue(option)));
            }

            ++counter;
        }
    }

    @Inject(method = "buttonClicked", at = @At("HEAD"), cancellable = true)
    public void handleButtonClicked(Button arg, CallbackInfo ci) {
        if (arg.id == ConfigOption.MULTIPLIER.getId()) ci.cancel();
        if (arg.id == ConfigOption.ENABLED.getId()) {
            BetterHurtCam.getConfig().setEnabled(!BetterHurtCam.getConfig().isEnabled());
            BetterHurtCam.saveConfig();
            arg.text = BetterHurtCam.getTranslatedValue(ConfigOption.ENABLED);
            ci.cancel();
        }
    }
}
