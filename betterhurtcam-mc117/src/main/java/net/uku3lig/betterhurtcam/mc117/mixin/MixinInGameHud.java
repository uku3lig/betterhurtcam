package net.uku3lig.betterhurtcam.mc117.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    @ModifyArg(method = "renderStatusBars", index = 10, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHealthBar(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/entity/player/PlayerEntity;IIIIFIIIZ)V"))
    public boolean renderHearts(boolean blinking) {
        return blinking && BetterHurtCam.getManager().getConfig().isHeartBlink();
    }
}
