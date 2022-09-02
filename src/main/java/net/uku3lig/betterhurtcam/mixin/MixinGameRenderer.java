package net.uku3lig.betterhurtcam.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.uku3lig.betterhurtcam.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3f;getDegreesQuaternion(F)Lnet/minecraft/util/math/Quaternion;"), method = "bobViewWhenHurt")
    public float changeBobIntensity(float value) {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig().getStrength() * value / 10;
    }

    @Inject(method = "bobViewWhenHurt", at = @At("HEAD"), cancellable = true)
    public void disableHurtCam(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (!AutoConfig.getConfigHolder(ModConfig.class).getConfig().isEnabled()) {
            ci.cancel();
        }
    }
}
