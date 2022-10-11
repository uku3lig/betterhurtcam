package net.uku3lig.betterhurtcam.mc114.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.GameRenderer;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Redirect(method = "bobViewWhenHurt", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;rotatef(FFFF)V"))
    public void changeBobIntensity(float angle, float x, float y, float z) {
        angle = (float) (BetterHurtCam.getManager().getConfig().getMultiplier() * angle);
        GlStateManager.rotatef(angle, x, y, z);
    }

    @Inject(method = "bobViewWhenHurt", at = @At("HEAD"), cancellable = true)
    public void disableHurtCam(float tickDelta, CallbackInfo ci) {
        if (!BetterHurtCam.getManager().getConfig().isEnabled()) ci.cancel();
    }
}
