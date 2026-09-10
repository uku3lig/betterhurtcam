package net.uku3lig.betterhurtcam.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.state.level.CameraEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @ModifyArg(at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;rotateDegrees(Lcom/mojang/math/Axis;F)V"), method = "bobHurt", index = 1, require = 4)
    public float changeBobIntensity(float value) {
        return (float) (BetterHurtCam.getManager().getConfig().getMultiplier() * value);
    }

    @Inject(method = "bobHurt", at = @At("HEAD"), cancellable = true)
    public void disableHurtCam(CameraRenderState cameraState, PoseStack poseStack, CallbackInfo ci) {
        if (!BetterHurtCam.getManager().getConfig().isEnabled()) ci.cancel();
    }

    @WrapOperation(method = "bobHurt", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/state/level/CameraEntityRenderState;hurtDir:F", opcode = Opcodes.GETFIELD))
    public float changeHurtCamType(CameraEntityRenderState instance, Operation<Float> original) {
        return switch (BetterHurtCam.getManager().getConfig().getType()) {
            case OLD -> 0;
            case YAW_BASED -> original.call(instance);
        };
    }
}
