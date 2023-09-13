package catgirlbot;



import java.util.BitSet;
import java.util.HashMap;

import org.javacord.api.*;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.message.Messageable;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.json.JSONObject;




public class Main {
    static JSONObject perms;
    static HashMap<Long, BitSet> parsedPerms;
    static Message message;

    public static void main(String[] args) {
        String token = System.getenv("SECONDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();
        perms = Perms.loadPerms();
        parsedPerms = Perms.parsePerms(perms);
        Proxy.makeProxy(api);
        SetupCommand.setupCommand();

        api.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
            if (slashCommandInteraction.getFullCommandName().equals("catgirlbot permissions edit")) {
                if (slashCommandInteraction.getUser().isBotOwner() || slashCommandInteraction.getServer().get().hasPermission(slashCommandInteraction.getUser(), PermissionType.valueOf("MANAGE_CHANNELS"))) {
                    ServerChannel channel = slashCommandInteraction.getArgumentChannelValueByName("channel").get();
                 
                    message = new MessageBuilder().append("Press the buttons of the perms you want").addActionRow(
                        Button.primary("perm1", "Meow alarm"),
                        Button.primary("perm2", "Treasure hunt"),
                        Button.primary("perm3", "Cutesy triggers"),
                        Button.primary("perm4", "Anarchychess triggers"),
                        Button.primary("perm5", "Catgirl's quotes"),
                        Button.primary("perm6", "Mia's quotes"),
                        Button.primary("perm7", "Magic 8-ball"),
                        Button.primary("perm8", "@someone pinging"),
                        Button.danger("finish", "Finish editing")
                    ).send((Messageable) slashCommandInteraction.getChannel().get()).join();


                    message.addButtonClickListener((e) -> {
                        var interaction = e.getButtonInteraction();
                        if (interaction.getUser().getId() == slashCommandInteraction.getUser().getId()) {

                            if (interaction.getCustomId().equals("perm1")) {
                                posy++;
                            }
                            
                        }
                    });

                    int permission = slashCommandInteraction.getArgumentLongValueByName("permission").get().intValue();
                    slashCommandInteraction.createImmediateResponder().setContent("You tried to toggle permission " + permission + " in channel " + channel.getName()).setFlags(MessageFlag.EPHEMERAL).respond().join();
                    perms.put(String.valueOf(channel.getId()), permission);
                    Perms.savePerms(perms);
                    perms = Perms.loadPerms();
                    parsedPerms = Perms.parsePerms(perms);
                    System.out.println("Successfully changed perms!");
                }
            }
        });
 
        api.addSlashCommandCreateListener(event -> {
                SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
                if (slashCommandInteraction.getFullCommandName().equals("catgirlbot permissions manual")) {
                    if (slashCommandInteraction.getUser().isBotOwner() || slashCommandInteraction.getServer().get().hasPermission(slashCommandInteraction.getUser(), PermissionType.valueOf("MANAGE_CHANNELS"))) {
                        ServerChannel channel = slashCommandInteraction.getArgumentChannelValueByName("channel").get();
                        int permission = slashCommandInteraction.getArgumentLongValueByName("permission").get().intValue();
                        slashCommandInteraction.createImmediateResponder().setContent("You tried to toggle permission " + permission + " in channel " + channel.getName()).setFlags(MessageFlag.EPHEMERAL).respond().join();
                        perms.put(String.valueOf(channel.getId()), permission);
                        Perms.savePerms(perms);
                        perms = Perms.loadPerms();
                        parsedPerms = Perms.parsePerms(perms);
                        System.out.println("Successfully changed perms!");
                }
            }
        });


        
        api.addMessageCreateListener(event -> {
            if (event.getMessageAuthor().isRegularUser() && parsedPerms.containsKey(event.getChannel().getId())) {

                // block for meow timer, perm value 1
                if (parsedPerms.get(event.getChannel().getId()).get(0) && event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 14)).equalsIgnoreCase("Meow at me in ")) {
                    Meow.meowtimer(event);
                    return;
                }
                // end of block for meow timer
                
                // begin general block for commands starting with catgirl,  perm value 2
                if (event.getMessageContent().substring(0, Math.min(event.getMessageContent().length(), 9)).equalsIgnoreCase("catgirl, ")) {
                    if (parsedPerms.get(event.getChannel().getId()).get(1) && event.getMessageContent().substring(9).equalsIgnoreCase("treasure hunt")) {
                        new Treasurehunt(event, api).start();
                        return;
                    }
                }
                // end general block for commands starting with catgirl,

                // begin random cutesy stuff blocck, perm value 4
                if (parsedPerms.get(event.getChannel().getId()).get(2)){ 
                    Cutesy.doCute(event);
                }
                
                // begin anarchy chess, perm value 8
                if (parsedPerms.get(event.getChannel().getId()).get(3)){ 
                    AnCh.doAC(event);
                }

                //  begin quote magic, perm value 16
                if (parsedPerms.get(event.getChannel().getId()).get(4)){ 
                    Quotes.doQuote(event, api);
                } // if replaced with mias bot value 32
                else if (parsedPerms.get(event.getChannel().getId()).get(5)) {
                    Quotes.dontQuote(event, api);
                }

                // perm value 64 for magic 8 ball
                if (parsedPerms.get(event.getChannel().getId()).get(6)) {
                    EightBall.doBall(event);
                }

                // perm value 128 for someone ping 
                if (parsedPerms.get(event.getChannel().getId()).get(7)) {
                    Someone.doSomeone(event);
                }

                if ((event.getMessage().getContent().equalsIgnoreCase("catgirl, explain the permissions command to me")) && (event.getMessageAuthor().isBotOwner() || event.getServer().get().hasPermission(event.getMessageAuthor().asUser().get(), PermissionType.valueOf("MANAGE_CHANNELS")))) {
                    event.getChannel().sendMessage("Okay okay so this is going to be needlessly complex because Sasha's lazy and should make a better system but: \n"
                    + "So first you use /catgirlbot permissions toggle, this will ask for the channel where you want to change her permissions, and a value.\n"
                    + "You get the value by adding up the following numbers for each feature you want enabled, or 0 for none: \n"
                    + "Meow alarm clock : 1 \n Treasure hunt : 2 \n Random cute triggers : 4 \n Anarchychess triggers : 8 \n Catgirls quotes: 16 \n Catgirl quotes when Mia's bot replaces hers : 32 \n Magic 8-ball : 64 \n The @someone ping : 128 \n"
                    + "In order to enable everything except quotes the current value is 239"
                    );
                }

            }
        });
    }
}