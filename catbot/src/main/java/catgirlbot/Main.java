package catgirlbot;

import java.time.LocalTime;

import org.javacord.api.*;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.MessageAuthor;



public class Main {
     public static void Meow(TextChannel channel, MessageAuthor author, int delay)throws InterruptedException {
        channel.sendMessage("Okey, I\'ll meow at you in " + delay + " seconds, it is currently " + LocalTime.now());
        Thread.sleep(delay*1000);
        author.asUser().ifPresent(user -> channel.sendMessage("Meow~, " + user.getMentionTag() + ", it has been " + delay + " seconds, it is now " + LocalTime.now()));
    }
    
    public static void main(String[] args) {
        // Insert your bot's token here
        String token = System.getenv("SECONDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();
        api.addMessageCreateListener(event -> {

            // block for meow timer
            if (event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 14)).equalsIgnoreCase("Mrrp at me in ")) {
                try {
                    int delay = Integer.parseInt(event.getMessageContent().replaceAll(",", "").substring(14));
                    if (delay>-1) {
                        new Thread(){
                            public void run() {
                                try {
                                    Meow(event.getChannel(), event.getMessageAuthor(), delay);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    } else {
                        event.getChannel().sendMessage("That...... is not a positive number");
                    }
                } catch (Exception e) {
                    event.getChannel().sendMessage("That...... is not a positive number"); // catch the exception when people give non--numbers as timer delay
                }
            }
            // end of block for meow timer

            // begin general block for commands starting with catgirl,
            if (event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 9)).equalsIgnoreCase("catgirl, ")) {
                event.getChannel().sendMessage("Command test, received:");

            }
            // end general block for commands starting with catgirl,
        });
    }
}