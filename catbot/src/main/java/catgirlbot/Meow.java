package catgirlbot;

import java.time.LocalTime;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;

public class Meow {
        public static void meowtimer (MessageCreateEvent event) {
            try {
                int delay = Integer.parseInt(event.getMessageContent().replaceAll(",", "").substring(14));
                if (delay>-1) {
                    new Thread() {
                        public void run() {
                            try {
                                Meow.meow(event.getChannel(), event.getMessageAuthor(), delay);
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

        public static void meow(TextChannel channel, MessageAuthor author, int delay)throws InterruptedException {
            channel.sendMessage("Okey, I\'ll meow at you in " + delay + " seconds, it is currently " + LocalTime.now());
            Thread.sleep(delay*1000);
            author.asUser().ifPresent(user -> channel.sendMessage("Meow~, " + user.getMentionTag() + ", it has been " + delay + " seconds, it is now " + LocalTime.now()));
        }  
}
