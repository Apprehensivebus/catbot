package catgirlbot;

import java.util.Arrays;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandOptionType;

public class SetupCommand {

    public static void setupCommand(){

        String token = System.getenv("SECONDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();


        SlashCommand.with("catgirlbot2", "Command used to control catgirlbot",
            Arrays.asList(
                SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND_GROUP, "permissions", "Manage catgirlbot's permissions",
                    Arrays.asList(
                        SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "toggle", "Allows a permission to a user for a channel",
                            Arrays.asList(
                                SlashCommandOption.create(SlashCommandOptionType.CHANNEL, "channel", "The channel for which to toggle the permission", true),
                                SlashCommandOption.create(SlashCommandOptionType.STRING, "permission", "The permission to toggle", true
        )))))))
        .createGlobal(api)
        .join();

    
    }
    
}