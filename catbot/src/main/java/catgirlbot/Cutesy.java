package catgirlbot;

import org.javacord.api.event.message.MessageCreateEvent;


public class Cutesy {
    public static void main (MessageCreateEvent event) {

        if (event.getMessageContent().equalsIgnoreCase("mrrp")) {
            event.getMessage().getChannel().sendMessage("nyaa~");
        }

        else if (event.getMessageContent().equalsIgnoreCase("be gay")) {
            event.getMessage().getChannel().sendMessage("Do crime :sunglasses:");
        }

        else if (event.getMessageContent().toLowerCase().contains("can")&&event.getMessageContent().toLowerCase().contains("have headpats")) {
            event.getMessage().getChannel().sendMessage("Of course! *pat* *pat* *pat* :smile:");
        }

        else if (event.getMessageContent().toLowerCase().contains("can")&&event.getMessageContent().toLowerCase().contains("have hug")) {
            event.getMessage().getChannel().sendMessage("Of course! :people_hugging:");
        }

        else if (event.getMessageContent().toLowerCase().contains("hey")&&event.getMessageContent().toLowerCase().contains("have hug")) {
            event.getMessage().getChannel().sendMessage("Of course! :people_hugging:");
        }

        



    }
    
}
