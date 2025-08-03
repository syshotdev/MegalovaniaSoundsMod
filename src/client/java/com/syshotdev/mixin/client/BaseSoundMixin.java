package com.syshotdev.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;

// NOT WORKING!!!
// A mixin to change the `sound` instance constructor entirely.
@Mixin(Sound.class)
public class BaseSoundMixin {

  // Does not work for now, I am not sure why.
  // Complains that is not static (I think I fixed it), but now it doesn't like all the parameters?
  //@Inject(method = "<clinit>(Lnet/minecraft/class_2960;Lnet/minecraft/class_7373;Lnet/minecraft/class_7373;ILnet/minecraft/class_1111$class_1112;ZZI)V", at = @At("HEAD"))
	@Inject(at = @At(value = "RETURN"), method = "<init>")
  private static void initSound(Object id, Object volume, SoundInstance soundInstance, int weight, Object registrationType,
      boolean stream, boolean preload, int attenuation, CallbackInfo ci) {
    System.out.println("Playing sound: " + soundInstance.getSource() + ", pitch=" + soundInstance.getPitch());
  }
}
