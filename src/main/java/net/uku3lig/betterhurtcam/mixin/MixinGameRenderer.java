package net.uku3lig.betterhurtcam.mixin;

import net.minecraft.client.render.GameRenderer;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Redirect(method = "method_1849", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", remap = false))
    public void changeBobIntensity(float angle, float x, float y, float z) {
        angle = BetterHurtCam.getManager().getConfig().getMultiplier() * angle;
        GL11.glRotatef(angle, x, y, z);
    }

    @Inject(method = "method_1849", at = @At("HEAD"), cancellable = true)
    public void disableHurtCam(float f, CallbackInfo ci) {
        if (!BetterHurtCam.getManager().getConfig().isEnabled()) ci.cancel();
    }
}
