package catgirlbot;
import java.time.LocalTime;

import org.javacord.api.*;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.MessageAuthor;



public class Main {
     public static void Meow(TextChannel channel, MessageAuthor author, int delay)throws InterruptedException {
        channel.sendMessage("Nyaa~ (1) "+LocalTime.now());
        Thread.sleep(delay*1000);
        channel.sendMessage("Nyaa~ (2) "+LocalTime.now());
    }
    
    public static void main(String[] args) {
        // Insert your bot's token here
        String token = System.getenv("SECONDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();
        

   
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("Mrrp")) {
                    new Thread(){public void run() {try {Meow(event.getChannel(), event.getMessageAuthor(), 10);} catch (InterruptedException e) {e.printStackTrace();}}}.start();
            }
        });
    }

}