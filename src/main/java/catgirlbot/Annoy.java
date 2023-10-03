package catgirlbot;

import java.util.Optional;

import org.javacord.api.entity.channel.PrivateChannel;

import org.javacord.api.event.message.MessageCreateEvent;

public class Annoy {
    public static void checkannoy (MessageCreateEvent event) {
        if (event.getMessageContent().toLowerCase().contains("annoy") && event.getMessageContent().toLowerCase().contains("catgirl") && event.getMessageContent().toLowerCase().contains("me")){
            Optional<PrivateChannel> chan = event.getMessageAuthor().asUser().get().getPrivateChannel();
            event.getMessage().reply("Okey, I'm gonna annoy you >:3 (tell me to stop in dm to make me stop)");
            chan.ifPresentOrElse((channel) -> {
                new Thread() {
                    public void run() {
                        try {
                            annoy(channel);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            },() ->{
                    new Thread() {
                        public void run() {
                            try {
                                annoy(event.getMessageAuthor().asUser().get().openPrivateChannel().join());
                            } catch (Exception e) {
                              e.printStackTrace();
                            }
                        }
                    }.start();
            });
        }
    }

    public static void annoy(PrivateChannel channel)throws Exception {
        channel.sendMessage("Meow~");
        for (int i = 0; i<500; i++) {
            Thread.sleep(Math.abs((long) (450000*Math.cos(i))));
            if (channel.getMessages(1).get().getNewestMessage().get().getContent().equalsIgnoreCase("stop")) {
                i=500;
                channel.sendMessage("Okey I stop :/");
                System.out.println("Stopped annoying " + channel.getRecipient().get().getName());
            } else {
                channel.sendMessage("Meow~");
                System.out.println("Hehe annoyed " + channel.getRecipient().get().getName());
            }
        }
    }  
}
