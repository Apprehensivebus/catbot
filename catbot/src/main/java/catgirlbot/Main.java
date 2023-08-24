package catgirlbot;

import java.util.BitSet;
import java.util.HashMap;

import org.javacord.api.*;
import org.javacord.api.entity.intent.Intent;




public class Main {
    static Long homechannel = 1142829953562464267L; // temp solution until I add commands to add channels and file storage for persistence
    static BitSet homeperms= new BitSet(2);

    public static HashMap<Long, BitSet> initPerms(){ //creates the permission bitset for each channel
        HashMap<Long, BitSet> perms= new HashMap<Long, BitSet>(); 
        homeperms.set(1);
        homeperms.set(0);
        perms.put(homechannel,homeperms); // woo bitset
        return perms;
    }
    
    public static void main(String[] args) {
        // Insert your bot's token here
        String token = System.getenv("SECONDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();
        HashMap<Long, BitSet> perms=initPerms();
        api.addMessageCreateListener(event -> {
            if (perms.containsKey(event.getChannel().getId())){

                // block for meow timer
                if (perms.get(event.getChannel().getId()).get(0)&&event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 14)).equalsIgnoreCase("Meow at me in ")) {
                    Meow.meowtimer(event);
                }
            // end of block for meow timer

            // begin general block for commands starting with catgirl,
                if (perms.get(event.getChannel().getId()).get(1)&&event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 9)).equalsIgnoreCase("catgirl, ")) {
                    if (event.getMessageContent().substring(9).equalsIgnoreCase("treasure hunt")) {
                        new Treasurehunt(event, api).start();
                    }
                }
            // end general block for commands starting with catgirl,
            }
        });
    }
}