package cz.kominekjan.extraordinarygifts.commands.all;

import org.bukkit.entity.Player;

public class HelpCommand {
    public static final String commandName = "help";

    public static final String[] contents = {
            "",
            ""
    };

    public static void command(Player p) {
        p.sendMessage("Hello");
    }
}
