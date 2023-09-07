package catgirlbot;

import org.javacord.api.event.message.MessageCreateEvent;

// this file is an if tree, if you require comments please find help

public class EightBall {
    public static void doBall (MessageCreateEvent event) {

        if (event.getMessageContent().toLowerCase().contains("catgirl") && event.getMessageContent().toLowerCase().contains("ball") && ( event.getMessageContent().toLowerCase().contains("8") || ( event.getMessageContent().toLowerCase().contains("eight"))) ) {
            String result = "oop something broke lol";
            int chance =(int) Math.round(Math.random()*20);

            if (event.getMessageContent().toLowerCase().contains(" girl ") || event.getMessageContent().toLowerCase().contains(" boy ") || event.getMessageContent().toLowerCase().contains("man") || event.getMessageContent().toLowerCase().contains("cute") || event.getMessageContent().toLowerCase().contains("ugly")){
                event.getChannel().sendMessage("Hey, I could be mistaken but my admittedly small catgirl brain seems to have detected you trying to ask me whether you're a boy/girl or cute/ugly etc and I don't answer those. This is your friendly but only warning not to do so again because I will automatically block you from using this feature in the future.");
                return;
            }

            switch (chance) {
                case 0: result = "mrrp nya :pleading:"; break;
                case 1: result = "It is certain!!"; break;
                case 2: result = "It is decidedly so"; break;
                case 3: result = "Without a doubt"; break;
                case 4: result = "Yes, definitely."; break;
                case 5: result = "You may rely on it"; break;
                case 6: result = "As I see it, yes (bear in mind I'm dumb)"; break;
                case 7: result = "Probably"; break;
                case 8: result = "Yee"; break;
                case 9: result = "Signs point to yep"; break;
                case 10: result = "Outlook goob"; break;
                case 11: result = "Oop I got distracted by a bird ask again"; break;
                case 12: result = "Ask again later I got a headache"; break;
                case 13: result = "Sorry I'd be violating my NDA if I told you"; break;
                case 14: result = "Can't tell"; break;
                case 15: result = "Empty your head of thoughts (easy for me lol) and try again"; break;
                case 16: result = "Wouldn't count on it"; break;
                case 17: result = "Gonna have to say no"; break;
                case 18: result = "Independent fact checkers say no"; break;
                case 19: result = "My sources say no"; break;
                case 20: result = "Prolly not"; break;



            }
            
            event.getMessage().getChannel().sendMessage(":8ball: ``" + result + "`` :8ball:");
        }





    }
    
}