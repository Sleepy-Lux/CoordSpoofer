package uk.sleepylux.coordspoofer.client;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.random.Random;

import java.util.UUID;

public class CoordspooferClient implements ClientModInitializer {
    public static boolean enabled = true;
    public static int coord_seed = Random.create().nextInt(((1_000_000 - -1_000_000) + 1) + -1_000_000);

    @Override
    public void onInitializeClient() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("setCoordSeed")
                    .then(CommandManager.argument("CoordSeed", IntegerArgumentType.integer(-1_000_000, 1_000_000))
                        .executes(context -> {
                            int new_seed = IntegerArgumentType.getInteger(context, "CoordSeed");
                            coord_seed = new_seed;
                            context.getSource().sendMessage(Text.literal("Set CoordSeed to: ")
                                    .styled(style -> style.withColor(Formatting.GREEN)).append(Text.literal(String.valueOf(new_seed))
                                            .styled(style -> style.withColor(Formatting.LIGHT_PURPLE))));
                            return 1;
                        }))
            );
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("getCoordSeed")
                .executes(context -> {
                    context.getSource().sendMessage(Text.literal("CoordSeed is: ")
                            .styled(style -> style.withColor(Formatting.GREEN)).append(Text.literal(String.valueOf(CoordspooferClient.coord_seed))
                                    .styled(style -> style.withColor(Formatting.LIGHT_PURPLE))));
                    return 1;
                })
            );
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("toggleCoordSpoofer")
                    .executes(context -> {
                        CoordspooferClient.enabled = !CoordspooferClient.enabled;
                        context.getSource().sendMessage(Text.literal("CoordSpoofer is now ")
                                .styled(style -> style.withColor(Formatting.GREEN)).append(Text.literal((CoordspooferClient.enabled) ? "Enabled":"Disabled")
                                        .styled(style -> style.withColor(Formatting.LIGHT_PURPLE))));
                        return 1;
                    })
            );
        });
    }
}
