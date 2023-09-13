package catgirlbot;



import java.io.IOException;
import java.util.List;

import org.javacord.api.*;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.server.Server;
import org.javacord.api.interaction.SlashCommandInteraction;




public class Main {

    public static void main(String[] args) {
        String token = System.getenv("THIRDDISTOKEN");
        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.GUILD_MEMBERS).login().join();


        //SetupCommand.setupCommand();

        api.addUserChangeNicknameListener(event ->{
            Server server = event.getServer();
            List<String> bads;
            List<String> weirbs;
            System.out.println("aaah");
            try {
                weirbs = GoobNames.getWeirbs(server);
                bads = GoobNames.getBads(server);
                if (!GoobNames.deBadify(GoobNames.deWeirbify(event.getNewNickname().get().toLowerCase(), weirbs), bads).equals(event.getNewNickname().get().toLowerCase())){
                    event.getUser().updateNickname(event.getServer(),GoobNames.deBadify(GoobNames.deWeirbify(event.getNewNickname().get().toLowerCase(), weirbs), bads));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            
        });

        api.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
            if (slashCommandInteraction.getFullCommandName().equals("catgirlpolice filter add")) {
                if (slashCommandInteraction.getUser().isBotOwner() || slashCommandInteraction.getServer().get().hasPermission(slashCommandInteraction.getUser(), PermissionType.valueOf("MANAGE_CHANNELS"))) {
                    char weirb = slashCommandInteraction.getArgumentStringValueByName("weirbchar").get().toCharArray()[0];
                    char unweirb = slashCommandInteraction.getArgumentStringValueByName("normchar").get().toCharArray()[0];
                    slashCommandInteraction.createImmediateResponder().setContent("You tried to add weirb " + weirb + " to  " + unweirb).setFlags(MessageFlag.EPHEMERAL).respond().join();
                    Server server = slashCommandInteraction.getServer().get();
                    try {
                        GoobNames.addWeirb(server, weirb, unweirb);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (slashCommandInteraction.getFullCommandName().equals("catgirlpolice filter test")) {
                if (slashCommandInteraction.getUser().isBotOwner() || slashCommandInteraction.getServer().get().hasPermission(slashCommandInteraction.getUser(), PermissionType.valueOf("MANAGE_CHANNELS"))) {
                    Server server = slashCommandInteraction.getServer().get();
                    String stringe = slashCommandInteraction.getArgumentStringValueByName("word").get();
                    List<String> weirbs;
                    try {
                        weirbs = GoobNames.getWeirbs(server);
                        slashCommandInteraction.createImmediateResponder().setContent(GoobNames.deWeirbify(stringe, weirbs)).setFlags(MessageFlag.EPHEMERAL).respond().join();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }


            if (slashCommandInteraction.getFullCommandName().equals("catgirlpolice bad add")) {
                if (slashCommandInteraction.getUser().isBotOwner() || slashCommandInteraction.getServer().get().hasPermission(slashCommandInteraction.getUser(), PermissionType.valueOf("MANAGE_CHANNELS"))) {
                    String bad = slashCommandInteraction.getArgumentStringValueByName("badword").get();
                    String goob = slashCommandInteraction.getArgumentStringValueByName("goodword").get();
                    slashCommandInteraction.createImmediateResponder().setContent("You tried to add bad word " + bad + " to  " + goob).setFlags(MessageFlag.EPHEMERAL).respond().join();
                    Server server = slashCommandInteraction.getServer().get();
                    try {
                        GoobNames.addBad(server, bad, goob);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Successfully changed perms!");
                }
            }


            if (slashCommandInteraction.getFullCommandName().equals("catgirlpolice bad test")) {
                if (slashCommandInteraction.getUser().isBotOwner() || slashCommandInteraction.getServer().get().hasPermission(slashCommandInteraction.getUser(), PermissionType.valueOf("MANAGE_CHANNELS"))) {
                    Server server = slashCommandInteraction.getServer().get();
                    String stringe = slashCommandInteraction.getArgumentStringValueByName("word").get();
                    List<String> bads;
                    List<String> weirbs;
                    try {
                        weirbs = GoobNames.getWeirbs(server);
                        bads = GoobNames.getBads(server);
                        slashCommandInteraction.createImmediateResponder().setContent(GoobNames.deBadify(GoobNames.deWeirbify(stringe, weirbs), bads)).setFlags(MessageFlag.EPHEMERAL).respond().join();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });


        System.out.println("catgorl ready");
    }
}