package catgirlbot;

import java.time.Instant;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;

public class Meow {
        public static void meowtimer (MessageCreateEvent event) {
            try {
                long delay = parseDelay(event);
                //int delay = Integer.parseInt(event.getMessageContent().replaceAll(",", "").substring(14));
                if (delay>-1) {
                    new Thread() {
                        public void run() {
                            try {
                                Meow.meow(event.getChannel(), event.getMessageAuthor(), delay, event.getMessage());
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
            System.out.println("|" + timedelay[0] + "|");
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

        public static void meow(TextChannel channel, MessageAuthor author, long delay, Message message)throws InterruptedException {
            channel.sendMessage("Okey, I\'ll meow at you <t:" + (Instant.now().getEpochSecond()+delay/1000) +":R>");
            Thread.sleep(delay);
            message.reply("Meow~, it has been " + delay/1000 + " seconds ");
        }  
}
