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





    }
    
}
