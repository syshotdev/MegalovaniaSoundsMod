package com.syshotdev.mixin.client;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.syshotdev.MegalovaniaSoundsClient;

@Mixin(AbstractSoundInstance.class)
public class AbstractSoundInstanceMixin {
	@Inject(method = "getPitch", at = @At("HEAD"), cancellable = true)
	private void returnPitch(CallbackInfoReturnable<Float> cir) {
    float pitch = MegalovaniaSoundsClient.self.getNextNotePitch();
    System.out.println("Pitch: " + pitch);
    cir.setReturnValue(pitch);
	}
}
