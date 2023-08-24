package catgirlbot;



import java.util.BitSet;
import java.util.HashMap;

import org.javacord.api.*;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.json.JSONObject;




public class Main {
    //public static JSONObject perms;
    
    

    public static void main(String[] args) {
        // Insert your bot's token here
        String token = System.getenv("SECONDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();
        JSONObject perms = Perms.loadPerms();
        HashMap<Long, BitSet> parsedPerms = Perms.parsePerms(perms);

        api.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
            if (slashCommandInteraction.getCommandName().equals("catgirlbot")) {
                slashCommandInteraction.createImmediateResponder().setContent("I did not set this up yet").setFlags(MessageFlag.EPHEMERAL).respond();
            }
        });

        api.addMessageCreateListener(event -> {
            if (parsedPerms.containsKey(event.getChannel().getId())){

                // block for meow timer
                if (parsedPerms.get(event.getChannel().getId()).get(0)&&event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 14)).equalsIgnoreCase("Meow at me in ")) {
                    Meow.meowtimer(event);
                }
            // end of block for meow timer

            // begin general block for commands starting with catgirl, 
                if (event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 9)).equalsIgnoreCase("catgirl, ")) {
                    if (parsedPerms.get(event.getChannel().getId()).get(1)&&event.getMessageContent().substring(9).equalsIgnoreCase("treasure hunt")) {
                        new Treasurehunt(event, api).start();
                    }
                }

                if (event.getMessageContent().equalsIgnoreCase("catgirl, save settings")) {
                    Perms.savePerms(perms);
                }

            // end general block for commands starting with catgirl,
            }
        });
    }
}