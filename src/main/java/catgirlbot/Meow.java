package catgirlbot;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

public class Meow {
        public static void meowtimer (MessageCreateEvent event) {
            try {
                long delay = parseDelay(event);
                if (delay>-1) {
                    saveMeow(event.getChannel(), delay, event.getMessage());
                    event.getChannel().sendMessage("Okey, I\'ll meow at you <t:" + (Instant.now().getEpochSecond()+delay/1000) +":R>");
                    new Thread() {
                        public void run() {
                            try {
                                Meow.meow(event.getChannel(), delay, event.getMessage());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } else {
                    event.getChannel().sendMessage("That...... is not a positive number or you did something else to try and break my poor little brain");
                }
            } catch (Exception e) {
                e.printStackTrace();
                event.getChannel().sendMessage("That...... is not a positive number or you did something else to try and break my poor little brain"); // catch the exception when people give non--numbers as timer delay
            }
        }

        private static long parseDelay(MessageCreateEvent event) {
            long delay = -1;
            double amt;
            String[] timedelay = event.getMessageContent().replaceAll(",", "").substring(14).split(" ");
            if (timedelay[0].toLowerCase().contains("a") || timedelay[0].toLowerCase().contains("an")) {
                amt = 1;
            } else {
                amt = Double.parseDouble(timedelay[0]);
            }

            System.out.println(timedelay.length);
            if (amt == 1){
                if (timedelay.length>1) {
                    switch (timedelay[1].toLowerCase()) {
                        case "millisecond": 
                            delay = Math.round(1);
                            break;

                        case "second": 
                            delay = Math.round(1000);
                            break; 

                        case "minute": 
                            delay = Math.round(60000);
                            break; 

                        case "hour": 
                            delay = Math.round(3600000);
                            break; 

                        case "day": 
                            delay = Math.round(86400000);
                            break; 

                        case "weeks": // for the jokesters
                            delay = Math.round(amt*604800000);
                            break;  

                        case "month": // for the jokesters
                            delay = Math.round(2592000)*1000;
                            break;                    

                        case "years": // for the jokesters
                            delay = Math.round(amt*31536000)*1000;
                            break;

                        case "decade": // for the jokesters
                            delay = Math.round(315360000)*1000;
                            break;

                        default:
                            delay = -1;

                    }
                } else {
                    delay = Math.round(amt*1000);
                }
            } 
            else {
                if (timedelay.length>1) {
                    switch (timedelay[1].toLowerCase()) {
                        case "milliseconds": 
                            delay = Math.round(amt*1);
                            break;

                        case "seconds": 
                            delay = Math.round(amt*1000);
                            break; 

                        case "minutes": 
                            delay = Math.round(amt*60000);
                            break; 

                        case "hours": 
                            delay = Math.round(amt*3600000);
                            break; 

                        case "days": 
                            delay = Math.round(amt*86400000);
                            break; 

                        case "weeks": // for the jokesters
                            delay = Math.round(amt*604800000);
                            break;  

                        case "months": // for the jokesters
                            delay = Math.round(amt*2592000)*1000;
                            break;                    

                        case "years": // for the jokesters
                            delay = Math.round(amt*31536000)*1000;
                            break;

                        case "decades": // for the jokesters
                            delay = Math.round(amt*315360000)*1000;
                            break;

                        default:
                            delay = -1;

                    }
                } else {
                    delay = Math.round(amt*1000);
                }
            }

            return delay;
        }

        public static void meow(TextChannel channel, long delay, Message message)throws InterruptedException {
            Thread.sleep(delay);
            message.reply("Meow~, it has been " + delay/1000 + " seconds ");
        }  

        public static void saveMeow (TextChannel channel, long delay, Message message) { // adds a quote duh
            try{ // so java no yell at me :'(
                long endtime = delay+System.currentTimeMillis();
                FileOutputStream fos = new FileOutputStream(("meows.txt"), true); // get file named after the server
                fos.write((message.getServer().get().getIdAsString() + "/" + message.getChannel().getId() + "/" + message.getId() + "/" + endtime + "\r\n").getBytes()); //write channel & message ids
                fos.close(); // to prevent memory leaks and more importantly java from yelling at me
            }

            catch (Exception e) {
                e.printStackTrace(); // coordinated yelling
                message.getChannel().sendMessage("Sorry, something went wrong I guess"); // if something fucks up just say oops
            }
        }

        public static void loadMeow (DiscordApi api) throws Exception{
            Path path = Paths.get("meows.txt"); // get the file location for this thing
            List<String> ids = Files.readAllLines(path); // just read the entire file and grab line of choice
            Files.delete(path);
            for (int i = 0 ; i<ids.size(); i++ ){
                try{
                    if (Long.parseLong(ids.get(i).split("/")[3]) > System.currentTimeMillis()){
                        long delay = Long.parseLong(ids.get(i).split("/")[3]) - System.currentTimeMillis(); 
                        TextChannel channel = api.getTextChannelById(ids.get(i).split("/")[1]).get();
                        System.out.println(ids.get(i).split("/")[2]);
                        Message message = api.getMessageById(ids.get(i).split("/")[2] , channel).join();
                        saveMeow(api.getTextChannelById(ids.get(i).split("/")[1]).get(), delay, api.getMessageById(ids.get(i).split("/")[2] , api.getTextChannelById(ids.get(i).split("/")[1]).get()).get());  
                        new Thread() {
                            public void run() {
                                try {
                                    Meow.meow(channel , delay, message);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        System.out.println("Loaded a meow!");
                    } else {
                        System.out.println("Tried to load a meow, but it was expired");
                    }
                }catch(Exception e){
                    System.out.println("something went oopsie :p");
                }
            }
            System.out.println("Done loading meows!");
        }
}
