package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.entity.Player;

public class CreateCommand {
    public static final String commandName = "create";

    public static void command(Player p) {
        p.sendMessage("Hello");
    }
}
