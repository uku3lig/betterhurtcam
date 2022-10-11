package net.uku3lig.betterhurtcam.mc114.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.mc114.ConfigScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SettingsScreen.class)
public class MixinSettingsScreen extends Screen {
    @Inject(method = "init", at = @At("RETURN"))
    public void addBetterHurtCamButton(CallbackInfo ci) {
        this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 24 - 6, 150, 20,
                "BetterHurtCam...", button -> MinecraftClient.getInstance().openScreen(new ConfigScreen(this, BetterHurtCam.getManager()))));
    }

    protected MixinSettingsScreen(Text title) {
        super(title);
    }
}
