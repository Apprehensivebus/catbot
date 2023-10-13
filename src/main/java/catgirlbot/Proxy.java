package catgirlbot;

import java.util.Scanner;
import org.javacord.api.*;
import org.javacord.api.entity.channel.TextChannel;

public class Proxy {
    static String channel = "1146823564306174043";
    static String result;
    public static void doProxy (DiscordApi api) {
        Scanner term = new Scanner(System.in);
        while(true){
            String result = term.nextLine();  // Read user input
            if (result.startsWith("/")) {
                channel=result.substring(1);
            } else {
                ((TextChannel) api.getChannelById(channel).get()).sendMessage(result);
            }
        }
    }
    public static void makeProxy (DiscordApi api) {
        new Thread() {
            public void run() {
                    Proxy.doProxy(api);;
            }
        }.start();
    }
}
