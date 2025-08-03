package com.syshotdev.mixin.client;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.syshotdev.MegalovaniaSoundsClient;

@Mixin(AbstractSoundInstance.class)
public class AbstractSoundInstanceMixin {

  // Goodness this is terrifying
  // We check if the source equals the source the main class says we should care about,
  // then we change it if it is.
	@Inject(method = "getPitch()F", at = @At("HEAD"), cancellable = true)
	private void returnPitch(CallbackInfoReturnable<Float> cir) {
    AbstractSoundInstance soundInstance = (AbstractSoundInstance) (Object) this;
    if (soundInstance.getSource() == MegalovaniaSoundsClient.self.currentCategory) {
      float pitch = MegalovaniaSoundsClient.self.getNextNotePitch();
      System.out.println("Category: " + soundInstance.getSource().toString() + ", Pitch: " + pitch);
      cir.setReturnValue(pitch);
    } else {
      cir.setReturnValue(1.0f);
    }
	}
}
