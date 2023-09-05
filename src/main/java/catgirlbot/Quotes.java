package catgirlbot;

// yummy imports good thing we don't have customs fees in java land
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;


public class Quotes {
    public static void doQuote (MessageCreateEvent event, DiscordApi api) { // the command if tree conveniently hidden away from main.java
        if (event.getMessageContent().toLowerCase().contains("catgirl, add quote")) {
            addQuote(event, api);
        } 
        
        else if (event.getMessageContent().toLowerCase().contains("catgirl, grab quote")) {
            grabQuote(event, api);
        } 
        
        else if (event.getMessageContent().toLowerCase().contains("catgirl, random quote")) {
            randomQuote(event, api);
        } 

        else if (event.getMessageContent().toLowerCase().contains("catgirl, delete quote") && event.getMessage().getAuthor().isBotOwner()) {
            deleteQuote(event, api);
        }
    }

    public static void addQuote (MessageCreateEvent event, DiscordApi api) { // adds a quote duh
        String id = (event.getMessageContent().split("channels")[1]); // lazy method of splitting the important info from the message link hehehe
        
        try{ // so java no yell at me :'(
            Message quote = (Message) api.getMessageById(id.split("/")[3],(TextChannel) api.getChannelById(id.split("/")[2]).get()).join(); //construct message object from link              
            event.getChannel().sendMessage("\"" + quote.getContent().replace('@','.') + "\" --" + quote.getAuthor().getDisplayName() + ""); //posting quote before saving
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
            event.getChannel().sendMessage("Quote (" + quoteNumb + ") \"" + quote.getContent().replace('@','.') + "\" --" + quote.getAuthor().getDisplayName()); // send that quote!
        } 
        
        catch (Exception e) {
            e.printStackTrace(); // coordinated yelling
            event.getChannel().sendMessage("Sorry, I could not grab that"); // if something fucks up just say oops
        }
    }

    public static void randomQuote (MessageCreateEvent event, DiscordApi api) {
        try {
            Path path = Paths.get("quotes/" + event.getServer().get().getId() + ".txt"); // get the file location for this thing
            List<String> ids = Files.readAllLines(path); // just read the entire file
            Integer quoteNumb =(int) Math.round(Math.random()*(ids.size()-1)); // select random quote number between 0 and max index of ids
            String id=ids.get(quoteNumb);
            Message quote = (Message) api.getMessageById(id.split("/")[1], (TextChannel) api.getChannelById(id.split("/")[0]).get()).join();// perform black magic to grab the message from its id
            event.getChannel().sendMessage("Quote (" + quoteNumb + ") \"" + quote.getContent().replace('@','.') + "\" --" + quote.getAuthor().getDisplayName()); // send that quote!
        } 
        
        catch (Exception e) {
            e.printStackTrace(); // coordinated yelling
            event.getChannel().sendMessage("Sorry, something went wrong"); // if something fucks up just say oops
        }

    }

    public static void deleteQuote (MessageCreateEvent event, DiscordApi api) {
        try {
            Path path = Paths.get("quotes/" + event.getServer().get().getId() + ".txt"); // get the file location for this thing
            Integer quoteNumb = Integer.parseInt(event.getMessageContent().split(":")[1]); // grab the quote number after the :
            List<String> ids = Files.readAllLines(path);// just read the entire file and grab line of choice
            ids.remove(ids.get(quoteNumb)); // remove the marked for death quote
            FileOutputStream fos = new FileOutputStream(("quotes/" + event.getServer().get().getId() + ".txt"), false); // get file named after the server
            for (int k = 0 ; k<ids.size() ; k++ ){ // rewrite the entire file without dead quote
                fos.write((ids.get(k) + "\r\n").getBytes()); //write channel & message ids
            }   
            fos.close();
            event.getChannel().sendMessage("Okay, deleted quote " + quoteNumb);
        } 
        
        catch (Exception e) {
            e.printStackTrace(); // coordinated yelling
            event.getChannel().sendMessage("Sorry, I could not delete that"); // if something fucks up just say oops
        }
    }

// for noe
    public static void dontQuote (MessageCreateEvent event, DiscordApi api) { // the command if tree conveniently hidden away from main.java
        if (event.getMessageContent().toLowerCase().contains("catgirl, add quote")) {
            event.getChannel().sendMessage("Sorry, but my functionality has been replaced by Mia's QuotationBot, please use the command /quote to get started");
        } 
        
        else if (event.getMessageContent().toLowerCase().contains("catgirl, grab quote")) {
            event.getChannel().sendMessage("Sorry, but my functionality has been replaced by Mia's QuotationBot, please use the command /quote to get started");
        } 
        
        else if (event.getMessageContent().toLowerCase().contains("catgirl, random quote")) {
            event.getChannel().sendMessage("Sorry, but my functionality has been replaced by Mia's QuotationBot, please use the command /quote to get started");
        } 

        else if (event.getMessageContent().toLowerCase().contains("catgirl, delete quote") && event.getMessage().getAuthor().isBotOwner()) {
            event.getChannel().sendMessage("Sorry, but my functionality has been replaced by Mia's QuotationBot, please use the command /quote to get started");
        }
    }
}
