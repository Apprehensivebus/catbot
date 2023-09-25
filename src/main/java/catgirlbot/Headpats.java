package catgirlbot;

import org.javacord.api.event.message.MessageCreateEvent;

public class Headpats {
    private static final String[] Patlist = {
        "https://tenor.com/view/pat-gif-19836590",
        "https://tenor.com/view/pat-gif-19836593",
        "https://tenor.com/view/head-pat-anime-kawaii-neko-nyaruko-gif-15735895",
        "https://tenor.com/view/kanna-kamui-pat-head-pat-gif-12018819",
        "https://tenor.com/view/anime-head-pat-anime-head-rub-neko-anime-love-anime-gif-16121044",
        "https://tenor.com/view/pat-gif-19389750",
        "https://tenor.com/view/anime-pat-gif-22001971",
        "https://tenor.com/view/pat-garrys-mod-garrys-mod-physics-fast-intense-gif-26322619",
        "https://tenor.com/view/qualidea-code-head-pat-anime-anime-girl-blush-anime-gif-24627864",
        "https://tenor.com/view/pat-gif-20637974"
    };

    public static void doPat (MessageCreateEvent event) {
        event.getMessage().getChannel().sendMessage("Of course!");
        event.getMessage().getChannel().sendMessage(Patlist[(int) Math.round(Math.random()*9)]);
    }

    
}
