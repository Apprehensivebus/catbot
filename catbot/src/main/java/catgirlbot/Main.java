package catgirlbot;

import java.util.HashMap;

import org.javacord.api.*;
import org.javacord.api.entity.intent.Intent;




public class Main {
    static Long homechannel = 1142829953562464267L;

    public static HashMap<Long, String> initPerms(){
        HashMap<Long, String> perms= new HashMap<Long, String>(); 
        perms.put(homechannel,"11"); 
        return perms;
    }
    
    public static void main(String[] args) {
        // Insert your bot's token here
        String token = System.getenv("SECONDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();
        HashMap<Long, String> perms=initPerms();
        api.addMessageCreateListener(event -> {
            if (perms.containsKey(event.getChannel().getId())){

                // block for meow timer
                if (perms.get(event.getChannel().getId()).charAt(0)=='1'&&event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 14)).equalsIgnoreCase("Meow at me in ")) {
                    Meow.meowtimer(event);
                }
            // end of block for meow timer

            // begin general block for commands starting with catgirl,
                if (perms.get(event.getChannel().getId()).charAt(1)=='1'&&event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 9)).equalsIgnoreCase("catgirl, ")) {
                    if (event.getMessageContent().substring(9).equalsIgnoreCase("treasure hunt")) {
                        new Treasurehunt(event, api).start();
                    }
                }
            // end general block for commands starting with catgirl,
            }
        });
    }
}