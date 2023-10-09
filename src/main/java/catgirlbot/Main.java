package catgirlbot;





import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.javacord.api.*;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageSet;






public class Main {
    static MessageSet msgs;

    public static void main(String[] args) {
        String token = System.getenv("THIRDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();


        api.addMessageCreateListener(event -> {
            if (event.getMessageAuthor().isBotOwner() && event.getMessageContent().equals("Catgorl, start project Céleste.")) {
                System.out.println("oh lawd");
                msgs = event.getChannel().getMessages(300000).join();
                System.out.println(msgs.size());
                var list = new ArrayList<Message>();
                list.addAll(msgs);
                list.removeIf(m -> m.getAuthor().getId()!=63359901975449600L);
                System.out.println(list.size());
                try {
                    FileOutputStream fos = new FileOutputStream(("vin.txt"), false); // get file named after the server
                for (int k = 0 ; k<list.size() ; k++ ){ // rewrite the entire file without dead quote
                    fos.write((list.get(k).getContent() + "\r\n").getBytes()); //write channel & message ids
                }

                fos.close();
            } 
            catch (Exception e) {
                e.printStackTrace(); // coordinated yelling
            }
            }
        });
    }
}