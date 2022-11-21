package net.uku3lig.betterhurtcam.mc116.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    @Redirect(method = "renderStatusBars", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/hud/InGameHud;heartJumpEndTick:J", ordinal = 0))
    public long renderHearts(InGameHud instance) {
        // this is *somewhat* hacky, we do this to make sure this.heartJumpEndTick is ALWAYS smaller than this.ticks
        return Long.MIN_VALUE;
    }
}
