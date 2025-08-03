package com.syshotdev.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.syshotdev.MegalovaniaSoundsClient;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundSource;

@Mixin(SoundManager.class)
public class SoundManagerMixin {
    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At("HEAD"))
    private void onPlaySound(SoundInstance soundInstance, CallbackInfo ci) {
      SoundSource soundCategory = soundInstance.getSource();
      switch (soundCategory) {
      default:
        return;
      // Ignore all other sounds except these
      case SoundSource.BLOCKS:
        break;
      case SoundSource.NEUTRAL:
        break;
      case SoundSource.RECORDS:
        break;
      case SoundSource.MASTER:
        break;
      }

      if (MegalovaniaSoundsClient.self.timeToResetCategory(soundCategory)) {
        MegalovaniaSoundsClient.self.resetCategory(soundCategory);
      }
    }
}
