package com.maxheyn.spawnbook;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.io.FileNotFoundException;

public class SpawnBookCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("spawnbook").requires(src -> src.hasPermissionLevel(0)).executes(SpawnBookCommand::giveSpawnBook));
    }

    public static int giveSpawnBook(CommandContext<ServerCommandSource> context) {
        ServerCommandSource src = context.getSource();
        try {
            Spawnbook.giveSpawnBook(src.getPlayer());
        } catch (FileNotFoundException e) {
            src.sendFeedback(Text.literal("Config file has not been found, the SpawnBook could not be created.").formatted(Formatting.RED), true);
            return -1;
        }
        return 1;
    }
}
