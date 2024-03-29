package net.uku3lig.betterhurtcam.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/RotationAxis;rotationDegrees(F)Lorg/joml/Quaternionf;"), method = "tiltViewWhenHurt", require = 4)
    public float changeBobIntensity(float value) {
        return (float) (BetterHurtCam.getManager().getConfig().getMultiplier() * value);
    }

    @Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
    public void disableHurtCam(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (!BetterHurtCam.getManager().getConfig().isEnabled()) ci.cancel();
    }

    @Redirect(method = "tiltViewWhenHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getDamageTiltYaw()F"))
    public float changeHurtCamType(LivingEntity instance) {
        return switch (BetterHurtCam.getManager().getConfig().getType()) {
            case OLD -> 0;
            case YAW_BASED -> instance.getDamageTiltYaw();
        };
    }
}
