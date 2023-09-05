package catgirlbot;


import java.util.HashMap; // import the HashMap class
import java.util.Random;
import org.javacord.api.*;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

public class Treasurehunt extends Thread {
    int px;
    int py;
    MessageCreateEvent event;
    DiscordApi api;

    public Treasurehunt(MessageCreateEvent even, DiscordApi ap) {
        event = even;
        api = ap;
    };

    public void run() {
        //This hashmap thing should be cleaner idk how tho
        HashMap<String, Integer> movesX = new HashMap<String, Integer>(); 
        movesX.put("north", 1); 
        movesX.put("south", -1);
        movesX.put("east", 0);
        movesX.put("west", -0);
        HashMap<String, Integer> movesY = new HashMap<String, Integer>(); 
        movesY.put("north", 0);
        movesY.put("south", 0);
        movesY.put("east", 1);
        movesY.put("west", -1);

        event.getChannel().sendMessage("Treasure hunting time!\n Original credit to verysmollgecko!! \n\n(use **north, south, east, west** to move!) NOW FIND IT!! :3\n--------");
        Random generator = new Random(System.currentTimeMillis());
        final int ty = generator.nextInt(10); // random starts
        final int tx = generator.nextInt(10);
        py = generator.nextInt(10);
        px = generator.nextInt(10);
        if (ty == py) {
            py = generator.nextInt(10); // can't start on the tresure
        }
        if (tx == px) {
            px = generator.nextInt(10);
        }

        Message mess = (Message) event.getChannel().sendMessage("Your starting coordinates are:(" + px + "," + py + ")*\n---\nYou're **" + (Math.abs(px - tx) + Math.abs(py - ty)) + "** spaces away :3").join();
        api.addMessageCreateListener(msg -> {
            if (msg.getChannel() == mess.getChannel() && movesY.containsKey(msg.getMessageContent())) {
                px += movesX.get(msg.getMessageContent()); // add the moves from the hashmap
                py += movesY.get(msg.getMessageContent());
                mess.edit("You moved 1 space " + msg.getMessageContent() + " your new coordinates are:(" + px + "," + py + ")*\n---\nYou're **" + (Math.abs(px - tx) + Math.abs(py - ty)) + "** spaces away :3");
                if (py == ty && px == tx) {
                    mess.edit("**You found my treasure!!** :pensive: ");
                    return;
                }
            msg.deleteMessage(); // prevent spam
            }
        }); 
    }
}
