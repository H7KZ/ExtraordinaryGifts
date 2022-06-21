package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.entity.Player;

public class OpenCommand {
    public static final String commandName = "open";

    public static void command(Player p) {
        p.sendMessage("Hello");
    }
}
