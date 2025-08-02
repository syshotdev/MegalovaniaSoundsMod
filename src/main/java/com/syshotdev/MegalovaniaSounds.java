package com.syshotdev;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Add megalovania pitches (generate them or something)
// Also: Add them into the pitch generator
// Also: Figure out how to set all the pitches (I've found one place, but the pitches still change)
// Also: Clean up these classes like what the heck (and rename them)
// Also: Make a todo
// Also: Add to git
//
// Also: (Writing this days later):
// Make megalovania notes only apply to a certain category of sound
// The category of sound clears after 5 seconds of silence
// Certain categories have priority. Blocks > items > animals > music discs > misc

public class MegalovaniaSounds implements ModInitializer {
	public static final String MOD_ID = "megalovania-sounds";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  @Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}

}
// K I've found a couple places it could be:
// BlockSoundGroup.mapping
// SoundEntry.mapping
// SoundInstance.mapping
// PlaySoundS2CPacket.mapping
// Maybe Sound.mapping? But that would distort the entire game. Which would be funnier
// SoundEvents.mapping?
// AmbientSoundPlayer.mapping?

/*
### 4. **Inject Code Using Mixins**

Fabric uses [Mixin](https://github.com/SpongePowered/Mixin) for injecting code into existing classes. Here’s a simplified example of how you might use Mixin to inject code:

- **Create a Mixin Class:** Define a new class that will contain your injected methods.

```java
package com.example.myfabricmod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(method = "interactBlock", at = @At("HEAD"), cancellable = true)
    private void onInteractBlock(World world, ServerPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        // Your custom code here
        if (world.isClient) return; // Ensure this runs only on the server side

        // Modify block pitch or other properties here
        // For example:
        // player.setPitch(50f); // Adjust pitch as needed
        // BlockState state = world.getBlockState(hitResult.getBlockPos());
        // world.setBlockState(hitResult.getBlockPos(), state.with(SOME_PROPERTY, SOME_VALUE), ...);

        // You can also cancel the original behavior and replace it with your own
        cir.cancel();
        cir.setReturnValue(ActionResult.SUCCESS); // Or ActionResult.PASS if you want to continue processing
    }
}
```

### 5. **Configure Mixins**

- **Mixin Configuration File:** Ensure that your mixin configuration file (usually in `src/main/resources`) is set up correctly and includes your mixin class.

```json
{
  "required": true,
  "package": "com.example.myfabricmod.mixin",
  "compatibilityLevel": "JAVA_8",
  "mixins": [
    "ServerPlayerEntityMixin"
  ],
  "injectors": {
    "defaultRequire": 1
  }
}
```

### 6. **Build and Test Your Mod**

- **Compile the Mod:** Build your mod using Maven or Gradle.
- **Load the Mod:** Place your compiled `.jar` file in the `mods` folder of your Minecraft instance.
- **Test in-game:** Launch Minecraft and test your changes to ensure that the block pitch is being controlled as expected.

### 7. **Refine and Debug**

- **Debugging:** Use logs and breakpoints within your IDE to debug your injected code.
- **Optimization:** Refine your logic based on testing feedback, ensuring performance remains unaffected.

By following these steps, you should be able to inject custom behavior into Minecraft's block placement logic to control the pitch or other properties of placed blocks. Remember that modding can be complex and may require a good understanding of both Java programming and the Minecraft codebase.
*/
