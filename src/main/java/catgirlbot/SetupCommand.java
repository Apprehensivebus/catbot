package catgirlbot;

import java.util.Arrays;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandOptionType;

public class SetupCommand {

    public static void setupCommand() {

        String token = System.getenv("SECONDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();


        SlashCommand.with("catgirlbot", "Command used to control catgirlbot",
            Arrays.asList(
                SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND_GROUP, "permissions", "Manage catgirlbot's permissions",
                    Arrays.asList(
                        SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "toggle", "Allows a permission to a user for a channel",
                            Arrays.asList(
                                SlashCommandOption.create(SlashCommandOptionType.CHANNEL, "channel", "The channel for which to toggle the permission", true),
                                SlashCommandOption.create(SlashCommandOptionType.LONG, "permission", "The permission to toggle", true
        )))))))
        .createGlobal(api)
        .join();

        SlashCommand.with("catgirlbotweirb", "Command used to control catgirlbot",
            Arrays.asList(
                SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND_GROUP, "weirb", "Manage weird characters",
                    Arrays.asList(
                        SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "add", "Adds a weird character",
                            Arrays.asList(
                                SlashCommandOption.create(SlashCommandOptionType.STRING, "weirbchar", "The weird character used to evade filters", true),
                                SlashCommandOption.create(SlashCommandOptionType.STRING, "normchar", "What normal character people use it as", true
        )))))))
        .createGlobal(api)
        .join();

        SlashCommand.with("catgirlbotweirb2", "Command used to control catgirlbot",
            Arrays.asList(
                SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND_GROUP, "weirb", "Manage weird characters",
                    Arrays.asList(
                        SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "test", "Adds a weird character",
                            Arrays.asList(
                                SlashCommandOption.create(SlashCommandOptionType.STRING, "word", "The word to convert from weird to normal", true)
        ))))))
        .createGlobal(api)
        .join();


        SlashCommand.with("catgirlbotbad", "Command used to control catgirlbot",
            Arrays.asList(
                SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND_GROUP, "bad", "Manage weird characters",
                    Arrays.asList(
                        SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "add", "Adds a weird character",
                            Arrays.asList(
                                SlashCommandOption.create(SlashCommandOptionType.STRING, "badword", "The weird character used to evade filters", true),
                                SlashCommandOption.create(SlashCommandOptionType.STRING, "goodword", "What normal character people use it as", true
        )))))))
        .createGlobal(api)
        .join();

        SlashCommand.with("catgirlbotbad2", "Command used to control catgirlbot",
            Arrays.asList(
                SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND_GROUP, "bad", "Manage weird characters",
                    Arrays.asList(
                        SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "test", "Adds a weird character",
                            Arrays.asList(
                                SlashCommandOption.create(SlashCommandOptionType.STRING, "word", "The word to convert from weird to normal", true)
        ))))))
        .createGlobal(api)
        .join();

    
    }
    

    public static void deleteAllCommands() {
        String token = System.getenv("SECONDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();
        api.getGlobalSlashCommands().join().forEach(command -> command.delete().join());
        api.getServers().forEach((server) -> {
            api.getServerSlashCommands(server).join().forEach(command -> command.delete().join());
        });
    }
}
