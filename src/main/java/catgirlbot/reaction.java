package catgirlbot;


import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.Role;


public class reaction {
    public static void reactionroles(Message message){
        message.addReactionAddListener(e -> {
            Role unv = e.getServer().get().getRolesByName("Unverified").get(0);
            e.getUser().get().removeRole(unv);
        });
    }
}
