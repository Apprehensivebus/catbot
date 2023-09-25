package catgirlbot;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import org.javacord.api.event.message.MessageCreateEvent;

public class Someone {
    public static void doSomeone(MessageCreateEvent event){
        if (event.getMessageContent().toLowerCase().contains("catgirl, make me someone")) {
            addSomeone(event);
            event.getChannel().sendMessage("If you regret your decision type :\"catgirl, make me nobody\"");
        } 

        if (event.getMessageContent().toLowerCase().contains("catgirl, make me nobody")) {
            deleteSomeone(event);
        } 
        
        else if (event.getMessageContent().toLowerCase().contains("@someone")) {
            grabSomeone(event);
        }

    }

    
    public static void addSomeone (MessageCreateEvent event) { // adds a quote duh
        String id = event.getMessageAuthor().getIdAsString(); 

        try{ // so java no yell at me :'(
            event.getChannel().sendMessage("Added you to the @someone list :smile: "); //posting quote before saving
            FileOutputStream fos = new FileOutputStream(("someones/" + event.getServer().get().getIdAsString() + ".txt"), true); // get file named after the server
            fos.write((id + "\r\n").getBytes()); //write channel & message ids
            fos.close(); // to prevent memory leaks and more importantly java from yelling at me
        }

        catch (Exception e) {
            e.printStackTrace(); // coordinated yelling
            event.getChannel().sendMessage("Sorry, something went wrong I guess"); // if something fucks up just say oops
        }
    }

    public static void grabSomeone (MessageCreateEvent event) {
        try {
            Path path = Paths.get("someones/" + event.getServer().get().getIdAsString() + ".txt"); // get the file location for this thing
            List<String> ids = Files.readAllLines(path); // just read the entire file
            Integer quoteNumb =(int) Math.round(Math.random()*(ids.size()-1)); // select random quote number between 0 and max index of ids
            String id=ids.get(quoteNumb);
            if (id.equals(event.getMessageAuthor().getIdAsString())){
                grabSomeone(event); // yippee recursion
                System.out.println("had to recurse");
            } else {
                event.getChannel().sendMessage("<@" + id + ">"); // send that quote!
            }
        } 
        
        catch (Exception e) {
            e.printStackTrace(); // coordinated yelling
            event.getChannel().sendMessage("Sorry, something went wrong"); // if something fucks up just say oops
        }

    }

    public static void deleteSomeone (MessageCreateEvent event) {
        try {
            Path path = Paths.get("someones/" + event.getServer().get().getIdAsString() + ".txt"); // get the file location for this thing
            List<String> ids = Files.readAllLines(path);// just read the entire file and grab line of choice
            for (int i = 0 ; i<ids.size(); i++ ){
                //System.out.println(ids.get(i) + "|" + event.getMessageAuthor().getIdAsString());
                if (event.getMessageAuthor().getIdAsString().equals(ids.get(i))) {
                    ids.remove(ids.get(i)); // remove the marked for death person
                    event.getChannel().sendMessage("Okay, removed you from the list ");
                }
            }
            FileOutputStream fos = new FileOutputStream("someones/" + event.getServer().get().getIdAsString() + ".txt", false); // get file named after the server
            for (int k = 0 ; k<ids.size() ; k++ ){ // rewrite the entire file without dead quote
                fos.write((ids.get(k) + "\r\n").getBytes()); //write channel & message ids
            }   
            fos.close();
            
        } 
        catch (Exception e) {
            e.printStackTrace(); // coordinated yelling
            event.getChannel().sendMessage("Sorry, something went wrong"); // if something fucks up just say oops
        }
    }
}
