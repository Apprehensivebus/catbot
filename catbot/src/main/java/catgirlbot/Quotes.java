package catgirlbot;

// yummy imports good thing we don't have customs fees in java land
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;


public class Quotes {
    public static void main (MessageCreateEvent event, DiscordApi api) { // the command if tree conveniently hidden away from main.java
        if (event.getMessageContent().toLowerCase().contains("catgirl, add quote")) {
            addQuote(event, api);
        } 
        
        else if (event.getMessageContent().toLowerCase().contains("catgirl, grab quote")) {
            grabQuote(event, api);
        } 
        
        else if (event.getMessageContent().toLowerCase().contains("catgirl, random quote")) {
            event.getChannel().sendMessage("I did not add this feature yet, pick a number yourself I guess");
        } 

        else if (event.getMessageContent().toLowerCase().contains("catgirl, delete quote")) {
            event.getChannel().sendMessage("I did not add this feature yet, just delete the message");
        }
    }

    public static void addQuote (MessageCreateEvent event, DiscordApi api) { // adds a quote duh
        String id = (event.getMessageContent().split("channels")[1]); // lazy method of splitting the important info from the message link hehehe
        
        try{ // so java no yell at me :'(
            Message quote = (Message) api.getMessageById(id.split("/")[3],(TextChannel) api.getChannelById(id.split("/")[2]).get()).join(); //construct message object from link              
            event.getChannel().sendMessage("\""+ quote.getContent() + "\" --" + quote.getAuthor().getDisplayName() + ""); //posting quote before saving
            FileOutputStream fos = new FileOutputStream(("quotes/" + id.split("/")[1] + ".txt"), true); // get file named after the server
            fos.write((String.valueOf(id.split("/")[2]) + "/" + String.valueOf(id.split("/")[3]) + "\r\n").getBytes()); //write channel & message ids
            fos.close(); // to prevent memory leaks and more importantly java from yelling at me
        } 
        
        catch (Exception e) {
            e.printStackTrace(); // coordinated yelling
            event.getChannel().sendMessage("Sorry, I could not find that message"); // if something fucks up just say oops
        }
    }

    public static void grabQuote (MessageCreateEvent event, DiscordApi api) {
        try {
            Path path = Paths.get("quotes/" + event.getServer().get().getId() + ".txt"); // get the file location for this thing
            Integer quoteNumb = Integer.parseInt(event.getMessageContent().split(":")[1]); // grab the quote number after the :
            String id = Files.readAllLines(path).get(quoteNumb); // just read the entire file and grab line of choice
            Message quote = (Message) api.getMessageById(id.split("/")[1], (TextChannel) api.getChannelById(id.split("/")[0]).get()).join();// perform black magic to grab the message from its id
            event.getChannel().sendMessage("\"" + quote.getContent() + "\" --" + quote.getAuthor().getDisplayName()); // send that quote!
        } 
        
        catch (Exception e) {
            e.printStackTrace(); // coordinated yelling
            event.getChannel().sendMessage("Sorry, I could not grab that"); // if something fucks up just say oops
        }
    }
}