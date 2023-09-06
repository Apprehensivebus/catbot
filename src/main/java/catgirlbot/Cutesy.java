package catgirlbot;

import org.javacord.api.event.message.MessageCreateEvent;

// this file is an if tree, if you require comments please find help

public class Cutesy {
    public static void doCute (MessageCreateEvent event) {

        if (event.getMessageContent().equalsIgnoreCase("mrrp")) {
            event.getMessage().getChannel().sendMessage("nyaa~");
        }

        else if (event.getMessageContent().equalsIgnoreCase("be gay")) {
            event.getMessage().getChannel().sendMessage("Do crime :sunglasses:");
        }

        else if (event.getMessageContent().toLowerCase().contains("can") && event.getMessageContent().toLowerCase().contains("have headpats")) {
            event.getMessage().getChannel().sendMessage("Of course! *pat* *pat* *pat* :smile:");
        }

        else if (event.getMessageContent().toLowerCase().contains("can") && event.getMessageContent().toLowerCase().contains("have hug")) {
            event.getMessage().getChannel().sendMessage("Of course! :people_hugging:");
        }

        else if (event.getMessageContent().toLowerCase().contains("m not cute") || event.getMessageContent().toLowerCase().contains("m uncute")) {
            event.getMessage().reply(":warning: ``Independent fact checkers have verified that this claim to uncuteness is false`` :warning:");
        }

        else if (event.getMessageContent().toLowerCase().contains("catgirl") && (event.getMessageContent().toLowerCase().contains("github") || event.getMessageContent().toLowerCase().contains("source"))) {
            event.getMessage().reply("Of course! https://github.com/Apprehensivebus/catbot/");
        }

        else if (event.getMessageContent().toLowerCase().contains("launch codes")) {
            event.getMessage().reply("``This message was redacted for national security reasons``");
        }

        else if (event.getMessageContent().toLowerCase().contains("can") && event.getMessageContent().toLowerCase().contains("catgirl") && event.getMessageContent().toLowerCase().contains("do") && event.getMessageContent().toLowerCase().contains("what")) {
            event.getMessage().reply("Oh my god I thought you'd never ask! \n I can reply silly things to certain trigger messages \n I can respond with the meme chain from the AnarchyChess subreddit \n I can do alarms or reminders (ask me to meow at you in x amount of time! \n I can play treasure hunt! \n I can do quotes with the following commands: (catgirl, ) grab quote:x, random quote, add quote (messagelink) \n I'm cute");
        }



    }
    
}
