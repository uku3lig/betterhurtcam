package net.uku3lig.betterhurtcam.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.render.GameRenderer;
import net.uku3lig.betterhurtcam.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3f;getDegreesQuaternion(F)Lnet/minecraft/util/math/Quaternion;"), method = "bobViewWhenHurt")
    public float changeBobIntensity(float value) {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig().getMultiplier() * value;
    }
}
