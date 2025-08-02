package com.syshotdev.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.Sound.Type;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundSource;

@Mixin(SoundManager.class)
public class SoundManagerMixin {
    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At("HEAD"))
    private void onPlaySound(SoundInstance soundInstance, CallbackInfo ci) {
      // Move pitch stuff to here so the song only applies to specific blocks
      if (soundInstance.getSource() == SoundSource.BLOCKS){
        
        System.out.println(
            "Playing sound: " + soundInstance.getSource() + 
            //", sound: " + soundInstance.getSound() +
            //", pitch: " + soundInstance.getPitch() +
            ", attenuation: " + soundInstance.getAttenuation());

      }
    }
}
