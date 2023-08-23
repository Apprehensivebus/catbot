package catgirlbot;

import org.javacord.api.*;
import org.javacord.api.entity.intent.Intent;




public class Main {

    
    public static void main(String[] args) {
        // Insert your bot's token here
        String token = System.getenv("SECONDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();
        api.addMessageCreateListener(event -> {

            // block for meow timer
            if (event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 14)).equalsIgnoreCase("Mrrp at me in ")) {
                Meow.meowtimer(event);
            }
            // end of block for meow timer

            // begin general block for commands starting with catgirl,
            if (event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 9)).equalsIgnoreCase("catgirl, ")) {
                if (event.getMessageContent().substring(9).equalsIgnoreCase("treasure hunt")) {
                    event.getChannel().sendMessage("Treasure hunt test command received");
                    new Treasurehunt(event, api).start();
                }
            }
            // end general block for commands starting with catgirl,

        });
    }
}