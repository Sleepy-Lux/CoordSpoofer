package uk.sleepylux.coordspoofer.client.mixin;

import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.sleepylux.coordspoofer.client.CoordspooferClient;
import uk.sleepylux.coordspoofer.client.MathSecret;

import java.util.List;
import java.util.Locale;

@Mixin(DebugHud.class)
public class DebugHudMixin {

    @Unique
    public float add_remove(float num, float multiplier) {
        boolean positive = true;
        int absolute_seed = Math.abs(CoordspooferClient.coord_seed);
        if (absolute_seed != CoordspooferClient.coord_seed)
            positive = false;

        return (positive) ? num + MathSecret.MathSecret(num, multiplier) : num - MathSecret.MathSecret(num, multiplier);
    }

    @Inject(method = "getLeftText", at = @At("RETURN"), cancellable = true)
    public void modify_debug_screen(CallbackInfoReturnable<List<String>> info) {
        if (!CoordspooferClient.enabled) return;

        List<String> left_text = info.getReturnValue();

        for (int i = 0; i < left_text.size(); i++)
            if (left_text.get(i).contains("XYZ: ")) {
                String[] position = left_text.get(i).split("XYZ: ")[1].split(" / ");
                left_text.set(i, String.format(Locale.ROOT, "XYZ: %.3f / %.5f / %.3f",
                                add_remove(Float.parseFloat(position[0]), 0.75f),
                                add_remove(Float.parseFloat(position[1]), 1.0f),
                                add_remove(Float.parseFloat(position[2]), 1.25f)
                        )
                );
            }
        info.setReturnValue(left_text);
    };
}
