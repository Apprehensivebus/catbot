package catgirlbot;



import java.util.BitSet;
import java.util.HashMap;

import org.javacord.api.*;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.json.JSONObject;




public class Main {
    //public static JSONObject perms;
    static JSONObject perms;
    static HashMap<Long, BitSet> parsedPerms;

    public static void main(String[] args) {
        // Insert your bot's token here
        String token = System.getenv("SECONDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();
        perms = Perms.loadPerms();
        parsedPerms = Perms.parsePerms(perms);

        api.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
            if (slashCommandInteraction.getFullCommandName().equals("catgirlbot permissions toggle")) {
                if (slashCommandInteraction.getUser().isBotOwner()) {
                    ServerChannel channel = slashCommandInteraction.getArgumentChannelValueByName("channel").get();
                    int permission = slashCommandInteraction.getArgumentLongValueByName("permission").get().intValue();
                    slashCommandInteraction.createImmediateResponder().setContent("You tried to toggle permission " + permission + " in channel " + channel.getName()).setFlags(MessageFlag.EPHEMERAL).respond().join();
                    perms.put(String.valueOf(channel.getId()), permission);
                    Perms.savePerms(perms);
                    perms = Perms.loadPerms();
                    parsedPerms = Perms.parsePerms(perms);
                    System.out.println("Successfully changed perms!");
                }
            }
        });

        api.addMessageCreateListener(event -> {
            if (event.getMessageAuthor().isRegularUser() && parsedPerms.containsKey(event.getChannel().getId())) {

                // block for meow timer, perm value 1
                if (parsedPerms.get(event.getChannel().getId()).get(0) && event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 14)).equalsIgnoreCase("Meow at me in ")) {
                    Meow.meowtimer(event);
                    return;
                }
                // end of block for meow timer
                
                // begin general block for commands starting with catgirl,  perm value 2
                if (event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 9)).equalsIgnoreCase("catgirl, ")) {
                    if (parsedPerms.get(event.getChannel().getId()).get(1) && event.getMessageContent().substring(9).equalsIgnoreCase("treasure hunt")) {
                        new Treasurehunt(event, api).start();
                        return;
                    }
                }
                // end general block for commands starting with catgirl,

                // begin random cutesy stuff blocck, perm value 4
                if (parsedPerms.get(event.getChannel().getId()).get(2)){ 
                    Cutesy.main(event);
                }
                
                // begin anarchy chess, perm value 8
                if (parsedPerms.get(event.getChannel().getId()).get(3)){ 
                    AnCh.main(event);
                }

                //  begin quote magic, perm value 16
                if (parsedPerms.get(event.getChannel().getId()).get(4)){ 
                    Quotes.main(event, api);
                }



            
            }
        });
    }
}