package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.entity.Player;

public class SendCommand {
    public static final String commandName = "send";

    public static void command(Player p) {
        p.sendMessage("Hello");
    }
}
