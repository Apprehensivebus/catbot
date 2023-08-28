package catgirlbot;

import org.javacord.api.event.message.MessageCreateEvent;

// this file is an if tree, if you require comments please find help

public class AnCh {
    public static void main (MessageCreateEvent event) {

        if (event.getMessageContent().substring(0,6).equalsIgnoreCase("google")) {
            event.getMessage().getChannel().sendMessage("Holy hell :cat_wtf:");
        }

        else if (event.getMessageContent().equalsIgnoreCase("holy hell")) {
            event.getMessage().getChannel().sendMessage("New catgirl just dropped! :rainbow_flag:");
        }

        else if (event.getMessageContent().toLowerCase().contains("new") && event.getMessageContent().toLowerCase().contains("just dropped")) {
            event.getMessage().getChannel().sendMessage("Call the animal shelter!");
        }

        else if (event.getMessageContent().toLowerCase().contains("call the")) {
            event.getMessage().getChannel().sendMessage("Catgirl went on vacation, never came back :pensive:");
        }

        else if (event.getMessageContent().toLowerCase().contains("went on vacation") && event.getMessageContent().toLowerCase().contains("never came back")) {
            event.getMessage().getChannel().sendMessage("Catgirl storm incoming! :cat_blush:");
        }
    }
}
