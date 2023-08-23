package catgirlbot;

import java.time.LocalTime;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;

public class Meow{
        public static void meow(TextChannel channel, MessageAuthor author, int delay)throws InterruptedException {
            channel.sendMessage("Okey, I\'ll meow at you in " + delay + " seconds, it is currently " + LocalTime.now());
            Thread.sleep(delay*1000);
            author.asUser().ifPresent(user -> channel.sendMessage("Meow~, " + user.getMentionTag() + ", it has been " + delay + " seconds, it is now " + LocalTime.now()));
        }
   
}
