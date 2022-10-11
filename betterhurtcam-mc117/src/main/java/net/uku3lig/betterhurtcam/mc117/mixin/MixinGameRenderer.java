package net.uku3lig.betterhurtcam.mc117.mixin;

import net.minecraft.client.render.GameRenderer;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3f;getDegreesQuaternion(F)Lnet/minecraft/util/math/Quaternion;"), method = "bobViewWhenHurt", require = 4)
    public float changeBobIntensity(float value) {
        return (float) (BetterHurtCam.getManager().getConfig().getMultiplier() * value);
    }
}
