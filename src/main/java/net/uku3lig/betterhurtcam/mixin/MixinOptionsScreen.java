package net.uku3lig.betterhurtcam.mixin;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.gui.widgets.OptionButton;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.config.ConfigScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public class MixinOptionsScreen extends Screen {
    @SuppressWarnings("unchecked")
    @Inject(method = "init()V", at = @At("RETURN"))
    public void addUkulibButton(CallbackInfo ci) {
        this.buttons.add(new OptionButton(169, this.width / 2 - 155 + 160, this.height / 6 + 24 * (5 >> 1), 150, 20, "BetterHurtCam..."));
    }

    @Inject(method = "buttonClicked", at = @At("HEAD"), cancellable = true)
    public void handleButtonClicked(Button arg, CallbackInfo ci) {
        if (arg.id == 169) {
            this.minecraft.openScreen(new ConfigScreen(this, BetterHurtCam.getManager()));
            ci.cancel();
        }
    }
}
